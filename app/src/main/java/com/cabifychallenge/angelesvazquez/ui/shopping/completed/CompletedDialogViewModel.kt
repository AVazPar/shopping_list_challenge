package com.cabifychallenge.angelesvazquez.ui.shopping.completed

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cabifychallenge.angelesvazquez.common.EitherClass
import com.cabifychallenge.angelesvazquez.usecases.DeleteAllProductsUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.coroutines.suspendCoroutine

/**
 * Author: AngelesVP
 *
 * This is the complete dialog fragment viewmodel where we call to Room database to delete all products from the list.
 * For that, I use a Kotlin viewmodel scope coroutine.
 * */
class CompletedDialogViewModel(private val deleteAllProductsUseCase: DeleteAllProductsUseCase): ViewModel() {

    fun cleanShoppingList() {
        viewModelScope.launch{
            withContext(Dispatchers.IO) {
                suspendCoroutine<Unit> {
                    val it = deleteAllProductsUseCase.run()
                    when(it){
                        is EitherClass.Success -> {
                            Log.e("SUCCESS -> ", it.s.toString())
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