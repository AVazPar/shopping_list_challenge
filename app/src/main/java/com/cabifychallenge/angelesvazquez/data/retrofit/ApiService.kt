package com.cabifychallenge.angelesvazquez.data.retrofit

import com.cabifychallenge.angelesvazquez.data.retrofit.entities.ProductResponse
import retrofit2.Call
import retrofit2.http.GET

/**
 * Author: ÁngelesVP
 *
 * This interface it's to set the call type, the response and the last part of the URL.
 *
 * For example:
 * Base URL = "http://IP:Port"
 * Rest of URL = "/service/01"
 *
 * Call type: GET, POST, PATCH...
 */
interface ApiService{

    @GET("/bins/4bwec")
    fun getAllProducts(): Call<ProductResponse>
}