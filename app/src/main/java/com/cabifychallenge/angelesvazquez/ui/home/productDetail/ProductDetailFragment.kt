package com.cabifychallenge.angelesvazquez.ui.home.productDetail

import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.lifecycle.Observer
import com.cabifychallenge.angelesvazquez.R
import com.cabifychallenge.angelesvazquez.common.base.BaseFragment
import com.cabifychallenge.angelesvazquez.common.utility.DiscountCalculate
import com.cabifychallenge.angelesvazquez.ui.MainActivityViewModel
import com.cabifychallenge.angelesvazquez.ui.MainToolbar
import com.cabifychallenge.angelesvazquez.ui.home.entities.Product
import kotlinx.android.synthetic.main.fragment_product_detail.*
import org.koin.android.viewmodel.ext.android.sharedViewModel

/**
 * Author: ÁngelesVP
 *
 * I've used a BaseFragment class where I add the viewModel into it.
 * We can use this viewModel in this fragment, in that case ProductDetailViewModel.
 *
 * As well I use the sharedviewModel to get product value into this view.
 *
 * In onCreateView override method we set the layout XML, and onActivityCreated override method I
 * can add the product selected by the user, with the discount, price, quanitity, that he has choosen.
 * I use Room database for that.
 *
 * In addiction, we set in this view all data.
 */
class ProductDetailFragment : BaseFragment<ProductDetailViewModel>(ProductDetailViewModel::class),
    AdapterView.OnItemSelectedListener {

    override fun layoutId(): Int = R.layout.fragment_product_detail

    private val sharedViewModel: MainActivityViewModel by sharedViewModel()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        sp_quantity2.onItemSelectedListener = this

        initUI(sharedViewModel.getProduct().value!!)
        setActionBar()
        setSpinner()
        clickListener()
        observe()
    }

    /**
     * Set the product item values from the sharedviewModel
     */
    private fun initUI(it: Product){
        tv_product_detail_title.text = it.description
        tv_product_detail_last_price.text = String.format("%.2f", it.amount).plus(it.currency)
        tv_product_detail_price.text = String.format("%.2f", it.amount).plus(it.currency)
        iv_product_detail_image.background = resources.getDrawable(it.image!!)

        if(it.discount.isNullOrEmpty()){
            bt_discount.visibility = View.GONE
        }else{
            bt_discount.findViewById<Button>(R.id.bt_discount).text = it.discount
        }
        viewModel.mProduct.value = it
    }

    /**
     * Set spinner method for choose quantity
     */
    private fun setSpinner(){
        ArrayAdapter.createFromResource(
            context!!,
            R.array.quantity,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            sp_quantity2.adapter = adapter
        }
    }

    /**
     * Set functionality into discount button, and add to shopping list button.
     */
    private fun clickListener(){
        bt_add_to_shopping_list.setOnClickListener {
            viewModel.updateProduct()
            viewModel.addProductToShoppingList()
        }

        bt_discount.setOnClickListener {
            val discount = DiscountCalculate.getDiscountItem(
                code = viewModel.mProduct.value!!.code!!,
                amount = viewModel.mProduct.value!!.amount!!,
                quantity = viewModel.mProduct.value!!.quantiy!!.toInt())

            viewModel.mProduct.value!!.discountValue = discount
            viewModel.mProduct.value!!.amount = viewModel.mProduct.value!!.amount!!.minus(discount)
            tv_product_detail_price.text = String.format("%.2f", (viewModel.mProduct.value!!.amount!!)).plus(viewModel.mProduct.value!!.currency)
        }
    }

    /**
     * We observe if product item has some changes from viewmodel
     */
    private fun observe(){
        viewModel.mProduct.observe(viewLifecycleOwner, Observer {
            tv_product_detail_price.text = String.format("%.2f", it.amount).plus(it.currency)
        })
    }

    /**
     * Set backIcon into action bar
     */
    private fun setActionBar() {
        var productToolbar = MainToolbar(backIcon = true)
        sharedViewModel.setScreenTitle(mainToolbar = productToolbar)
    }

    /**
     * Spinner listener methods
     */
    override fun onNothingSelected(parent: AdapterView<*>?) {}

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        viewModel.mProduct.value!!.quantiy = parent!!.getItemAtPosition(position).toString()
        viewModel.mProduct.value!!.amount = viewModel.mProduct.value!!.amount_per_unit!!.times(parent.getItemAtPosition(position).toString().toFloat())
        tv_product_detail_last_price.text = String.format("%.2f", (viewModel.mProduct.value!!.amount!!)).plus(viewModel.mProduct.value!!.currency)
        tv_product_detail_price.text = String.format("%.2f", (viewModel.mProduct.value!!.amount!!)).plus(viewModel.mProduct.value!!.currency)
    }
}