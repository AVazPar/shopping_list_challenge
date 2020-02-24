package com.cabifychallenge.angelesvazquez.usecases

import com.cabifychallenge.angelesvazquez.common.EitherClass
import com.cabifychallenge.angelesvazquez.data.ProductsRepository
import com.cabifychallenge.angelesvazquez.ui.home.entities.Product

/**
 * Author: ÁngelesVP
 *
 * This UseCase doesn't have a strong functionality in this application. But we can create
 * a base UseCase abstract class to use couroutines.
 *
 * In this application I don't use it because I choose viewmodelscope coroutines.
 */
class UpdateProductUseCase(private val productsRepository: ProductsRepository) {
    fun run(param: Product): EitherClass<String, Unit?> {
        return productsRepository.updateProduct(product = param)
    }
}