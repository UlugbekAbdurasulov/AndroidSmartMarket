package com.example.androidsmartmarket.activity.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
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
import kotlin.collections.HashSet

@HiltViewModel
class CatInnerHomeViewModel @Inject constructor(private val postService: PhotosService) : ViewModel(){
    val allPosts = MutableLiveData<Welcomee>()

    val allPostsFamily = MutableLiveData<Welcomee?>()

    val id : HashSet<Long> = HashSet()
    val id_Family : HashSet<Long> = HashSet()
    var getHashSet : HashSet<Long> = HashSet()

    fun apiPostList() {
        postService.listCategoriesInnerHome("ru",26,2601,121,1,16,"popular",
            "desc",26,2601).enqueue(object : Callback<Welcomee> {
            override fun onResponse(call: Call<Welcomee>, response: Response<Welcomee>) {
                allPosts.value = response.body()
                for (i in response.body()!!.data.products) {
                    for (i in i.photos) {
                        id.add(i.product_ID)
                        getHashSet.add(i.product_ID)
                        break
                    }
                }

                for (i in response.body()!!.data.products) {
                    for (i in i.photos) {
                        id_Family.add(i.product_ID)
                        break
                    }
                }



                apiGetListFamily(id_Family)

            }

            override fun onFailure(call: Call<Welcomee>, t: Throwable) {
            }

        })
    }



    fun apiGetListFamily(id_Family: HashSet<Long>) {
        for (i in id_Family) {
            postService.listCategoriesInnerHome("ru",26,2601,121,1,16,"popular",
                "desc",26,2601).enqueue(object : Callback<Welcomee> {
                override fun onFailure(call: Call<Welcomee>, t: Throwable) {
                }

                override fun onResponse(call: Call<Welcomee>, response: Response<Welcomee>) {
                    allPostsFamily.value = response.body()
                }

            })
        }
    }


}