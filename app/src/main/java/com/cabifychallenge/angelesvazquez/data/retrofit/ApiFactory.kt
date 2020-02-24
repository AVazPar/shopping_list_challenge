package com.cabifychallenge.angelesvazquez.data.retrofit

/**
 * Author: ÁngelesVP
 *
 * We call the retrofit object to build it pass thought the base URL that you provide me.
 */
object ApiFactory{

    val dataProducts : ApiService = RetrofitFactory.retrofit("https://api.myjson.com/")
        .create(ApiService::class.java)
}