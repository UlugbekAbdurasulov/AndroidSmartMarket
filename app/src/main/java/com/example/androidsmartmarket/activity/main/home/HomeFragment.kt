package com.example.androidsmartmarket.activity.main.home

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.androidsmartmarket.R
import com.example.androidsmartmarket.activity.viewmodel.HomeViewModel
import com.example.androidsmartmarket.adabter.HomeAdapter
import com.example.androidsmartmarket.adabter.TechnicalsAdapter
import com.example.androidsmartmarket.databinding.FragmentHomeBinding
import com.example.androidsmartmarket.model.*
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment: Fragment(R.layout.fragment_home) {
    var adapter : HomeAdapter? = null
    var technicalsAdapter : TechnicalsAdapter? = null
    val homeViewModel: HomeViewModel by viewModels()
    var arrayList : ArrayList<Family> = ArrayList()
    var arrayListSec = ArrayList<Datas>()
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
//        var list : ArrayList<Datas> = ArrayList()
//        homeViewModel.allPosts.observe(requireActivity(), Observer {
//            adapter!!.setItems(it.data.technicals)
//        })

//        homeViewModel.allPostsrter.observe(requireActivity(),{
//            list.add(it.data)
//            technicalsAdapter!!.setItems(list)
//            Log.d("WWWW",list.toString())
//        })

        homeViewModel.allPosts.observe(requireActivity(), Observer {
            it.data.technicals.forEach {
                for (i in it.photos) {
                    arrayListThree.add(i.product_id)
                    homeViewModel.apiPostListM(i.product_id,"ru",0,0)
                    homeViewModel.allPostsrter.observe(requireActivity(), {
                        arrayListSec.add(it.data)
//                Toast.makeText(requireContext(),arrayListSec.toString(),Toast.LENGTH_LONG).show()
                    })
                }
            }
            Log.d("Log",arrayListThree.toString())
//            for (i in arrayListThree) {
////                addProduct(i,"ru",0,0)
//            homeViewModel.apiPostListM(i,"ru",0,0)
//
//            }
            Log.d("LLLLLL",arrayListSec.toString())
        })
        homeViewModel.apiPostList()

//            adapter!!.setItems(arrayListSec)
    }

//    public fun addProduct(productId: Long,lang : String,region_id : Long,district_id : Long) {
//        homeViewModel.apiPostListM(productId,lang,region_id,district_id)
//    }

}