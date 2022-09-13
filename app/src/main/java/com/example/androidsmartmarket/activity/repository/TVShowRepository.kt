package com.example.androidsmartmarket.activity.repository

import com.example.androidsmartmarket.network.service.PhotosService
import javax.inject.Inject

class TVShowRepository @Inject constructor(
    private val tvShowService: PhotosService) {

    /**
     * Retrofit Related
     * **/
    suspend fun apiTVShowPopular() = tvShowService.listPhotos()
    suspend fun apiTVShowDetails(q: Long) = tvShowService.listPhotosProduct(q,"ru",0,0)

}