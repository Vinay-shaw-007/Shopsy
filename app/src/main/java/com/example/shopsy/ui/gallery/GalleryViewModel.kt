package com.example.shopsy.ui.gallery

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shopsy.repository.GalleryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GalleryViewModel @Inject constructor(private val repository: GalleryRepository) : ViewModel() {

    val allPhotos = repository.allPhotos

    fun getAllGalleryPhotos() {
        viewModelScope.launch {
            repository.getAllGalleryPhotos()
        }
    }


}