package com.example.shopsy.ui.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.shopsy.databinding.RvSingleProductItemBinding
import com.example.shopsy.model.SingleProduct
import com.example.shopsy.ui.home.HomeFragment.Companion.TAG
import com.example.shopsy.utils.diffCallbacks.ProductDiffCallback

class ProductAdapter(private val productClickListener: ProductClickListener) :
    ListAdapter<SingleProduct, ProductAdapter.ViewHolder>(ProductDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = RvSingleProductItemBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item, productClickListener)
    }

    inner class ViewHolder(private val binding: RvSingleProductItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: SingleProduct, action: ProductClickListener) {

            Log.d(TAG, "adapter -> ${item.brand}")
            binding.product = item
            binding.executePendingBindings()
            itemView.setOnClickListener {
                action.onProductClickListener(item)
            }
        }

    }

}

interface ProductClickListener {
    fun onProductClickListener(product: SingleProduct)
}
