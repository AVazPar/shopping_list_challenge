package com.cabifychallenge.angelesvazquez.ui.home.entities

/**
 * Author: ÁngelesVP
 *
 * Product custom object, that I use like base object into this application
 */
data class Product(
    val code: String?,
    val description: String?,
    var amount: Float?,
    var amount_per_unit: Float?,
    val currency: String?,
    var discount: String?,
    var discountValue: Float?,
    var quantiy: String?,
    val image: Int?
)