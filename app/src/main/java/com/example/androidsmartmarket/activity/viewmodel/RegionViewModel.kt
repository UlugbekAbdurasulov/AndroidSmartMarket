package com.example.androidsmartmarket.activity.viewmodel

import android.graphics.Region
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.androidsmartmarket.activity.main.home.HomeFragment
import com.example.androidsmartmarket.model.Technicals
import com.example.androidsmartmarket.model.Welcome
import com.example.androidsmartmarket.model.WelcomeR
import com.example.androidsmartmarket.model.Welcomes
import com.example.androidsmartmarket.network.service.PhotosService
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class RegionViewModel @Inject constructor(private val postService: PhotosService) : ViewModel(){
    val allPosts = MutableLiveData<WelcomeR>()
    val allPostsrter = MutableLiveData<Welcomes>()
    fun apiGetRegions(q : String) {
        postService.getRegions(q).enqueue(object : Callback<WelcomeR> {
            override fun onResponse(call: Call<WelcomeR>, response: Response<WelcomeR>) {
                allPosts.value = response.body()
            }

            override fun onFailure(call: Call<WelcomeR>, t: Throwable) {
            }
        })
    }

}