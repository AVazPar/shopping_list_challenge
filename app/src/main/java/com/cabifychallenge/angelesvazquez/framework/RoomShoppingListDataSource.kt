package com.cabifychallenge.angelesvazquez.framework

import com.cabifychallenge.angelesvazquez.common.EitherClass
import com.cabifychallenge.angelesvazquez.data.room.ProductDatabase
import com.cabifychallenge.angelesvazquez.data.room.ProductRoomData
import com.cabifychallenge.angelesvazquez.data.toRepository
import com.cabifychallenge.angelesvazquez.data.toRoom
import com.cabifychallenge.angelesvazquez.ui.home.entities.Product

/**
 * Author: ÁngelesVP
 * These methods are for manage the product in Room Database.
 */
class RoomShoppingListDataSource(private val productDBData: ProductDatabase) : ShoppingListDataSource {

    /**
     * Get all products of the shopping list
     */
    override fun getAllShoppingList(): EitherClass<String, List<Product>> {
        val productDAO = productDBData.productDAO()
        val allProducts: List<ProductRoomData> = productDAO.getAlphabetizedWords()

        return if (allProducts.isNotEmpty()) EitherClass.Success(allProducts.toRepository()) else EitherClass.Error("Esto es un error")
    }

    /**
     * Add product of the shopping list
     */
    override fun addProductToShoppingList(product: Product): EitherClass<String, Unit> {
        val productDAO = productDBData.productDAO()
        productDAO.insert(product = product.toRoom())

        return EitherClass.Error("")
    }

    /**
     * Update product of the shopping list
     */
    override fun updateProduct(product: Product): EitherClass<String, Unit> {
        val productDAO = productDBData.productDAO()
        productDAO.update(product = product.toRoom())

        return EitherClass.Error("")
    }

    /**
     * Delete all products of the shopping list
     */
    override fun deleteAll(): EitherClass<String, Unit> {
        val productDAO = productDBData.productDAO()
        productDAO.deleteAll()

        return EitherClass.Error("")    }
}