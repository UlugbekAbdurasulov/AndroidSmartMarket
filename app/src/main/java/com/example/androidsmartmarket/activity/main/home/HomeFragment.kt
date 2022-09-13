package com.example.androidsmartmarket.activity.main.home

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.androidsmartmarket.R
import com.example.androidsmartmarket.activity.viewmodel.HomeViewModel
import com.example.androidsmartmarket.adabter.HomeAdapter
import com.example.androidsmartmarket.adabter.TechnicalsAdapter
import com.example.androidsmartmarket.databinding.FragmentHomeBinding
import com.example.androidsmartmarket.model.*
import com.example.androidsmartmarket.utils.ARG
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment: Fragment(R.layout.fragment_home) {
    var adapter : HomeAdapter? = null
    var technicalsAdapter : TechnicalsAdapter? = null
    val homeViewModel: HomeViewModel by viewModels()
    var arrayList : ArrayList<Datas> = ArrayList()
    var arrayListSec = ArrayList<Family>()
    var arrayListThree = ArrayList<Long>()
    private val binding by viewBinding(FragmentHomeBinding::bind)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    private fun initViews() {
        binding.rvItem.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL,false)
        adapter = HomeAdapter(this)
        technicalsAdapter = TechnicalsAdapter(this)
        binding.rvItem.adapter = adapter

        homeViewModel.allPosts.observe(requireActivity()) {
            it!!.data.technicals.forEach {
                it.photos.forEach {
                    Log.d("UUUU", it.product_id.toString())
                    homeViewModel.apiPostListM(it.product_id)
                }
            }
            Log.d("Saved", homeViewModel.allPostSave.toString())
        }
        homeViewModel.apiPostList()
        homeViewModel.allPostsrter.observe(requireActivity(),{
            Log.d("PPPPP",it!!.data.toString())
            ARG.apiSaUrl.add(it.data)
            arrayList.add(it.data)
        })
        apiData()
    }

    private fun apiData() {
        var arg : ARG = ARG
        adapter!!.setItems(arg.apiSaUrl)
    }
}