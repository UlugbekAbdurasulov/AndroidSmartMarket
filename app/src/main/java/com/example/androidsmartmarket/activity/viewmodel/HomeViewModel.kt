package com.example.androidsmartmarket.activity.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidsmartmarket.database.Event
import com.example.androidsmartmarket.database.MedicineRepository
import com.example.androidsmartmarket.database.entity.Product
import com.example.androidsmartmarket.model.Technicals
import com.example.androidsmartmarket.model.Welcome
import com.example.androidsmartmarket.model.Welcomes
import com.example.androidsmartmarket.network.service.PhotosService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject
import kotlin.collections.HashSet

@HiltViewModel
class HomeViewModel @Inject constructor(private val postService: PhotosService,private val repository: MedicineRepository) : ViewModel(){
    val allPosts = MutableLiveData<Welcome>()
    val allPostsrter = MutableLiveData<Welcomes?>()
    val allPostsFamily = MutableLiveData<Welcomes?>()
    val allPostsComp = MutableLiveData<Welcomes?>()
    val id : HashSet<Long> = HashSet()
    val id_Family : HashSet<Long> = HashSet()
    val id_Comp : HashSet<Long> = HashSet()
    var getHashSet : HashSet<Long> = HashSet()
    var technicals : Technicals = Technicals()
    val subscribers_base = repository.subscribers
    private val statusMessage = MutableLiveData<Event<String>>()
    fun apiPostList() {
        postService.listPhotos().enqueue(object : Callback<Welcome> {
            override fun onResponse(call: Call<Welcome>, response: Response<Welcome>) {
                allPosts.value = response.body()
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

    fun insertBase(subscriber: Product): Job = viewModelScope.launch {
        val newRowId = repository.insert(subscriber)
        if (newRowId > -1) {
            statusMessage.value = Event("successfully")
        } else {
            statusMessage.value = Event("Error Occurred")
        }
    }

    fun delete(subscriber: Product): Job = viewModelScope.launch {
        val noOfRowsDeleted = repository.delete(subscriber)
    }

    fun clearAll(): Job = viewModelScope.launch {
        val noOfRowsDeleted = repository.deleteAll()
        if (noOfRowsDeleted > 0) {
            statusMessage.value = Event("$noOfRowsDeleted O`chirish omadli yakunlandi")
        } else {
            statusMessage.value = Event("Error Occurred")
        }

    }

}