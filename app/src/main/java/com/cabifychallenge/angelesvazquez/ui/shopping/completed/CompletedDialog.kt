package com.cabifychallenge.angelesvazquez.ui.shopping.completed

import com.cabifychallenge.angelesvazquez.R
import kotlinx.android.synthetic.main.dialog_completed.*
import android.os.Bundle
import com.cabifychallenge.angelesvazquez.common.base.BaseDialogFragment


/**
 * Author: ÁngelesVP
 *
 * This is the only dialog fragment, I've used a BaseDialogFragment class where I add the viewModel into it.
 * We can use this viewModel in this dialog fragment, in that case CompeletedDialogViewModel.
 *
 * In onCreateView override method we set the layout XML, and onActivityCreated override method I
 * implement onclicklistener of the dismiss button, to dismiss the dialog and clean the shopping list.
 */
class CompletedDialog : BaseDialogFragment<CompletedDialogViewModel>(CompletedDialogViewModel::class) {

    override fun layoutId(): Int = R.layout.dialog_completed

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        bt_dismiss.setOnClickListener {
            viewModel.cleanShoppingList()
            dismiss()
        }
    }
}