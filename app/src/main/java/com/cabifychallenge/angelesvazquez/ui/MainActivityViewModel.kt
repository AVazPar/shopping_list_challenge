package com.cabifychallenge.angelesvazquez.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.cabifychallenge.angelesvazquez.ui.home.entities.Product

/**
 * Author: ÁngelesVP
 *
 * This is the mainActivity viewModel. The data of this viewmodel is access from all fragment viewmodels
 * like a sharedViewModel.
 *
 * In this case, I use this viewmodel to manage the toolbar and pass the product value from item
 * list product clicked (all product list view) to detail item product view.
 */
class MainActivityViewModel: ViewModel() {

    private var toolbarTitleObservable = MutableLiveData<MainToolbar>()
    private var mProduct = MutableLiveData<Product>()

    fun getScreenTitle(): LiveData<MainToolbar> {
        return toolbarTitleObservable
    }

    fun setScreenTitle(mainToolbar: MainToolbar) {
        toolbarTitleObservable.value = mainToolbar
    }

    fun getProduct(): LiveData<Product> {
        return mProduct
    }

    fun setProduct(shoppingProduct: Product) {
        mProduct.value = shoppingProduct
    }
}

data class MainToolbar(val backIcon: Boolean)
