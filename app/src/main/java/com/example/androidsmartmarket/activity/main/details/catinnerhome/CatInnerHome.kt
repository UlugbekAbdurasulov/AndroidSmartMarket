package com.example.androidsmartmarket.activity.main.details.catinnerhome

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.app.ActivityOptionsCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.androidsmartmarket.R
import com.example.androidsmartmarket.activity.DetailsActivity
import com.example.androidsmartmarket.activity.viewmodel.CatInnerHomeViewModel
import com.example.androidsmartmarket.adabter.CatInnerHomeAdapter
import com.example.androidsmartmarket.databinding.FragmentCategoryHomeBinding
import com.example.androidsmartmarket.model.*
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CatInnerHome: Fragment(R.layout.fragment_category_home) {
    var arrayCategory : ArrayList<Datas> = ArrayList()
    var adapter : CatInnerHomeAdapter? = null
    val CatInnerHomeViewModel: CatInnerHomeViewModel by viewModels()
    var booleans = false
    private val binding by viewBinding(FragmentCategoryHomeBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("OOOOOOO","onViewCreated")
        initViews()
    }

    @SuppressLint("SuspiciousIndentation", "NotifyDataSetChanged")
    private fun initViews() {
        binding.rvCategoriy.layoutManager = GridLayoutManager(requireContext(),2)
        adapter = CatInnerHomeAdapter{ seletedItem: Datas -> listItemClicked(seletedItem)}
        binding.rvCategoriy.adapter = adapter
        progressOnn()
        val idLong = arguments?.getLong("orders")
        Log.d("KKKLLLLLll",idLong.toString())
        CatInnerHomeViewModel.apiPostList(idLong!!)
        CatInnerHomeViewModel.allProducts.observe(this.viewLifecycleOwner) {
            arrayCategory.add(it!!.data!!)
            if (arrayCategory.size == 16) {
                adapter!!.setItems(arrayCategory)
                if (CatInnerHomeViewModel.allProducts.value!=null) {
                    progressOff()
                }
            }
        }
        !booleans
    }


    private fun listItemClicked(seletedItem: Datas) {
        var intent = Intent(requireContext(),DetailsActivity::class.java)
        intent.putExtra("datas", seletedItem)
        val options = ActivityOptionsCompat.makeSceneTransitionAnimation(requireActivity())
        startActivity(intent,options.toBundle())
    }

    fun progressOff() {
        binding.progressBar.visibility = View.GONE
    }

    fun progressOnn() {
        binding.progressBar.visibility = View.VISIBLE
    }

    override fun onPause() {
        super.onPause()
        clear()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun clear() {
        arrayCategory.clear()  // clear list
        adapter!!.notifyDataSetChanged()
        binding.rvCategoriy.removeAllViewsInLayout()
        Log.d("SSSSSSSSS", arrayCategory.size.toString())
    }

/*    private fun listItemClickedd(seletedItem: Datas, view: View) {
        var intent = Intent(requireContext(),DetailsActivity::class.java)
        intent.putExtra("datas", seletedItem)
        val options = ActivityOptionsCompat.makeSceneTransitionAnimation(requireActivity())
        startActivity(intent,options.toBundle())
    }*/

}