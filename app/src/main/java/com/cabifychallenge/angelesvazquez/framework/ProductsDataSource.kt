package com.cabifychallenge.angelesvazquez.framework

import com.cabifychallenge.angelesvazquez.data.retrofit.entities.ProductResponse
import retrofit2.Call

/**
 * Author: ÁngelesVP
 * This interface it's for get all products from retrofit datasource.
 */
interface ProductsDataSource {

    fun getAllProducts() : Call<ProductResponse>
}