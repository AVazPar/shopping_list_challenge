package com.cabifychallenge.angelesvazquez.ui.shopping

import android.os.Bundle
import androidx.lifecycle.Observer
import com.cabifychallenge.angelesvazquez.R
import com.cabifychallenge.angelesvazquez.common.base.BaseFragment
import com.cabifychallenge.angelesvazquez.common.utility.DiscountCalculate
import com.cabifychallenge.angelesvazquez.ui.MainActivityViewModel
import com.cabifychallenge.angelesvazquez.ui.MainToolbar
import com.cabifychallenge.angelesvazquez.ui.home.adapter.ProductAdapter
import com.cabifychallenge.angelesvazquez.ui.shopping.completed.CompletedDialog
import kotlinx.android.synthetic.main.fragment_shopping.*
import org.koin.android.viewmodel.ext.android.sharedViewModel

/**
 * Author: ÁngelesVP
 *
 * This is the first fragment, I've used a BaseFragment class where I add the viewModel into it.
 * We can use this viewModel in this fragment, in that case ShoppingViewModel.
 *
 * As well I use the sharedviewModel to pass product value to detail product view.
 *
 * In onCreateView override method we set the layout XML, and onActivityCreated override method I
 * call to Service API call to set in the recycler view all data.
 */
class ShoppingFragment : BaseFragment<ShoppingViewModel>(ShoppingViewModel::class) {

    override fun layoutId(): Int = R.layout.fragment_shopping

    private val sharedViewModel: MainActivityViewModel by sharedViewModel()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        setActionBar()
        setupRecyclerView()
        clickListener()
        observe()
    }

    override fun onResume() {
        super.onResume()
        viewModel.getShoppingList()
    }

    /**
     * Setup the recycler view with our custom product adapter
     */
    private fun setupRecyclerView(){
        val shoppingAdapter = ProductAdapter(mDataSet = ArrayList(), context = context)
        recycler_shopping_list?.adapter = shoppingAdapter
    }

    /**
     * Set functionality into shopping fragment button to complete the order.
     */
    private fun clickListener(){
        bt_shopping.setOnClickListener {
            val dialog = CompletedDialog()
            dialog.show(parentFragmentManager, getString(R.string.completed))
        }
    }

    /**
     * Set backIcon into action bar
     */
    private fun setActionBar() {
        val shoppingToolbar = MainToolbar(backIcon = false)
        sharedViewModel.setScreenTitle(mainToolbar = shoppingToolbar)
    }

    /**
     * We observe if product list choosen by the user if it has some changes from viewmodel
     */
    private fun observe(){
        viewModel.mShoppingList.observe(viewLifecycleOwner, Observer {
            it.let{
                (recycler_shopping_list?.adapter as? ProductAdapter)?.onNewData(it)

                val discountPrices = DiscountCalculate.getTotalPrice(viewModel.mShoppingList.value!!)

                tv_total_price_value.text = String.format("%.2f", discountPrices.last_price).plus(discountPrices.currency)
                tv_total_discount_value.text = "-".plus(String.format("%.2f", discountPrices.discount).plus(discountPrices.currency))
                tv_total_real_value.text = String.format("%.2f", discountPrices.real_price).plus(discountPrices.currency)
            }
        })
    }
}