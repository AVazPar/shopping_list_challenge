package com.cabifychallenge.angelesvazquez.ui.home

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cabifychallenge.angelesvazquez.common.EitherClass
import com.cabifychallenge.angelesvazquez.data.toView
import com.cabifychallenge.angelesvazquez.ui.home.entities.Product
import com.cabifychallenge.angelesvazquez.usecases.GetAllProductsUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.coroutines.suspendCoroutine


/**
 * Author: AngelesVP
 *
 * This is the home fragment viewmodel where we call to API service call. For that, I use a Kotlin
 * viewmodel scope coroutine. I choose that because I call to Retrofit and Room calls from two differents
 * viewmodels at the same time. So, I think it's better use viewmodelScope, not GlobalScope.
 *
 * */
class HomeViewModel(private val getAllProductUseCase: GetAllProductsUseCase) : ViewModel() {

    var mProductList: MutableLiveData<List<Product>> = MutableLiveData()

    fun getAllProducts() {
        viewModelScope.launch{
            withContext(Dispatchers.Default) {
                suspendCoroutine<Unit> {
                    when (val it = getAllProductUseCase.run()) {
                        is EitherClass.Success -> {
                            mProductList.postValue(it.s!!.toView())
                        }
                        is EitherClass.Error -> {
                            Log.e("ERROR -> ", it.e)
                        }
                    }
                }
            }
        }
    }
}