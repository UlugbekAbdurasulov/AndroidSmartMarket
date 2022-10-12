package com.example.androidsmartmarket.activity.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.androidsmartmarket.model.Welcomes
import com.example.androidsmartmarket.network.service.PhotosService
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.ArrayList
import javax.inject.Inject

@HiltViewModel
class CustomViewModel @Inject constructor(private val postService: PhotosService) : ViewModel() {
    val allProducts = MutableLiveData<Welcomes?>()

    fun apiGetListFamily(getLong: ArrayList<Int>?) {
        for (i in getLong!!) {
            postService.listPhotosProduct(i.toLong(),"ru",0,0).enqueue(object : Callback<Welcomes> {
                override fun onFailure(call: Call<Welcomes>, t: Throwable) {
                }

                override fun onResponse(call: Call<Welcomes>, response: Response<Welcomes>) {
                    allProducts.value = response.body()
                }

            })
        }
    }
}