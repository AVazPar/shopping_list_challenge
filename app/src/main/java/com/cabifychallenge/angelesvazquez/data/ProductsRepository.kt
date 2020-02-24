package com.cabifychallenge.angelesvazquez.data

import com.cabifychallenge.angelesvazquez.common.EitherClass
import com.cabifychallenge.angelesvazquez.data.retrofit.RetrofitCallExecutor
import com.cabifychallenge.angelesvazquez.data.retrofit.entities.ProductResponse
import com.cabifychallenge.angelesvazquez.framework.ProductsDataSource
import com.cabifychallenge.angelesvazquez.framework.ShoppingListDataSource
import com.cabifychallenge.angelesvazquez.ui.home.entities.Product

/**
 * Author: ÁngelesVP
 * It's the only repository that I use in that application.
 * It has both datasource interfaces and I use them to call their methods.
 *
 * All methods that I've created return an EitherClass. It's because maybe in the future
 * we can add or improve the error management.
 *
 * In this class I call to Retrofit Executor in the first method and the rest of all,
 * I call to Room data sources.
 */
class ProductsRepository(
    private val productsDataSource: ProductsDataSource,
    private val shoppingListDataSource: ShoppingListDataSource
) {

    fun getAllProductsFromServer(): EitherClass<String, ProductResponse?> {
        return RetrofitCallExecutor.request(productsDataSource.getAllProducts())
    }

    fun getShoppingList(): EitherClass<String, List<Product>>{
        return shoppingListDataSource.getAllShoppingList()
    }

    fun addProductToShoppingList(product: Product): EitherClass<String, Unit>{
        return shoppingListDataSource.addProductToShoppingList(product = product)
    }

    fun updateProduct(product: Product): EitherClass<String, Unit>{
        return shoppingListDataSource.updateProduct(product = product)
    }

    fun deleteAllProducts(): EitherClass<String, Unit>{
        return shoppingListDataSource.deleteAll()
    }
}