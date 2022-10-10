package com.example.androidsmartmarket.activity.main.home

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.app.ActivityOptionsCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
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
class CatInnerHome: Fragment() {
    var arrayCategory : ArrayList<Producta> = ArrayList()
    var adapter : CatInnerHomeAdapter? = null

    val CatInnerHomeViewModel: CatInnerHomeViewModel by viewModels()
    var arrayList : ArrayList<Producta> = ArrayList()

    var getHashSet : HashSet<Long> = HashSet()
    var booleans = false
    private val binding by viewBinding(FragmentCategoryHomeBinding::bind)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("OOOOOOO","onViewCreated")
        this.initViews(view)
    }

    @SuppressLint("SuspiciousIndentation", "NotifyDataSetChanged")
    private fun initViews(view: View) {
        binding.rvCategoriy.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL,false)
        adapter = CatInnerHomeAdapter{ seletedItem: Producta -> listItemClicked(seletedItem,view)}
        binding.rvCategoriy.adapter = adapter

        var id = arguments?.getLong("orderIDArgumentHome")
        CatInnerHomeViewModel.apiPostList()
        CatInnerHomeViewModel.allPosts.observe(this.viewLifecycleOwner) {
            it.data.products.forEach {
                var strParentId = it.category_id.toLong()
                if (id == strParentId.toLong()) {
                    arrayCategory.add(it)
                    Log.d("SELECTITEMID", it.toString())
                }
            }
            adapter!!.setItems(arrayCategory)
        }
        !booleans


    }


    private fun listItemClicked(seletedItem: Producta, view: View) {
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

    override fun onPause() {
        clear()
        super.onPause()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun clear() {
//        val size: Int = arrayList.size
//        if (size > 0) {
//            for (i in 0 until size) {
//                arrayList.removeAt(0)
//            }
//            adapter!!.notifyItemRangeRemoved(0, size)
//            binding.rvItem.removeAllViewsInLayout()
//        }
        arrayList.clear()  // clear list
        adapter!!.notifyDataSetChanged()

        Log.d("SSSSSSSSS", arrayList.size.toString())
    }

}