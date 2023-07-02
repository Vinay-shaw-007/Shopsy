package com.example.shopsy.utils

import android.app.Activity
import android.view.View
import com.example.shopsy.R
import com.google.android.material.bottomnavigation.BottomNavigationView

object Constant {
    const val BASE_URL_PRODUCT = "https://dummyjson.com/"
    const val BASE_URL_GALLERY = "https://jsonplaceholder.typicode.com/"

    fun hideBottomNavigation(activity: Activity) {
        val navView: BottomNavigationView? = activity.findViewById(R.id.nav_view)
        navView?.visibility = View.GONE
    }

    fun showBottomNavigation(activity: Activity) {
        val navView: BottomNavigationView? = activity.findViewById(R.id.nav_view)
        navView?.visibility = View.VISIBLE
    }
}