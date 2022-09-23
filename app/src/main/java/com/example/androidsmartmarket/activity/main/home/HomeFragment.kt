package com.example.androidsmartmarket.activity.main.home

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import androidx.core.app.ActivityOptionsCompat
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.androidsmartmarket.R
import com.example.androidsmartmarket.activity.AdressActivity
import com.example.androidsmartmarket.activity.DetailsActivity
import com.example.androidsmartmarket.activity.SearchActivity
import com.example.androidsmartmarket.activity.viewmodel.HomeViewModel
import com.example.androidsmartmarket.adabter.FamilyAdapter
import com.example.androidsmartmarket.adabter.HomeAdapter
import com.example.androidsmartmarket.adabter.NoteAdapter
import com.example.androidsmartmarket.databinding.FragmentHomeBinding
import com.example.androidsmartmarket.model.*
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
    var booleans = true
    private val binding by viewBinding(FragmentHomeBinding::bind)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        this.initViews(view)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    private fun initViews(view: View) {
        binding.rvItem.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL,false)
        binding.rvFamily.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL,false)
        binding.rvComp.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL,false)
        adapter = HomeAdapter({ seletedItem: Datas -> listItemClicked(seletedItem,view)})
        familyAdapter = FamilyAdapter { seletedItem: Datas -> listItemClicked(seletedItem, view) }
        noteAdapter = NoteAdapter { seletedItem: Datas -> listItemClicked(seletedItem, view) }
        binding.rvItem.adapter = adapter
        binding.rvFamily.adapter = familyAdapter
        binding.rvComp.adapter = noteAdapter

        homeViewModel.apiPostList()

        homeViewModel.allPostsrter.observe(requireActivity(),{
            arrayList.add(it.data)
            if (booleans == true) {
                adapter!!.setItems(arrayList)
            }else {

            }
        })

        homeViewModel.allPostsFamily.observe(requireActivity(),{
            arrayList_FM.add(it!!.data)
            familyAdapter!!.setItems(arrayList_FM)
        })

        homeViewModel.allPostsComp.observe(requireActivity(),{
            arrayList_COMP.add(it!!.data)
            noteAdapter!!.setItems(arrayList_COMP)
            Log.d("Comp",arrayList_COMP.toString())
        })
        !booleans

        binding.apply {
            autoCompleteTextview2.setOnClickListener {
                var intent : Intent = Intent(requireContext(), SearchActivity::class.java)
                        startActivity(intent)
            }
            setAddress.setOnClickListener {
                var intent : Intent = Intent(requireContext(),AdressActivity::class.java)
                startActivity(intent)
            }
        }
    }

    private fun listItemClicked(seletedItem: Datas, view: View) {
        var intent = Intent(requireContext(),DetailsActivity::class.java)
        intent.putExtra("datas", seletedItem)
        val options = ActivityOptionsCompat.makeSceneTransitionAnimation(requireActivity())
        startActivity(intent,options.toBundle())
    }


/*    private fun listItemClickedd(seletedItem: Datas, view: View) {
        var intent = Intent(requireContext(),DetailsActivity::class.java)
        intent.putExtra("datas", seletedItem)
        val options = ActivityOptionsCompat.makeSceneTransitionAnimation(requireActivity())
        startActivity(intent,options.toBundle())
    }*/

    @SuppressLint("NotifyDataSetChanged")
    override fun onPause() {
        super.onPause()
    }

    override fun onStart() {
        super.onStart()
    }
    override fun onResume() {
//        Log.d("OnREst",homeViewModel.id_Comp.toString())
        super.onResume()
    }
}