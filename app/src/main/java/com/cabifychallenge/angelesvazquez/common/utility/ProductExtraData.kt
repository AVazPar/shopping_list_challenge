package com.cabifychallenge.angelesvazquez.common.utility

import com.cabifychallenge.angelesvazquez.R
import com.cabifychallenge.angelesvazquez.ui.home.adapter.ProductAdapter

/**
 * Author: ÁngelesVP
 * I've created this object class, because I think it's important add the dicount and image of the product.
 */
object ProductExtraData {

    /**
     * Author: ÁngelesVP
     * It's only the text discount from the product. I think it should be inform if the product has a discout or not.
     */
    fun getDiscount(code: String?): String {
        when (code) {
            ProductAdapter.MUG_CODE -> {
                return ""
            }
            ProductAdapter.TSHIRT_CODE -> {
                return "Discounts on bulk purchases"
            }
            ProductAdapter.VOUCHER_CODE -> {
                return "Discount: 2-for-1 promotions"
            }
        }
        return ""
    }

    /**
     * Author: ÁngelesVP
     * The image associated to a product, it should be inform from the service. So I've added into our Product.
     */
    fun getImage(code: String?): Int {
        when (code) {
            ProductAdapter.MUG_CODE -> {
                return R.drawable.image_mug
            }
            ProductAdapter.TSHIRT_CODE -> {
                return R.drawable.image_tshirt
            }
            ProductAdapter.VOUCHER_CODE -> {
                return R.drawable.image_voucher
            }
        }
        return 0
    }
}