package com.cabifychallenge.angelesvazquez.framework
import com.cabifychallenge.angelesvazquez.data.retrofit.ApiFactory
import com.cabifychallenge.angelesvazquez.data.retrofit.entities.ProductResponse
import retrofit2.Call

/**
 * Author: ÁngelesVP
 * This datasource implementation, I've use the override method to return the call type (GET in that case),
 * the response type and the last part of the url.
 */
class RetrofitProductsDataSource : ProductsDataSource {

    private val service by lazy { ApiFactory.dataProducts }

    override fun getAllProducts(): Call<ProductResponse> {
        return service.getAllProducts()
    }
}