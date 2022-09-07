package com.example.androidsmartmarket.network

import com.example.androidsmartmarket.network.service.PhotosService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

object RetrofitHttp {
    val IS_TESTER = true
    val SERVER_DEVELOPMENT = "https://api.cabinet.smart-market.uz/api/"
    val SERVER_PRODUCTION = "https://api.cabinet.smart-market.uz/api/"

    val retrofit = Retrofit.Builder()
        .baseUrl(server())
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun server(): String{
        if (IS_TESTER) return SERVER_DEVELOPMENT
        return SERVER_PRODUCTION
    }

    val photosService: PhotosService = retrofit.create(PhotosService::class.java)

    fun <T> createService(service: Class<T>): T{
        return retrofit.create(service)
    }
}