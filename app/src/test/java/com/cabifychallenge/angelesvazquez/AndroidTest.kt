package com.cabifychallenge.angelesvazquez

import android.app.Application
import android.content.Context
import com.cabifychallenge.angelesvazquez.common.base.BaseActivity
import org.mockito.Mockito.mock
import org.robolectric.RuntimeEnvironment


abstract class AndroidTest {

    val injectMocks = InjectMocksRule.create(this@AndroidTest)
    fun context() : Context = RuntimeEnvironment.application
    fun activityContext(): Context = mock(BaseActivity::class.java)

    internal class ApplicationStub : Application()
}