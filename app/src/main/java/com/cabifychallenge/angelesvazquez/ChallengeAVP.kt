package com.cabifychallenge.angelesvazquez

import android.app.Application
import com.cabifychallenge.angelesvazquez.di.injectDI

/**
 * Author: ÁngelesVP
 *
 * I've created this custom application class because I need inject Koin into it.
 *
 * I would like to write about the architecture of this application, I've used MVVM (Model-View-ViewModel).
 * For that reason the package structure is compose by:
 * 1) ui (view and view models)
 * 2) usecase (interactor)
 * 3) data (repository and build Retrofit and Room)
 * 4) framework (DataSource and Implementation of DataSource)
 *
 * Why I've use Retrofit?
 * Because it's the best option to do API calls in Kotlin.
 *
 * Why I've use Room?
 * Because it's a completed internal datasource and in the future we can integrate it with reactive
 * coding (RxKotlin or Flow).
 */
class ChallengeAVP : Application(){

    override fun onCreate() {
        super.onCreate()
        injectDI()
    }
}
