package com.example.androidsmartmarket.activity.viewmodel

import android.util.Log
import androidx.databinding.Bindable
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import by.kirich1409.viewbindingdelegate.viewBindingWithLifecycle
import com.example.androidsmartmarket.activity.repository.TVShowRepository
import com.example.androidsmartmarket.model.Welcome
import com.example.androidsmartmarket.model.Welcomes
import com.example.androidsmartmarket.network.service.PhotosService
import com.example.androidsmartmarket.utils.ARG
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject
import kotlin.coroutines.coroutineContext

@HiltViewModel
class HomeViewModel @Inject constructor(private val tvShowRepository: TVShowRepository) : ViewModel() {
    val allPosts = MutableLiveData<Welcome?>()
    val allPostsrter = MutableLiveData<Welcomes?>()
    val allPostSave: ArrayList<Long> = ArrayList()
    val getPosts = MutableLiveData<Welcome>()
    fun apiPostList() {
        CoroutineScope(Dispatchers.IO).launch {
            val response = tvShowRepository.apiTVShowPopular()
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    val resp = response.body()
                    allPosts.postValue(resp)

                } else {

                }
            }
        }

    }

    //
    fun apiPostListM(arg: Long) {
        CoroutineScope(Dispatchers.IO).launch {
            val response = tvShowRepository.apiTVShowDetails(arg)
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    val resp = response.body()
                    allPostsrter.postValue(resp)

                } else {

                }
            }
        }
    }
}