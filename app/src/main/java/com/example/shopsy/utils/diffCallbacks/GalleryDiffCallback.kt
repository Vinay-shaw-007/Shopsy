package com.example.shopsy.utils.diffCallbacks

import androidx.recyclerview.widget.DiffUtil
import com.example.shopsy.model.SingleGalleryResponse

class GalleryDiffCallback : DiffUtil.ItemCallback<SingleGalleryResponse>() {
    override fun areItemsTheSame(oldItem: SingleGalleryResponse, newItem: SingleGalleryResponse): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: SingleGalleryResponse, newItem: SingleGalleryResponse): Boolean {
        return oldItem == newItem
    }
}