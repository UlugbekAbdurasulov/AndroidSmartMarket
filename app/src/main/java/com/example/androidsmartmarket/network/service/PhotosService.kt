package com.example.androidsmartmarket.network.service



import com.example.androidsmartmarket.model.Welcome
import com.example.androidsmartmarket.model.Welcomes
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

public interface PhotosService {

    companion object {
        private const val ACCESS_KEY = "Mozilla/5.0 (iPhone; CPU iPhone OS 10_3_1 like Mac OS X) AppleWebKit/603.1.30 (KHTML, like Gecko) Version/10.0 Mobile/14E304 Safari/602.1"
        private const val ACCESS_KEY_SECOND = "Mozilla/5.0 (iPhone; CPU iPhone OS 10_3_1 like Mac OS X) AppleWebKit/603.1.30 (KHTML, like Gecko) Version/10.0 Mobile/14E304 Safari/602.1"
    }

    @Headers("User-Agent: $ACCESS_KEY")
    @GET("v2/frontend/home/products")
    fun listPhotos(): Call<Welcome>

    @Headers("User-Agent: $ACCESS_KEY_SECOND")
    @GET("v1/sync/frontend/catalog/product/detail")

       fun listPhotosProduct(
        @Query("product_id") product_id: Long,
        @Query("lang") lang: String,
        @Query("region_id") region_id: Long,
        @Query("district_id") district_id: Long

    ): Call<Welcomes>

       @Headers("User-Agent: $ACCESS_KEY_SECOND")
       @GET("v1/search")
       fun searchProducts(
           @Query("q") q : String
       ) : Call<Welcome>
}