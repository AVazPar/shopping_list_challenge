package com.cabifychallenge.angelesvazquez.di

import com.cabifychallenge.angelesvazquez.data.ProductsRepository
import com.cabifychallenge.angelesvazquez.framework.ProductsDataSource
import com.cabifychallenge.angelesvazquez.framework.RetrofitProductsDataSource
import com.cabifychallenge.angelesvazquez.ui.home.HomeViewModel
import com.cabifychallenge.angelesvazquez.usecases.GetAllProductsUseCase
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * Author: ÁngelesVP
 * I used to create several modules for Koin.
 */
val homeModule = module {

    viewModel {
        HomeViewModel(getAllProductUseCase = get())
    }

    factory {
        GetAllProductsUseCase(productsRepository = get())
    }

    single {
        ProductsRepository(productsDataSource = get(), shoppingListDataSource = get())
    }

    single<ProductsDataSource> {
        RetrofitProductsDataSource()
    }
}