package com.cabifychallenge.angelesvazquez.usecases

import com.cabifychallenge.angelesvazquez.common.EitherClass
import com.cabifychallenge.angelesvazquez.data.ProductsRepository

/**
 * Author: ÁngelesVP
 *
 * This UseCase doesn't have a strong functionality in this application. But we can create
 * a base UseCase abstract class to use couroutines.
 *
 * In this application I don't use it because I choose viewmodelscope coroutines.
 */
class DeleteAllProductsUseCase(private val productsRepository: ProductsRepository) {
    fun run(): EitherClass<String, Unit?> {
        return productsRepository.deleteAllProducts()
    }
}
