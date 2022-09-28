package com.example.androidsmartmarket.activity.main.category

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.androidsmartmarket.R
import com.example.androidsmartmarket.activity.viewmodel.CategoryViewModel
import com.example.androidsmartmarket.adabter.CategoriesAdapter
import com.example.androidsmartmarket.databinding.FragmentCategoryBinding
import com.example.androidsmartmarket.model.DatumValue
import com.example.androidsmartmarket.model.Datume
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CategoryFragment : Fragment(R.layout.fragment_category){

    var adapter: CategoriesAdapter? = null
    var arrayList : ArrayList<String> = ArrayList()
    val categoryViewModel : CategoryViewModel by viewModels()


    private val binding by viewBinding(FragmentCategoryBinding::bind)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    private fun initViews() {

        binding.rvCategory.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        adapter =
            CategoriesAdapter { seletedItem: Map<String, DatumValue> -> listItemClicked(seletedItem) }
        binding.rvCategory.adapter = adapter
        categoryViewModel.allCategory.observe(requireActivity(), {
            adapter!!.setItems(it.data)
        })

        categoryViewModel.apiGetCategory()
    }

    private fun listItemClicked(seletedItem: Map<String, DatumValue>){
        var map : HashMap<String,DatumValue> = HashMap(seletedItem)
        val bundle = bundleOf("amount" to map)
        findNavController().navigate(R.id.action_CategoryFragment, bundle)
    }



}