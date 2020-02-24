package com.cabifychallenge.angelesvazquez.data.retrofit

import com.cabifychallenge.angelesvazquez.common.EitherClass
import retrofit2.Call

/**
 * Author: ÁngelesVP
 *
 * It's a generic class that we use in RetrofitCallExecutor.
 * I declare that an any type function can request with a Call of any type of element, should be
 * return a Either class (String for error and any type of response of success).
 *
 * Inside the method only I call execute method of the Retrofit.
 */
object RetrofitCallExecutor {

    fun <T: Any> request(call: Call<T>): EitherClass<String, T?> =
        try {
            val response = call.execute()
            when (response.isSuccessful) {
                true -> EitherClass.Success(response.body())
                false -> EitherClass.Error(response.code().toString())
            }
        } catch (exception: Exception) {
            EitherClass.Error(exception.message.toString())
        }
}