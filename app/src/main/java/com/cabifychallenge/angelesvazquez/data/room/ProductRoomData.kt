package com.cabifychallenge.angelesvazquez.data.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Author: ÁngelesVP
 *
 * This dataclass it's the object of each item of the Room Database.
 * I've added several fields of this object.
 */
@Entity(tableName = "product_table")
data class ProductRoomData(

    @PrimaryKey @ColumnInfo(name = "code")
    val code: String,

    @ColumnInfo(name = "description")
    val description: String?,

    @ColumnInfo(name = "amount")
    val amount: Float?,

    @ColumnInfo(name = "amountUnit")
    val amountUnit: Float?,

    @ColumnInfo(name = "currency")
    val currency: String?,

    @ColumnInfo(name = "discountValue")
    val discountValue: Float?,

    @ColumnInfo(name = "discount")
    val discount: String?,

    @ColumnInfo(name = "quantiy")
    var quantiy: String?,

    @ColumnInfo(name = "image")
    val image: Int?
)