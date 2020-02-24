package com.cabifychallenge.angelesvazquez.data.retrofit.entities

import com.google.gson.annotations.SerializedName

/**
 * Author: ÁngelesVP
 * It's the detail of the element from the Retrofit response.
 */
data class ProductResponseDetail(

    @SerializedName("code")
    val code: String? = null,

    @SerializedName("name")
    val name: String? = null,

    @SerializedName("price")
    val price: Float? = null
)