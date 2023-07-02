package com.example.shopsy.di

import drawable.api.GalleryAPI
import drawable.api.ProductAPI
import com.example.shopsy.utils.Constant.BASE_URL_GALLERY
import com.example.shopsy.utils.Constant.BASE_URL_PRODUCT
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class NetworkModule {

    @Singleton
    @Provides
    fun providesRetrofit(): Retrofit.Builder {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
    }

    @Singleton
    @Provides
    fun providesProductAPI(retrofitBuilder: Retrofit.Builder): ProductAPI {
        return retrofitBuilder.baseUrl(BASE_URL_PRODUCT).build().create(ProductAPI::class.java)
    }

    @Singleton
    @Provides
    fun providesGalleryAPI(retrofitBuilder: Retrofit.Builder): GalleryAPI {
        return retrofitBuilder.baseUrl(BASE_URL_GALLERY).build().create(GalleryAPI::class.java)
    }
}