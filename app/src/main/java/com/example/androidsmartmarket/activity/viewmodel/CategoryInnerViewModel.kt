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
class CategoryInnerViewModel @Inject constructor(private val postService: PhotosService) : ViewModel(){
    val allCategories = MutableLiveData<Welcomess>()
    val id : ArrayList<Long> = ArrayList()
    fun apiGetCategoryies() {
        postService.listCategories("ru").enqueue(object : Callback<Welcomess> {
            override fun onResponse(call: Call<Welcomess>, response: Response<Welcomess>) {
                allCategories.value = response.body()
            }
            override fun onFailure(call: Call<Welcomess>, t: Throwable) {
            }
        })
    }
}