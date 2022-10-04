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
import com.example.androidsmartmarket.model.Category
import com.example.androidsmartmarket.model.DatumValue
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CategoryFragment : Fragment(R.layout.fragment_category){

    var adapter: CategoriesAdapter? = null
    var arrayList : ArrayList<String> = ArrayList()
    val categoryViewModel : CategoryViewModel by viewModels()
    val arrayCategoryList : ArrayList<Category> = ArrayList()

    private val binding by viewBinding(FragmentCategoryBinding::bind)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    private fun initViews() {

        binding.rvCategory.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        adapter =
            CategoriesAdapter { seletedItem: Long -> listItemClicked(seletedItem) }
        binding.rvCategory.adapter = adapter

        categoryViewModel.allCategories.observe(requireActivity(),{
            it.data.categories.forEach {
                if (it.parent_id.toInt() == 0) {
                    arrayCategoryList.add(it)
                    Log.d("CATEGORIESSArrayCateg", arrayCategoryList.toString())
                    Log.d("CATEGORIESS", it.name.toString())
                }
            }
            adapter!!.setItems(arrayCategoryList.toList())
            it.data.categories.forEach {
               var strParentId = it.parent_id.toString()
                if (strParentId.length == 6) {
                    var strSub = strParentId.substring(0,3)
                    if (strSub.equals("121"))
                        Log.d("CATEGORIESSParentId", strSub)
                }
            }
            Log.d("CATEGORIESS",it.data.toString())
        })
        categoryViewModel.apiGetCategoryies()

        categoryViewModel.allCategoriesId.observe(requireActivity(),{
            it.data.products.forEach {
                Log.d("OILAKREDIT",it.name)
            }
        })

        categoryViewModel.apiGetCategoriesId()
    }

    private fun listItemClicked(seletedItem: Long){
        Log.d("SELECTITEMID",seletedItem.toString())
        categoryViewModel.allCategories.observe(requireActivity(),{
            it.data.categories.forEach {
                var strParentId = it.parent_id.toString()
                if (seletedItem == strParentId.toLong()) {
                    Log.d("SELECTITEMID",it.toString())
                }
            }
        })
        categoryViewModel.apiGetCategoryies()
       var map : ArrayList<Category> = ArrayList()
        var bundle : Bundle = Bundle()
        bundle = bundleOf("amount" to map)
        findNavController().navigate(R.id.action_CategoryFragment, bundle)
    }

}