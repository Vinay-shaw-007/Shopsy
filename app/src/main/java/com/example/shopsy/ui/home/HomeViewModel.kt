package com.example.shopsy.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shopsy.repository.ProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val repository: ProductRepository) : ViewModel() {

    val allProduct = repository.allProduct

    fun getAllProducts() {
        viewModelScope.launch {
            repository.getAllProducts()
        }
    }


}