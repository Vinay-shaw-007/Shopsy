package drawable.api

import com.example.shopsy.model.SingleProduct
import com.example.shopsy.model.AllProductResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ProductAPI {

    @GET("products/")
    suspend fun getAllProducts(): Response<AllProductResponse>

    @GET("products/{id}")
    suspend fun getSingleProducts(@Path("id") id: Int): Response<SingleProduct>

}