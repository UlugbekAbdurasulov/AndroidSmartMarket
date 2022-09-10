package com.example.androidsmartmarket.activity.viewmodel

import android.util.Log
import androidx.databinding.Bindable
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
class HomeViewModel @Inject constructor(private val photosService: PhotosService) : ViewModel(){
    val allPosts = MutableLiveData<Welcome>()
    val allPostsrter = MutableLiveData<Welcomes>()
     fun apiPostList() {
        photosService.listPhotos().enqueue(object : Callback<Welcome> {
            override fun onResponse(
                call: Call<Welcome>,
                response: Response<Welcome>,
            ) {
                allPosts.value = response.body()
                Log.d("MainViewModel", allPosts.value.toString())
            }

            override fun onFailure(call: Call<Welcome>, t: Throwable) {

            }
        })

    }

    fun apiPostListM(product_Id : Long,lang:String,region_id:Long,district_id:Long) {
        photosService.listPhotosProduct(product_Id, "ru", region_id,district_id).enqueue(object : Callback<Welcomes> {
            override fun onResponse(call: Call<Welcomes>, response: Response<Welcomes>) {
                allPostsrter.value = response.body()
            }

            override fun onFailure(call: Call<Welcomes>, t: Throwable) {

            }

        })
    }
}