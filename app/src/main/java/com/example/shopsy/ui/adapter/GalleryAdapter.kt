package com.example.shopsy.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.shopsy.databinding.RvSingleGalleryItemBinding
import com.example.shopsy.model.SingleGalleryResponse
import com.example.shopsy.utils.diffCallbacks.GalleryDiffCallback

class GalleryAdapter(private val galleryClickListener: GalleryClickListener) :
    ListAdapter<SingleGalleryResponse, GalleryAdapter.ViewHolder>(GalleryDiffCallback()) {

    inner class ViewHolder(private val binding: RvSingleGalleryItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: SingleGalleryResponse, action: GalleryClickListener) {

            binding.galleryItem = item
            binding.executePendingBindings()
            itemView.setOnClickListener {
                action.onGalleryClickListener(item)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = RvSingleGalleryItemBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item, galleryClickListener)
    }
}

interface GalleryClickListener {
    fun onGalleryClickListener(galleryItem: SingleGalleryResponse)
}