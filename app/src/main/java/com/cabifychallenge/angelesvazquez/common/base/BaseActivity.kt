package com.cabifychallenge.angelesvazquez.common.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import org.koin.android.viewmodel.ext.android.viewModel
import kotlin.reflect.KClass

/**
 * Author: AngelesVP
 * I try to create a Base Elements abstract class because it's a best practice use it.
 * In that class I've created a layoutId where you can set the layout of the activity directly,
 * and I use it in onCreate method.
 * */
abstract class BaseActivity<VM : ViewModel>(clazz: KClass<VM>) : AppCompatActivity(){

    @LayoutRes
    protected abstract fun layoutId(): Int

    val viewModel: VM by viewModel(clazz)

    override fun onCreate(savedInstanceState: Bundle?)  {
        super.onCreate(savedInstanceState)
        setContentView(layoutId())
    }
}