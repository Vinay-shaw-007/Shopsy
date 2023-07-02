package com.example.shopsy.ui.productDetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shopsy.repository.ProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductDetailViewModel @Inject constructor(private val repository: ProductRepository) :
    ViewModel() {

    val singleProduct = repository.singleProduct

    fun getSingleProduct(id: Int) {
        viewModelScope.launch {
            repository.getSingleProduct(id)
        }
    }
}