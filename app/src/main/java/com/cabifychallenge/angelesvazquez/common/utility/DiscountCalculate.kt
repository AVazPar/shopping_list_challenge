package com.cabifychallenge.angelesvazquez.common.utility

import com.cabifychallenge.angelesvazquez.ui.home.adapter.ProductAdapter
import com.cabifychallenge.angelesvazquez.ui.home.entities.Product

/**
 * Author: ÁngelesVP
 * I've created a custom object to this utility methods.
 */
data class DiscountPrices(
    val last_price : Float,
    val discount : Float,
    val real_price : Float,
    val currency : String
)

/**
 * Author: ÁngelesVP
 * I've chosen this option to calculate the discount request, because I think this solution it's okay
 * at the moment. In the future we can create a service where we can obtain the discount, the real price
 * and the last price and set in the view.
 *
 * This logic should be in a service where we can obtain the discount and prices of the offers.
 */
object DiscountCalculate {

    /**
     * Author: ÁngelesVP
     * Get the discount given a amount, quantity and code of the product.
     */
    fun getDiscountItem(amount: Float, quantity: Int, code: String): Float {
        var discount = 0F
        when(code){
            ProductAdapter.VOUCHER_CODE -> {
                if(quantity>=2){
                    discount += if(quantity%2 == 1){
                        val priceUnit = amount/quantity
                        amount - (priceUnit * (quantity - 1))
                    }else{
                        amount / 2
                    }
                }
            }
            ProductAdapter.TSHIRT_CODE -> {
                if(quantity >= 3)
                    discount += (1F * quantity)
            }
        }
        return discount
    }

    /**
     * Author: ÁngelesVP
     * Get our custom object from a list of products.
     */
    fun getTotalPrice(listProduct: List<Product>): DiscountPrices {
        var totalPrice = 0F
        var currency = ""
        var discount = 0F
        for (i in listProduct){
            totalPrice += (i.amount_per_unit!!.times(i.quantiy!!.toFloat()))
            currency = i.currency.toString()
            discount += i.discountValue!!
        }

        return DiscountPrices(
            last_price = totalPrice,
            discount = discount,
            real_price = totalPrice - discount,
            currency = currency)
    }
}