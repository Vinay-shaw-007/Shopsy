package com.example.shopsy.repository

import drawable.api.GalleryAPI
import com.example.shopsy.model.AllGalleryResponse
import com.example.shopsy.model.SingleGalleryResponse
import com.example.shopsy.utils.NetworkResult
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

class GalleryRepository @Inject constructor(private val galleryAPI: GalleryAPI) {

    private var _allPhotos = MutableStateFlow<NetworkResult<AllGalleryResponse>>(NetworkResult.Loading())
    val allPhotos = _allPhotos.asStateFlow()

    private var _singlePhoto = MutableStateFlow<NetworkResult<SingleGalleryResponse>>(NetworkResult.Loading())
    val singlePhoto = _singlePhoto.asStateFlow()

    suspend fun getAllGalleryPhotos() {
        try {
            val response = galleryAPI.getAllPhotos()

            if (response.isSuccessful && response.body() != null) {
                _allPhotos.value = NetworkResult.Success(response.body()!!)
            } else if (response.errorBody() != null) {
                _allPhotos.value = NetworkResult.Error("Something went wrong.")
            } else {
                _allPhotos.value = NetworkResult.Error("Something went wrong.")
            }
        }catch (e: Exception) {
            _allPhotos.value = NetworkResult.Error(e.message)

        }
    }

    suspend fun getSingleGalleryPhotos(id: Int) {
        try {
            val response = galleryAPI.getSinglePhoto(id)

            if (response.isSuccessful && response.body() != null) {
                _singlePhoto.value = NetworkResult.Success(response.body()!!)
            } else if (response.errorBody() != null) {
                _singlePhoto.value = NetworkResult.Error("Something went wrong.")
            } else {
                _singlePhoto.value = NetworkResult.Error("Something went wrong.")
            }
        }catch (e: Exception) {
            _singlePhoto.value = NetworkResult.Error(e.message)
        }
    }
}