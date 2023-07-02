package com.example.shopsy

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class ShopsyApplication: Application() {

    private var dataLoadedForHomePage = false
    private var dataLoadedForGalleryPage = false

    fun isDataLoadedForHomePage(): Boolean {
        return dataLoadedForHomePage
    }

    fun setDataLoadedForHomePage(dataLoaded: Boolean) {
        this.dataLoadedForHomePage = dataLoaded
    }

    fun isDataLoadedForGalleryPage(): Boolean {
        return dataLoadedForGalleryPage
    }

    fun setDataLoadedForGalleryPage(dataLoaded: Boolean) {
        this.dataLoadedForGalleryPage = dataLoaded
    }
}