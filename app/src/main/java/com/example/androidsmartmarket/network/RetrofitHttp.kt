package com.example.androidsmartmarket.network

import android.content.Context
import com.example.androidsmartmarket.network.service.PhotosService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import retrofit2.http.Multipart
import javax.inject.Singleton
@Module
@InstallIn(SingletonComponent::class)
object RetrofitHttp {
    val IS_TESTER = true
    val SERVER_DEVELOPMENT = "https://api.cabinet.smart-market.uz/api/"
    val SERVER_PRODUCTION = "https://api.cabinet.smart-market.uz/api/"

    @Provides
    fun server():String {
        if (IS_TESTER) return SERVER_DEVELOPMENT
        return SERVER_PRODUCTION
    }

    @Provides
    @Singleton
    fun retrofitClient() : Retrofit {
        return Retrofit.Builder().baseUrl(server())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun postService() : PhotosService {
        return retrofitClient().create(PhotosService::class.java)
    }
}