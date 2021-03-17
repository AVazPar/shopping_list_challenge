package com.cabifychallenge.angelesvazquez

import org.mockito.InjectMocks

abstract class UnitTest {

    val injectMocks = InjectMocksRule.create(this@UnitTest)

}