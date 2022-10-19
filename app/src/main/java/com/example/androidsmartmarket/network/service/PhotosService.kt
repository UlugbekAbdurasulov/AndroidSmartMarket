package com.example.androidsmartmarket.network.service



import com.example.androidsmartmarket.model.*
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
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
        @Query("product_id") product_id: Int,
        @Query("lang") lang: String,
        @Query("region_id") region_id: Long,
        @Query("district_id") district_id: Long

    ): Call<Welcomes>

       @Headers("User-Agent: $ACCESS_KEY_SECOND")
       @GET("v1/search")
       fun searchProducts(
           @Query("q") q : String
       ) : Call<Welcome>


    @Headers("User-Agent: $ACCESS_KEY_SECOND")
    @GET("v1/sync/frontend/region")
    fun getRegions(
        @Query("lang") lang : String
    ) : Call<WelcomeR>

    @Headers("User-Agent: $ACCESS_KEY")
    @GET("v1/ecommerce/categories")
    fun listCategory(
        @Query("lang") lang: String
    ): Call<Welcom>

    @Headers("User-Agent: $ACCESS_KEY")
    @GET("v1/sync/frontend/catalogs")
    fun listCategories(
        @Query("lang") lang: String
    ): Call<Welcomess>

    @Headers("User-Agent: $ACCESS_KEY")
    @GET("v1/sync/frontend/catalog/products")
    fun listCategoriesId(
        @Query("lang") lang: String,
        @Query("shipping_region_id") shipping_region_id: Long,
        @Query("shipping_district_id") shipping_district_id: Long,
        @Query("category_id") category_id: Long,
        @Query("page") page: Long,
        @Query("page_size") page_size: Long,
        @Query("sorting") sorting: String,
        @Query("order") order: String,
        @Query("region_id") region_id: Long,
        @Query("district_id") district_id: Long,
    ): Call<Welcome>

    @Headers("User-Agent: $ACCESS_KEY")
    @GET("v1/sync/frontend/catalog/products")
    fun listCategoriesInnerHome(
        @Query("lang") lang: String,
        @Query("shipping_region_id") shipping_region_id: Long,
        @Query("shipping_district_id") shipping_district_id: Long,
        @Query("category_id") category_id: Long,
        @Query("page") page: Long,
        @Query("page_size") page_size: Long,
        @Query("sorting") sorting: String,
        @Query("order") order: String,
        @Query("region_id") region_id: Long,
        @Query("district_id") district_id: Long,
    ): Call<Welcomee>
}