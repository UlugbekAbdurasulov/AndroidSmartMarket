package com.example.androidsmartmarket.activity.viewmodel

import android.telecom.Call
import android.view.textservice.TextServicesManager
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.androidsmartmarket.activity.main.home.HomeFragment
import com.example.androidsmartmarket.model.Welcom
import com.example.androidsmartmarket.model.Welcome
import com.example.androidsmartmarket.network.service.PhotosService
import dagger.hilt.android.lifecycle.HiltViewModel
import okhttp3.Response
import javax.inject.Inject
import javax.security.auth.callback.Callback

@HiltViewModel
class CategoryViewModel @Inject constructor(private val postService: PhotosService ) : ViewModel(){

    val allCategory = MutableLiveData<Welcom>()
    val id : ArrayList<Long> = ArrayList()
    val qetHashSet : HashSet<Long> = HashSet()

    fun apiPostList() {
        var homeFragment: HomeFragment = HomeFragment()
        postService.listCategory().enqueue(object : retrofit2.Callback<Welcom> {
            override fun onResponse(call: retrofit2.Call<Welcom>, response: retrofit2.Response<Welcom>) {
                for (i in response.body()!!.data.listIterator()) {
                    for (i in i.photos) {
                        id.add(i.product_id)
                        qetHashSet.add(i.product_id)
                        break
                    }
//                    i.photos.forEach {
//                        technicals.productId = it.product_id
//                        technicals.districtId = i.seller.district_id
//                        technicals.regionId = i.seller.region_id
//                    }
                }

                for (i in response.body()!!.data.family) {
                    for (i in i.photos) {
                        id_Family.add(i.product_id)
                        break
                    }
//                    i.photos.forEach {
//                        id_Family.add(it.product_id)
//                    }
                }

                for (i in response.body()!!.data.computers) {
                    for (i in i.photos) {
                        id_Comp.add(i.product_id)
                        break
                    }
//                    i.photos.forEach {
//
//                    }
                }
                apiGetList(getHashSet)
                apiGetListFamily(id_Family)
                apiGetListComp(id_Comp)
            }

            override fun onFailure(call: retrofit2.Call<Welcome>, t: Throwable) {
                TODO("Not yet implemented")
            }

            override fun onFailure(call: retrofit2.Call<Welcom>, t: Throwable) {

            }

        })
    }

    fun apiGetList(id: HashSet<Long>){

        for(i  in id){

        }
    }


}






