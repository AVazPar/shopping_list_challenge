package com.cabifychallenge.angelesvazquez.data.room

import androidx.room.*

/**
 * Author: ÁngelesVP
 *
 * This interface it's needed for Room DataBase.
 * Here you can see the methods that I use in this application:
 * Insert, update a single product, get and clean all products.
 */
@Dao
interface ProductDAO {

    @Query("SELECT * from product_table ORDER BY code ASC")
    fun getAlphabetizedWords(): List<ProductRoomData>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(product: ProductRoomData)

    @Update(onConflict = OnConflictStrategy.IGNORE)
    fun update(product: ProductRoomData)

    @Query("DELETE FROM product_table")
    fun deleteAll()
}