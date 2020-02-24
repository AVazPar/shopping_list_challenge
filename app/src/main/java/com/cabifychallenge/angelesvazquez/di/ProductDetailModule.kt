package com.cabifychallenge.angelesvazquez.di

import com.cabifychallenge.angelesvazquez.ui.home.productDetail.ProductDetailViewModel
import com.cabifychallenge.angelesvazquez.ui.shopping.completed.CompletedDialogViewModel
import com.cabifychallenge.angelesvazquez.usecases.DeleteAllProductsUseCase
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * Author: ÁngelesVP
 * I used to create several modules for Koin
 */
val productDetailModule = module {

    viewModel {
        ProductDetailViewModel(addProductToSLUseCase = get(), updateProductUseCase = get())
    }

    viewModel {
        CompletedDialogViewModel(deleteAllProductsUseCase = get())
    }

    factory {
        DeleteAllProductsUseCase(productsRepository = get())
    }
}