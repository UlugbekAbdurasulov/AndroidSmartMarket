package com.example.androidsmartmarket.activity.main.home

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.androidsmartmarket.R
import com.example.androidsmartmarket.databinding.FragmentHomeBinding
import com.example.androidsmartmarket.model.Welcome
import com.example.androidsmartmarket.model.Welcomes
import com.example.androidsmartmarket.network.RetrofitHttp
import com.example.androidsmartmarket.network.service.PhotosService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class HomeFragment: Fragment(R.layout.fragment_home) {

    private val binding by viewBinding(FragmentHomeBinding::bind)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    private fun initViews() {
        getPhotos()

    }

    private fun getPhotos() {
//
        RetrofitHttp.photosService.listPhotosProduct(50490,"ru",8,810)
            .enqueue(object : Callback<Welcomes> {
                override fun onResponse(
                    call: Call<Welcomes>,
                    response: Response<Welcomes>,
                ) {
                    binding.tvHome.text = response.body().toString()
                    Log.d("@@@", "PhotosList -> ${response.body()}")
                }

                override fun onFailure(call: Call<Welcomes>, t: Throwable) {
                    binding.tvHome.text = t.localizedMessage
                    Log.d("@@@", "PhotosList -> Error ${t.localizedMessage}")
                }

            })
    }
}