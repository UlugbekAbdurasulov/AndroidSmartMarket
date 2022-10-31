package com.example.androidsmartmarket.activity.viewmodel
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.androidsmartmarket.model.Welcomee
import com.example.androidsmartmarket.model.Welcomes
import com.example.androidsmartmarket.network.service.PhotosService
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject
@HiltViewModel
class BacketViewModel @Inject constructor(private val postService: PhotosService) : ViewModel() {
    val allPosts = MutableLiveData<Welcomee>()
    val allProducts = MutableLiveData<Welcomes?>()
    val id : HashSet<Long> = HashSet()
    var arrayListLong : HashSet<Long> = HashSet()
    fun apiPostList(ids : Long) {
        postService.listCategoriesInnerHome("ru",26,2601,ids,1,16,"popular",
            "name",26,2601).enqueue(object : Callback<Welcomee> {
            override fun onResponse(call: Call<Welcomee>, response: Response<Welcomee>) {
                allPosts.value = response.body()
                response.body()!!.data.products.forEach {
                    var strParentId = it.category_id
                    var str = strParentId.toString()
                    str = str.substring(0,6)
                    Log.d("SSSSTOLONG",str)
                    if (str.toLong() == ids) {
                        for (i in it.photos) {
                            arrayListLong.add(i.product_id)
                            break
                        }
                    }
                }
            }
            override fun onFailure(call: Call<Welcomee>, t: Throwable) {
            }
        })
    }
    fun apiGetListFamily(getLong: ArrayList<Int>) {
        for (e in getLong) {
            postService.listPhotosProduct(e.toLong(),"ru",0,0).enqueue(object : Callback<Welcomes> {
                override fun onFailure(call: Call<Welcomes>, t: Throwable) {
                }

                override fun onResponse(call: Call<Welcomes>, response: Response<Welcomes>) {
                    allProducts.value = response.body()
                }

            })
        }
    }
}