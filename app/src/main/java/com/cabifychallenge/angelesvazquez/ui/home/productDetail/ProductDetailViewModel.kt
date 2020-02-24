package com.cabifychallenge.angelesvazquez.ui.home.productDetail

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cabifychallenge.angelesvazquez.common.EitherClass
import com.cabifychallenge.angelesvazquez.ui.home.entities.Product
import com.cabifychallenge.angelesvazquez.usecases.AddProductToSLUseCase
import com.cabifychallenge.angelesvazquez.usecases.UpdateProductUseCase
import kotlinx.coroutines.*
import java.lang.Exception

/**
 * Author: AngelesVP
 *
 * This is the product detail fragment viewmodel where I call Room database. For that, I use a Kotlin
 * viewmodel scope coroutine.
 *
 * In that case, we use insert and update request.
 *
 * */
class ProductDetailViewModel(private val addProductToSLUseCase: AddProductToSLUseCase,
                             private val updateProductUseCase: UpdateProductUseCase) : ViewModel() {

    var mProduct : MutableLiveData<Product> = MutableLiveData()

    fun addProductToShoppingList(){

        viewModelScope.launch{
            withContext(Dispatchers.IO) {
                try{
                    addProductToSLUseCase.run(param = mProduct.value!!)
                }catch (e: Exception){
                    Log.e("ERROR -> ", e.toString())
                }finally {
                    Log.e("FINALLY -> ", "")
                }
            }
        }
    }

    fun updateProduct(){
        viewModelScope.launch{
            withContext(Dispatchers.IO) {
                val it = updateProductUseCase.run(param = mProduct.value!!)
                when (it) {
                    is EitherClass.Success -> {
                        Log.e("UPDATED ", "")
                    }
                    is EitherClass.Error -> {
                        Log.e("ERROR -> ", it.e)
                    }
                }
            }
        }
    }
}