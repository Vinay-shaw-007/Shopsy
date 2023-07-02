package com.example.shopsy.ui.galleryDetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shopsy.repository.GalleryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GalleryDetailViewModel @Inject constructor(private val repository: GalleryRepository) :
    ViewModel() {

    val singlePhoto = repository.singlePhoto

    fun getSingleGalleryPhotos(id: Int) {
        viewModelScope.launch {
            repository.getSingleGalleryPhotos(id)
        }
    }
}