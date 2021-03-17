package com.cabifychallenge.angelesvazquez.ui

import com.cabifychallenge.angelesvazquez.AndroidTest
import com.cabifychallenge.angelesvazquez.common.EitherClass
import com.cabifychallenge.angelesvazquez.ui.home.entities.Product
import com.cabifychallenge.angelesvazquez.ui.shopping.ShoppingViewModel
import com.cabifychallenge.angelesvazquez.usecases.GetShoppingListUseCase
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.BDDMockito.given
import org.mockito.Mock

class ShoppingViewModelTest : AndroidTest() {

    private lateinit var viewModel: ShoppingViewModel

    @Mock
    private lateinit var getShoppingListUseCase: GetShoppingListUseCase

    @Before
    fun setUp(){
        viewModel = ShoppingViewModel(getShoppingListUseCase = getShoppingListUseCase)
    }

    @Test
    fun `viewmodel test get shopping list`(){
        val product = Product(code = "", description = "", amount = Float.MAX_VALUE, amount_per_unit = Float.MAX_VALUE, discount = "", discountValue = Float.MAX_VALUE, currency = "", quantiy = "", image = 0)
        val response : List<Product> = arrayListOf(product).toList()

        given(runBlocking { getShoppingListUseCase.run() }).willReturn(EitherClass.Success(response))
        runBlocking { viewModel.getShoppingList() }
    }
}