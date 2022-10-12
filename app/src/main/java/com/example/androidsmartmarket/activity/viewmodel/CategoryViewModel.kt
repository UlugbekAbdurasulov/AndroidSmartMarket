package com.example.androidsmartmarket.activity.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.androidsmartmarket.model.*
import com.example.androidsmartmarket.network.service.PhotosService
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject


@HiltViewModel
class CategoryViewModel @Inject constructor(private val postService: PhotosService) : ViewModel(){

    val allCategory = MutableLiveData<Welcom>()
    val allCategories = MutableLiveData<Welcomess>()
    val allCategoriesId = MutableLiveData<Welcomee>()
    val id : ArrayList<Long> = ArrayList()
    var arrayListLong : HashSet<Long> = HashSet()
    val allProducts = MutableLiveData<Welcomes?>()

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

    fun apiGetCategoriesId(ids : Long) {
        postService.listCategoriesInnerHome("ru",26,2601,ids,1,16,"popular",
        "desc",26,2601).enqueue(object : Callback<Welcomee> {
            override fun onResponse(call: Call<Welcomee>, response: Response<Welcomee>) {
                allCategoriesId.value = response.body()
            }

            override fun onFailure(call: Call<Welcomee>, t: Throwable) {

            }

        })
    }

}






