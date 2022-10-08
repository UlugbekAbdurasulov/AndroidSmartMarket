package com.example.androidsmartmarket.activity.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.androidsmartmarket.activity.main.home.HomeFragment
import com.example.androidsmartmarket.model.*
import com.example.androidsmartmarket.network.service.PhotosService
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class CategoryInnerViewModel @Inject constructor(private val postService: PhotosService) : ViewModel(){

    val allCategory = MutableLiveData<Welcom>()
    val allCategories = MutableLiveData<Welcomess>()
    val allCategoriesId = MutableLiveData<Welcome>()
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

    fun apiGetCategoryies() {
        postService.listCategories("ru").enqueue(object : Callback<Welcomess> {
            override fun onResponse(call: Call<Welcomess>, response: Response<Welcomess>) {
                allCategories.value = response.body()
            }

            override fun onFailure(call: Call<Welcomess>, t: Throwable) {

            }

        })
    }
    fun apiGetCategoriesId() {
        postService.listCategoriesId("ru",26,2601,121,1,16,"popular",
            "desc",26,2601).enqueue(object : Callback<Welcome> {
            override fun onResponse(call: Call<Welcome>, response: Response<Welcome>) {
                allCategoriesId.value = response.body()
            }

            override fun onFailure(call: Call<Welcome>, t: Throwable) {

            }

        })
    }

}