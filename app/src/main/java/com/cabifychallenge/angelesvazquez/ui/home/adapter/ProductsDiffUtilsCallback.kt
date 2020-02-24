package com.cabifychallenge.angelesvazquez.ui.home.adapter

import com.cabifychallenge.angelesvazquez.ui.home.entities.Product
import androidx.recyclerview.widget.DiffUtil

/**
 * Author: ÁngelesVP
 *
 * DiffUtils class to update the new product list into adapter.
 */
class ProductsDiffUtilsCallback(private val newList: List<Product>, private val oldList: List<Product>) :
    DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(
        oldItemPosition: Int,
        newItemPosition: Int
    ): Boolean = oldList[oldItemPosition] == newList[newItemPosition]

    override fun areContentsTheSame(
        oldItemPosition: Int,
        newItemPosition: Int
    ): Boolean = oldList[oldItemPosition].code == newList[newItemPosition].code
}