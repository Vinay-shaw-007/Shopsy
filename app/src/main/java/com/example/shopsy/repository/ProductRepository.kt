package com.example.shopsy.repository

import drawable.api.ProductAPI
import com.example.shopsy.model.AllProductResponse
import com.example.shopsy.model.SingleProduct
import com.example.shopsy.utils.NetworkResult
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

class ProductRepository @Inject constructor(private val productAPI: ProductAPI){

    private var _allProducts = MutableStateFlow<NetworkResult<AllProductResponse>>(NetworkResult.Loading())
    val allProduct = _allProducts.asStateFlow()

    private var _singleProducts = MutableStateFlow<NetworkResult<SingleProduct>>(NetworkResult.Loading())
    val singleProduct = _singleProducts.asStateFlow()



    suspend fun getAllProducts() {
        try {
            val response = productAPI.getAllProducts()

            if (response.isSuccessful && response.body() != null) {
                _allProducts.value = NetworkResult.Success(response.body()!!)
            } else if (response.errorBody() != null) {
                _allProducts.value = NetworkResult.Error("Something went wrong.")
            } else {
                _allProducts.value = NetworkResult.Error("Something went wrong.")
            }
        }catch (e: Exception) {
            _allProducts.value = NetworkResult.Error(e.message)

        }
    }

    suspend fun getSingleProduct(id: Int) {
        try {
            val response = productAPI.getSingleProducts(id)

            if (response.isSuccessful && response.body() != null) {
                _singleProducts.value = NetworkResult.Success(response.body()!!)
            } else if (response.errorBody() != null) {
                _singleProducts.value = NetworkResult.Error("Something went wrong")
            } else {
                _singleProducts.value = NetworkResult.Error("Something went wrong")
            }
        }catch (e: Exception) {
            _singleProducts.value = NetworkResult.Error(e.message)
        }
    }
}