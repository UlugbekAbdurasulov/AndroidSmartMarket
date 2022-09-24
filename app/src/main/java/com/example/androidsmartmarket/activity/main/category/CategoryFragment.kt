package com.example.androidsmartmarket.activity.main.category

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.androidsmartmarket.R
import com.example.androidsmartmarket.databinding.FragmentBasketBinding
import com.example.androidsmartmarket.databinding.FragmentCategoryBinding
import com.example.androidsmartmarket.databinding.FragmentHomeBinding


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.activity.viewModels
import androidx.core.app.ActivityOptionsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidsmartmarket.activity.DetailsActivity
import com.example.androidsmartmarket.activity.viewmodel.SearchViewModel
import com.example.androidsmartmarket.adabter.CategoryAdapter
import com.example.androidsmartmarket.databinding.ActivitySearchBinding
import com.example.androidsmartmarket.model.Datas
import com.example.androidsmartmarket.model.Product
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CategoryFragment(function: (Long) -> Unit) : AppCompatActivity() {
    private lateinit var binding: FragmentCategoryBinding
    var categoryAdapter : CategoryAdapter? = null
    val searchViewModel: SearchViewModel by viewModels()
    var arrayList : ArrayList<Datas> = ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentCategoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initViews()
    }

    private fun initViews() {
        binding.rvCategory.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false)
         categoryAdapter= CategoryAdapter {  }({ seletedItem: Long -> listItemClicked(seletedItem)})
        binding.rvCategory.adapter = categoryAdapter

    }

    private fun listItemClicked(seletedItem: Long) {
        searchViewModel.apiGetList(seletedItem)
        searchViewModel.allPostsrter.observe(this,{
            var intent = Intent(this, DetailsActivity::class.java)
            intent.putExtra("datas", it.data)
            val options = ActivityOptionsCompat.makeSceneTransitionAnimation(this)
            startActivity(intent,options.toBundle())
            finish()
        })
    }
}