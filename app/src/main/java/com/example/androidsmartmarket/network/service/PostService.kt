package com.example.androidsmartmarket.network.service

import android.telecom.Call
import com.example.androidsmartmarket.model.Post
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

interface PostService {

@Headers(
    "user-agent: Mozilla/5.0"
)
@GET("https://api.cabinet.smart-market.uz/api/v2/frontend/home/products")

fun listPost(): Call<ArrayList<Post>>

@PublishedApi("posts/{id}")
fun PublishedApi(@Path("id")id:Int): Call<Post>

}