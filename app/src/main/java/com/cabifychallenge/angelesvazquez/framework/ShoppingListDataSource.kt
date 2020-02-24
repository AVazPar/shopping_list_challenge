package com.cabifychallenge.angelesvazquez.framework

import com.cabifychallenge.angelesvazquez.common.EitherClass
import com.cabifychallenge.angelesvazquez.ui.home.entities.Product
import retrofit2.Call

/**
 * Author: ÁngelesVP
 * This interface it's for manage the products to Room datasource.
 */
interface ShoppingListDataSource {

    fun getAllShoppingList() : EitherClass<String, List<Product>>

    fun addProductToShoppingList(product: Product): EitherClass<String, Unit>

    fun updateProduct(product: Product): EitherClass<String, Unit>

    fun deleteAll(): EitherClass<String, Unit>
}