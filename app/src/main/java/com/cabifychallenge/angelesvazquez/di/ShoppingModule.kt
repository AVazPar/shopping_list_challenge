package com.cabifychallenge.angelesvazquez.di

import com.cabifychallenge.angelesvazquez.data.room.ProductDatabase
import com.cabifychallenge.angelesvazquez.framework.RoomShoppingListDataSource
import com.cabifychallenge.angelesvazquez.framework.ShoppingListDataSource
import com.cabifychallenge.angelesvazquez.ui.shopping.ShoppingViewModel
import com.cabifychallenge.angelesvazquez.usecases.AddProductToSLUseCase
import com.cabifychallenge.angelesvazquez.usecases.GetShoppingListUseCase
import com.cabifychallenge.angelesvazquez.usecases.UpdateProductUseCase
import org.koin.android.ext.koin.androidContext
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * Author: ÁngelesVP
 * I used to create several modules for Koin
 */
val shoppingModule = module {

    viewModel {
        ShoppingViewModel(getShoppingListUseCase = get())
    }

    factory {
        GetShoppingListUseCase(productsRepository = get())
    }

    factory {
        AddProductToSLUseCase(productsRepository = get())
    }

    factory {
        UpdateProductUseCase(productsRepository = get())
    }

    single<ShoppingListDataSource> {
        RoomShoppingListDataSource(productDBData = get())
    }

    single {
        ProductDatabase.getDatabase(context = androidContext())
    }
}