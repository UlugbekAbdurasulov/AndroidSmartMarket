package com.example.androidsmartmarket.activity.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.androidsmartmarket.model.Welcom
import com.example.androidsmartmarket.network.service.PhotosService
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject


@HiltViewModel
class CategoryViewModel @Inject constructor(private val postService: PhotosService) : ViewModel(){

    val allCategory = MutableLiveData<Welcom>()
    val id : ArrayList<Long> = ArrayList()
    val qetHashSet : HashSet<Long> = HashSet()

    fun apiGetCategory() {
        postService.listCategory("ru").enqueue(object : Callback<Welcom> {
            override fun onResponse(call: Call<Welcom>, response: Response<Welcom>) {
                allCategory.value = response.body()
            }

            override fun onFailure(call: Call<Welcom>, t: Throwable) {

            }

        })
    }


}






