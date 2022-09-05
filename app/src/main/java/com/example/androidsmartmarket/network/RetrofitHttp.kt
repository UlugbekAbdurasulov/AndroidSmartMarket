package com.example.androidsmartmarket.network

import com.example.androidsmartmarket.network.service.PostService
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

object RetrofitHttp {

    private val InTEST = true
    private val Server_Dacument ="https://api.cabinet.smart-market.uz/api/v2/frontend/home/products"
    private val Server_Product = "https://api.cabinet.smart-market.uz/api/v2/frontend/home/products"
    val retrofit = Retrofit.Builder().baseUrl(server()).addConverterFactory(GsonConverterFactory.create()).build()

    private fun server():String{
    if (InTEST) return Server_Dacument
        return Server_Product
    }
    val postService : PostService = retrofit.create(PostService::class.java)
}