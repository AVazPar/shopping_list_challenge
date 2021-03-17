package com.cabifychallenge.angelesvazquez.usecase

import com.cabifychallenge.angelesvazquez.UnitTest
import com.cabifychallenge.angelesvazquez.common.EitherClass
import com.cabifychallenge.angelesvazquez.data.ProductsRepository
import com.cabifychallenge.angelesvazquez.ui.home.entities.Product
import com.cabifychallenge.angelesvazquez.usecases.AddProductToSLUseCase
import org.junit.Before
import org.junit.Test
import org.mockito.BDDMockito.*
import org.mockito.Mock
import org.mockito.Mockito

class AddProductToSLUseCaseTest : UnitTest() {
    private val product: Product = Product(code = "", description = "", amount = Float.MAX_VALUE, amount_per_unit = Float.MAX_VALUE, discount = "", discountValue = Float.MAX_VALUE, currency = "", quantiy = "", image = 0)
    private val addProductDataResponse = Unit

    private lateinit var addProductToSLUseCase: AddProductToSLUseCase

    @Mock
    private lateinit var productsRepository: ProductsRepository

    @Before
    fun setUp(){
        addProductToSLUseCase = AddProductToSLUseCase(productsRepository = productsRepository)
    }

    @Test
    fun `should add data to repository just one time`(){
        given(productsRepository.addProductToShoppingList(product = product)).willReturn(EitherClass.Success(addProductDataResponse))
        addProductToSLUseCase.run(product)

        Mockito.verify(productsRepository).addProductToShoppingList(product)
        verifyNoMoreInteractions(productsRepository)
    }
}