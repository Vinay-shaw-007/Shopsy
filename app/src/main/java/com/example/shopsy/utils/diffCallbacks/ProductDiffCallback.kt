package com.example.shopsy.utils.diffCallbacks

import androidx.recyclerview.widget.DiffUtil
import com.example.shopsy.model.SingleProduct

class ProductDiffCallback : DiffUtil.ItemCallback<SingleProduct>() {
    override fun areItemsTheSame(oldItem: SingleProduct, newItem: SingleProduct): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: SingleProduct, newItem: SingleProduct): Boolean {
        return oldItem == newItem
    }
}
