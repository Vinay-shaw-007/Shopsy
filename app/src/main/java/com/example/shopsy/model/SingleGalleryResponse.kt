package com.example.shopsy.model

data class SingleGalleryResponse(
    val albumId: Int,
    val id: Int,
    val thumbnailUrl: String,
    val title: String,
    val url: String
)