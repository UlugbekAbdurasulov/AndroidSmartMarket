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
import com.example.androidsmartmarket.adabter.CategoriesAdapterInner
import com.example.androidsmartmarket.databinding.FragmentDetailsBinding
import com.example.androidsmartmarket.model.Category
import dagger.hilt.android.AndroidEntryPoint


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
        val id = arguments?.getLong("orderIDArgument")
        Log.d("DDDDDDDDDDD",id.toString())

       categoryViewModel.allCategories.observe(viewLifecycleOwner) {
            it.data.categories.forEach {
                val strParentId = it.parent_id.toString()
                if (id == strParentId.toLong()) {
                    arrayCategory.add(it)
                    Log.d("SELECTITEMID", it.toString())
                }
            }
           adapter!!.setItems(arrayCategory)
           Log.d("ArrayCategoriessa", arrayCategory.toString())
        }
       categoryViewModel.apiGetCategoryies()
    }

    @SuppressLint("SuspiciousIndentation")
    private fun listItemClicked(seletedItemE: Long) {
      val bundle = Bundle()
        bundle.putLong("orders", seletedItemE)
        Log.d("order", bundle.toString())
        findNavController().navigate(R.id.action_navigation_details_to_navigation_detailsRv, bundle)
        Log.d("VARCHARVAR",seletedItemE.toString())
    }
    override fun onResume() {
        clear()
        super.onResume()
    }
    @SuppressLint("NotifyDataSetChanged")
    fun clear() {
        arrayCategory.clear(); // clear list
        Log.d("ArrayCategoriess", arrayCategory.toString())
        adapter!!.notifyDataSetChanged()
        binding.rvCategoryIn.removeAllViewsInLayout();
    }
}