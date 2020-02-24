package com.cabifychallenge.angelesvazquez.common


/**
 * Author: ÁngelesVP
 * It's a sealed class to get an error or success response from the datasource to viewmodel.
 */
sealed class EitherClass<out E, out S> {
    data class Error<out E>(val e: E) : EitherClass<E, Nothing>()
    data class Success<out S>(val s: S) : EitherClass<Nothing, S>()
}