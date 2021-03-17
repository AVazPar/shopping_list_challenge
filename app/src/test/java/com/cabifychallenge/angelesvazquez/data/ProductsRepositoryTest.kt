package com.cabifychallenge.angelesvazquez.data

import com.cabifychallenge.angelesvazquez.UnitTest
import com.cabifychallenge.angelesvazquez.common.EitherClass
import com.cabifychallenge.angelesvazquez.data.retrofit.entities.ProductResponse
import com.cabifychallenge.angelesvazquez.framework.RetrofitProductsDataSource
import com.cabifychallenge.angelesvazquez.framework.RoomShoppingListDataSource
import org.amshove.kluent.shouldEqual
import org.junit.Before
import org.junit.Test
import org.mockito.BDDMockito.given
import org.mockito.Mock
import org.mockito.Mockito
import retrofit2.Call
import retrofit2.Response

class ProductsRepositoryTest : UnitTest() {

    private lateinit var productsRepository: ProductsRepository

    @Mock
    private lateinit var retrofitProductsDataSource: RetrofitProductsDataSource

    @Mock
    private lateinit var roomShoppingListDataSource: RoomShoppingListDataSource


    @Mock
    private lateinit var retrofitProductCall: Call<ProductResponse>

    @Mock
    private lateinit var retrofitProductCallResponse: Response<ProductResponse>

    @Before
    fun setUp(){
        productsRepository = ProductsRepository(productsDataSource = retrofitProductsDataSource, shoppingListDataSource = roomShoppingListDataSource)
    }

    @Test
    fun `get all products from Retrofit - Success `() {
        given(retrofitProductCallResponse.isSuccessful).willReturn(true)
        given(retrofitProductCall.execute()).willReturn(retrofitProductCallResponse)
        given(retrofitProductsDataSource.getAllProducts()).willReturn(retrofitProductCall)

        val getAllProducts = retrofitProductsDataSource.getAllProducts()
        getAllProducts shouldEqual EitherClass.Success(null)

        Mockito.verify(retrofitProductsDataSource.getAllProducts())
    }

    @Test
    fun `get all products from Retrofit - 500 Error `() {
        given(retrofitProductCallResponse.code()).willReturn(500)
        given(retrofitProductCall.execute()).willReturn(retrofitProductCallResponse)
        given(retrofitProductsDataSource.getAllProducts()).willReturn(retrofitProductCall)

        val getAllProducts = retrofitProductsDataSource.getAllProducts()
        getAllProducts shouldEqual EitherClass.Error("Esto es un error")

        Mockito.verify(retrofitProductsDataSource.getAllProducts())
    }
}