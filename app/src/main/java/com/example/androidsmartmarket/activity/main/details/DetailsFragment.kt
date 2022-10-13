package com.example.androidsmartmarket.activity.main.details

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.androidsmartmarket.R
import com.example.androidsmartmarket.activity.viewmodel.CategoryInnerViewModel
import com.example.androidsmartmarket.activity.viewmodel.CategoryViewModel
import com.example.androidsmartmarket.adabter.CategoriesAdapterInner
import com.example.androidsmartmarket.databinding.FragmentDetailsBinding
import com.example.androidsmartmarket.model.Category
import com.example.androidsmartmarket.model.Welcomess
import dagger.hilt.android.AndroidEntryPoint
import kotlin.math.log


@AndroidEntryPoint
class DetailsFragment: Fragment(R.layout.fragment_details) {
    private val binding by viewBinding(FragmentDetailsBinding::bind)
    var adapter: CategoriesAdapterInner? = null
    var arrayCategory : ArrayList<Category> = ArrayList()
    private val categoryViewModel : CategoryInnerViewModel by viewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        this.initViews()
    }

   private fun initViews() {
        binding.rvCategoryIn.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL,false)
        adapter = CategoriesAdapterInner{seletedItem: Long -> listItemClicked(seletedItem)}
        binding.rvCategoryIn.adapter = adapter
        var id = arguments?.getLong("orderIDArgument")
        Log.d("DDDDDDDDDDD",id.toString())

       categoryViewModel.allCategories.observe(requireActivity()) {
            it.data.categories.forEach {
                var strParentId = it.parent_id.toString()
                if (id == strParentId.toLong()) {
                    arrayCategory.add(it)
                    Log.d("SELECTITEMID", it.toString())
                }
            }
           adapter!!.setItems(arrayCategory)
           arrayCategory.clear(); // clear list
           Log.d("ArrayCategoriess", arrayCategory.toString())
           adapter!!.notifyDataSetChanged()
           binding.rvCategoryIn.removeAllViewsInLayout();
        }
       categoryViewModel.apiGetCategoryies()
    }

    @SuppressLint("SuspiciousIndentation")
    private fun listItemClicked(seletedItemE: Long) {
      var bundle: Bundle = Bundle()
        bundle.putLong("orders", seletedItemE)
        Log.d("order", bundle.toString())
        findNavController().navigate(R.id.action_CategoryDetalies, bundle)
        Log.d("VARCHARVAR",seletedItemE.toString())
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onStart() {
        clear()
        super.onStart()
    }
    @SuppressLint("NotifyDataSetChanged")
    fun clear() {
//        val size: Int = arrayCategoryList.size
//        if (size > 0) {
//            for (i in 0 until size) {
//                arrayCategoryList.removeAt(0)
//            }
//            adapter!!.notifyItemRangeRemoved(0, size)
//        }
        arrayCategory.clear(); // clear list
        Log.d("ArrayCategoriess", arrayCategory.toString())
        adapter!!.notifyDataSetChanged()
        binding.rvCategoryIn.removeAllViewsInLayout();
    }
}