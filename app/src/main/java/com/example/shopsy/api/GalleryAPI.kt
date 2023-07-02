package drawable.api

import com.example.shopsy.model.AllGalleryResponse
import com.example.shopsy.model.SingleGalleryResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface GalleryAPI {

    @GET("photos/")
    suspend fun getAllPhotos(): Response<AllGalleryResponse>

    @GET("photos/{id}")
    suspend fun getSinglePhoto(@Path("id") id: Int): Response<SingleGalleryResponse>
}