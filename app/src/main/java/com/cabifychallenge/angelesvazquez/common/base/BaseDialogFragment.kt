package com.cabifychallenge.angelesvazquez.common.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModel
import org.koin.android.viewmodel.ext.android.viewModel
import kotlin.reflect.KClass

/**
 * Author: AngelesVP
 * I try to create a Base Elements abstract class because it's a best practice use it.
 * In that class I've created a layoutId where you can set the layout of the dialog fragment directly,
 * and I use it in onCreate method.
 * */
abstract class BaseDialogFragment<VM : ViewModel>(clazz: KClass<VM>) : DialogFragment(){

    @LayoutRes
    protected abstract fun layoutId(): Int

    val viewModel: VM by viewModel(clazz)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(layoutId(), container, false)
    }
}