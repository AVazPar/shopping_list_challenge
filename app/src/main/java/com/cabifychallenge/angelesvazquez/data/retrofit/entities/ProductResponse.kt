package com.cabifychallenge.angelesvazquez.data.retrofit.entities

import com.google.gson.annotations.SerializedName

/**
 * Author: ÁngelesVP
 * It's the element from the Retrofit response.
 */
data class ProductResponse(
    @SerializedName("products")
    val products: List<ProductResponseDetail>? = null
)