package com.cabifychallenge.angelesvazquez.ui.home

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.cabifychallenge.angelesvazquez.ui.MainActivityViewModel
import com.cabifychallenge.angelesvazquez.ui.MainToolbar
import com.cabifychallenge.angelesvazquez.R
import com.cabifychallenge.angelesvazquez.common.base.BaseFragment
import com.cabifychallenge.angelesvazquez.ui.home.adapter.ProductAdapter
import kotlinx.android.synthetic.main.fragment_home.*
import org.koin.android.viewmodel.ext.android.sharedViewModel

/**
 * Author: ÁngelesVP
 *
 * This is the first fragment, I've used a BaseFragment class where I add the viewModel into it.
 * We can use this viewModel in this fragment, in that case HomeViewModel.
 *
 * As well I use the sharedviewModel to pass product value to detail product view.
 *
 * In onCreateView override method we set the layout XML, and onActivityCreated override method I
 * call to Service API call to set in the recycler view all data.
 */
class HomeFragment : BaseFragment<HomeViewModel>(HomeViewModel::class) {

    override fun layoutId(): Int = R.layout.fragment_home

    private val sharedViewModel: MainActivityViewModel by sharedViewModel()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel.getAllProducts()

        setActionBar()
        setupRecyclerView()
        observe()
    }

    /**
     * Setup the recycler view with our custom product adapter
     */
    private fun setupRecyclerView(){
        val productAdapter = ProductAdapter(mDataSet = ArrayList(), context = context)
        recycler_products?.adapter = productAdapter

        productAdapter.onItemClick = {
            sharedViewModel.setProduct(it)
            findNavController().navigate(R.id.action_home_to_product_detail)
        }
    }

    /**
     * Set backIcon into action bar
     */
    private fun setActionBar() {
        var homeToolbar = MainToolbar(backIcon = false)
        sharedViewModel.setScreenTitle(mainToolbar = homeToolbar)
    }

    /**
     * We observe if product list has some changes from viewmodel
     */
    private fun observe(){
        viewModel.mProductList.observe(viewLifecycleOwner, Observer {
            it.let{
                (recycler_products?.adapter as? ProductAdapter)?.onNewData(it)
            }
        })
    }
}