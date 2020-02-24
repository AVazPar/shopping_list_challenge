package com.cabifychallenge.angelesvazquez.di

import com.cabifychallenge.angelesvazquez.ChallengeAVP
import com.cabifychallenge.angelesvazquez.ui.MainActivityViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module

/**
 * Author: ÁngelesVP
 * Dependency injection it's one of the S.O.L.I.D values, and for that reason I use Koin.
 */
fun ChallengeAVP.injectDI() {
    startKoin {
        androidLogger()
        androidContext(this@injectDI)
        modules(
            listOf(
                mainModule,
                homeModule,
                shoppingModule,
                productDetailModule
            )
        )
    }
}

/**
 * Author: ÁngelesVP
 * I used to create several modules for Koin
 */
val mainModule = module {
    viewModel {
        MainActivityViewModel()
    }
}