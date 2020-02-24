package com.cabifychallenge.angelesvazquez.ui.home.adapter

import android.content.Context
import com.cabifychallenge.angelesvazquez.R
import com.cabifychallenge.angelesvazquez.ui.home.entities.Product
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.content.res.AppCompatResources.getDrawable
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

/**
 * Author: ÁngelesVP
 *
 * Custom adapter
 */
class ProductAdapter (private val mDataSet: MutableList<Product>, private val context: Context?) : RecyclerView.Adapter<ProductAdapter.ViewHolder>() {

    /**
     * Constants code - MUG, TSHIRT, VOUCHER
     */
    companion object{
        const val MUG_CODE : String = "MUG"
        const val TSHIRT_CODE : String = "TSHIRT"
        const val VOUCHER_CODE : String = "VOUCHER"
    }

    var onItemClick: ((Product) -> Unit)? = null


    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_product, viewGroup, false)

        return ViewHolder(v)
    }

    /**
     * Get the length (size) of the array
     */
    override fun getItemCount(): Int = mDataSet.size

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.bind(mDataSet[position])
    }

    /**
     * We use our DiffUtils class to update our data (product list)
     */
    fun onNewData(newData: List<Product>) {
        val diffResult = DiffUtil.calculateDiff(
            ProductsDiffUtilsCallback(
                newList = newData,
                oldList = mDataSet
            )
        )
        this.mDataSet.clear()
        this.mDataSet.addAll(newData)

        diffResult.dispatchUpdatesTo(this)
    }

    /**
     * ViewHolder class where we join the view elements with data
     */
    inner class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        init {
            v.setOnClickListener {
                Log.d("Product list", "Element $adapterPosition clicked.")
                onItemClick?.invoke(mDataSet[adapterPosition])
            }
        }

        fun bind(data: Product) {
            itemView.findViewById<TextView>(R.id.tv_product_title)?.text = data.description
            itemView.findViewById<TextView>(R.id.tv_product_price)?.text = String.format("%.2f", data.amount).plus(data.currency)
            itemView.findViewById<ImageView>(R.id.iv_product_image)?.background = getDrawable(context!!, data.image!!)
            itemView.findViewById<TextView>(R.id.tv_product_discount)?.text = data.discount
            itemView.findViewById<TextView>(R.id.tv_product_quantity)?.text = "Quantity: ".plus(data.quantiy)
        }
    }
}
