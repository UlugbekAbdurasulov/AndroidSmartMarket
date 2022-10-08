package com.example.androidsmartmarket.activity.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.androidsmartmarket.model.Technicals
import com.example.androidsmartmarket.model.Welcome
import com.example.androidsmartmarket.model.Welcomes
import com.example.androidsmartmarket.network.service.PhotosService
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject
import kotlin.collections.HashSet

@HiltViewModel
class HomeViewModel @Inject constructor(private val postService: PhotosService) : ViewModel(){
    val allPosts = MutableLiveData<Welcome>()
    val allPostsrter = MutableLiveData<Welcomes?>()
    val allPostsFamily = MutableLiveData<Welcomes?>()
    val allPostsComp = MutableLiveData<Welcomes?>()
    val id : HashSet<Long> = HashSet()
    val id_Family : HashSet<Long> = HashSet()
    val id_Comp : HashSet<Long> = HashSet()
    var getHashSet : HashSet<Long> = HashSet()
    var technicals : Technicals = Technicals()
    fun apiPostList() {
        postService.listPhotos().enqueue(object : Callback<Welcome> {
            override fun onResponse(call: Call<Welcome>, response: Response<Welcome>) {
                for (i in response.body()!!.data.technicals) {
                    for (i in i.photos) {
                        id.add(i.product_id)
                        getHashSet.add(i.product_id)
                        break
                    }
                }

                for (i in response.body()!!.data.family) {
                    for (i in i.photos) {
                        id_Family.add(i.product_id)
                        break
                    }
                }

                for (i in response.body()!!.data.computers) {
                    for (i in i.photos) {
                        id_Comp.add(i.product_id)
                        break
                    }
                }
                apiGetList(getHashSet)
                apiGetListFamily(id_Family)
                apiGetListComp(id_Comp)
            }

            override fun onFailure(call: Call<Welcome>, t: Throwable) {
            }

        })
    }

    fun apiGetList(id: HashSet<Long>) {
        for (i in id) {
            postService.listPhotosProduct(i,"ru",0,0).enqueue(object : Callback<Welcomes> {
                override fun onFailure(call: Call<Welcomes>, t: Throwable) {
                }

                override fun onResponse(call: Call<Welcomes>, response: Response<Welcomes>) {
                    allPostsrter.value = response.body()
                }

            })
        }
    }

    fun apiGetListFamily(id_Family: HashSet<Long>) {
        for (i in id_Family) {
            postService.listPhotosProduct(i,"ru",0,0).enqueue(object : Callback<Welcomes> {
                override fun onFailure(call: Call<Welcomes>, t: Throwable) {
                }

                override fun onResponse(call: Call<Welcomes>, response: Response<Welcomes>) {
                    allPostsFamily.value = response.body()
                }

            })
        }
    }

    fun apiGetListComp(id_Comp: HashSet<Long>) {
        for (i in id_Comp) {
            postService.listPhotosProduct(i,"ru",0,0).enqueue(object : Callback<Welcomes> {
                override fun onFailure(call: Call<Welcomes>, t: Throwable) {
                }

                override fun onResponse(call: Call<Welcomes>, response: Response<Welcomes>) {
                    allPostsComp.value = response.body()
                }

            })
        }
    }
}