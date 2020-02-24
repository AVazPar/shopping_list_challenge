package com.cabifychallenge.angelesvazquez.ui.shopping

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cabifychallenge.angelesvazquez.common.EitherClass
import com.cabifychallenge.angelesvazquez.ui.home.entities.Product
import com.cabifychallenge.angelesvazquez.usecases.GetShoppingListUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.coroutines.suspendCoroutine

/**
 * Author: AngelesVP
 *
 * This is the shopping fragment viewmodel where we call to Room database. For that, I use a Kotlin
 * viewmodel scope coroutine. I choose that because I call to Retrofit and Room calls from two differents
 * viewmodels at the same time. So, I think it's better use viewmodelScope, not GlobalScope.
 *
 * I get all shopping list from Room database into mShoppingList LiveData.
 *
 * */
class ShoppingViewModel(private val getShoppingListUseCase: GetShoppingListUseCase) : ViewModel() {

    var mShoppingList: MutableLiveData<List<Product>> = MutableLiveData()

    fun getShoppingList(){
        viewModelScope.launch{
            withContext(Dispatchers.IO) {
                suspendCoroutine<Unit> {
                    val it = getShoppingListUseCase.run()
                    when(it){
                        is EitherClass.Success -> {
                            mShoppingList.postValue(it.s)
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