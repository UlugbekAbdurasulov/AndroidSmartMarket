package com.example.androidsmartmarket.activity.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.androidsmartmarket.model.Welcome
import com.example.androidsmartmarket.model.Welcomes
import com.example.androidsmartmarket.network.service.PhotosService
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class CategoryViewModel @Inject constructor(private val postService: PhotosService) : ViewModel(){
    val allPosts = MutableLiveData<Welcome>()
    val allPostsrter = MutableLiveData<Welcomes>()
    fun apiSearchList(q : String) {
        postService.searchProducts(q).enqueue(object : Callback<Welcome> {
            override fun onResponse(call: Call<Welcome>, response: Response<Welcome>) {
                allPosts.value = response.body()
            }

            override fun onFailure(call: Call<Welcome>, t: Throwable) {
            }
        })
    }

    fun apiGetList(id: Long) {
            postService.listPhotosProduct(id,"ru",0,0).enqueue(object : Callback<Welcomes> {
                override fun onFailure(call: Call<Welcomes>, t: Throwable) {
                }

                override fun onResponse(call: Call<Welcomes>, response: Response<Welcomes>) {
                    allPostsrter.value = response.body()
                }
            })
    }
}