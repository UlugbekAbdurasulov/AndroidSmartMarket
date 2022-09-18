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
import com.example.androidsmartmarket.adabter.FamilyAdapter
import com.example.androidsmartmarket.adabter.HomeAdapter
import com.example.androidsmartmarket.adabter.NoteAdapter
import com.example.androidsmartmarket.databinding.FragmentHomeBinding
import com.example.androidsmartmarket.model.*
import com.example.androidsmartmarket.utils.ARG
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment: Fragment(R.layout.fragment_home) {
    var adapter : HomeAdapter? = null
    var familyAdapter : FamilyAdapter? = null
    var noteAdapter : NoteAdapter? = null
    val homeViewModel: HomeViewModel by viewModels()
    var arrayList : ArrayList<Datas> = ArrayList()
    var arrayList_FM : ArrayList<Datas> = ArrayList()
    var arrayList_COMP : ArrayList<Datas> = ArrayList()
    private val binding by viewBinding(FragmentHomeBinding::bind)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    private fun initViews() {
        binding.rvItem.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL,false)
        binding.rvFamily.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL,false)
        binding.rvComp.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL,false)
        adapter = HomeAdapter(this)
        familyAdapter = FamilyAdapter(this)
        noteAdapter = NoteAdapter(this)
        binding.rvItem.adapter = adapter
        binding.rvFamily.adapter = familyAdapter
        binding.rvComp.adapter = noteAdapter
        homeViewModel.apiPostList()
        homeViewModel.allPostsrter.observe(requireActivity(),{
            arrayList.add(it!!.data)
            adapter!!.setItems(arrayList)
        })

        homeViewModel.allPostsFamily.observe(requireActivity(),{
            arrayList_FM.add(it!!.data)
            familyAdapter!!.setItems(arrayList_FM)
        })

        homeViewModel.allPostsComp.observe(requireActivity(),{
            arrayList_COMP.add(it!!.data)
            noteAdapter!!.setItems(arrayList_COMP)
        })
    }

    private fun apiData() {
        var arg : ARG = ARG
    }

    override fun onPause() {
        Log.d("OnREst","OnPause")
        super.onPause()
    }
    override fun onResume() {
        Log.d("OnREst","OnResume")
        super.onResume()
    }
}