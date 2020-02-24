package com.cabifychallenge.angelesvazquez.data

import com.cabifychallenge.angelesvazquez.common.utility.ProductExtraData
import com.cabifychallenge.angelesvazquez.data.retrofit.entities.ProductResponse
import com.cabifychallenge.angelesvazquez.data.room.ProductRoomData
import com.cabifychallenge.angelesvazquez.ui.home.entities.Product

/**
 * Author: ÁngelesVP
 * It's just three extension methods from Kotlin language where I mapped from one object to another one.
 */
fun Product.toRoom(): ProductRoomData {
    return ProductRoomData(
        code = this.code!!,
        description = this.description!!,
        amount = this.amount!!,
        quantiy = this.quantiy!!,
        currency = this.currency!!,
        image = this.image!!,
        amountUnit = this.amount_per_unit!!,
        discountValue = this.discountValue!!,
        discount = this.discount!!)
}

fun List<ProductRoomData>.toRepository() : List<Product>{
    var list: MutableList<Product> = ArrayList()
    for(i in this){
        list.add(Product(
            code = i.code,
            description = i.description,
            amount = i.amount,
            amount_per_unit = i.amountUnit,
            quantiy = i.quantiy,
            currency = i.currency,
            image = i.image,
            discountValue = i.discountValue,
            discount = i.discount))
    }
    return list
}

fun ProductResponse.toView(): List<Product> {
    val list: MutableList<Product> = arrayListOf()
    for (product in this.products!!.iterator()) {
        list.add(
            Product(
                code = product.code,
                description = product.name,
                amount = product.price,
                amount_per_unit = product.price,
                currency = "€",
                image = ProductExtraData.getImage(product.code),
                quantiy = "1",
                discount = ProductExtraData.getDiscount(product.code),
                discountValue = 0F
            )
        )
    }
    return list
}