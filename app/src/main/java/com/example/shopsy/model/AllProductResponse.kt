package com.example.shopsy.model

data class AllProductResponse(
    val limit: Int,
    val products: List<SingleProduct>,
    val skip: Int,
    val total: Int
)