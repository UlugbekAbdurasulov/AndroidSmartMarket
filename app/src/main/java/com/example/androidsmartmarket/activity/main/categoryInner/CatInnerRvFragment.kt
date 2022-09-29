package com.example.androidsmartmarket.activity.main.categoryInner

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityOptionsCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.androidsmartmarket.R
import com.example.androidsmartmarket.activity.AdressActivity
import com.example.androidsmartmarket.activity.DetailsActivity
import com.example.androidsmartmarket.activity.SearchActivity
import com.example.androidsmartmarket.activity.viewmodel.HomeViewModel
import com.example.androidsmartmarket.adabter.*
import com.example.androidsmartmarket.databinding.ActivityCategoryRvBinding
import com.example.androidsmartmarket.databinding.FragmentHomeBinding
import com.example.androidsmartmarket.manager.PrefsManager
import com.example.androidsmartmarket.model.*
import dagger.hilt.android.AndroidEntryPoint
import java.util.*
import kotlin.collections.ArrayList

@AndroidEntryPoint
class CatInnerRvFragment: Fragment(R.layout.fragment_home) {

    var adapter: CategoryInnerRvAdapter? = null
    var adapterInner: CategoryInnerRv2Adapter? = null

    val homeViewModel: HomeViewModel by viewModels()
    var arrayList: ArrayList<Datas> = ArrayList()
    var arrayList_Cat: ArrayList<Datas> = ArrayList()

    var booleans = false

    private val binding by viewBinding(ActivityCategoryRvBinding::bind)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        this.initViews(view)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    private fun initViews(view: View) {
        binding.rvCategoriy.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.rvCategoriyy.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
       // adapter = FamilyAdapter { seletedItem: Datas -> listItemClicked(seletedItem, view) }
       // adapterInner = FamilyAdapter { seletedItem: Datas -> listItemClicked(seletedItem, view) }

        binding.rvCategoriy.adapter = adapter
        binding.rvCategoriyy.adapter = adapterInner
    }
}