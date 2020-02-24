package com.cabifychallenge.angelesvazquez.data.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Author: ÁngelesVP
 *
 * It's only the method where I build the retrofit object with the URL that you provided me.
 */
object RetrofitFactory {

    fun retrofit(baseUrl: String): Retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}