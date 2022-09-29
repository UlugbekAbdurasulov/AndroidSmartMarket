package com.example.androidsmartmarket.activity.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.androidsmartmarket.activity.main.home.HomeFragment
import com.example.androidsmartmarket.model.Technicals
import com.example.androidsmartmarket.model.Welcome
import com.example.androidsmartmarket.model.Welcomee
import com.example.androidsmartmarket.model.Welcomes
import com.example.androidsmartmarket.network.service.PhotosService
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class CategoryInnerViewModel @Inject constructor(private val postService: PhotosService) : ViewModel(){

    val categoryInnerRv = MutableLiveData<Welcomee>()
    val id : ArrayList<Long> = ArrayList()
    val id_category : ArrayList<Long> = ArrayList()
    var getHashSet : HashMap<Long,String> = HashMap()

}