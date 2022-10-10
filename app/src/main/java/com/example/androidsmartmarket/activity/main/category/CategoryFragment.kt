package com.example.androidsmartmarket.activity.main.category

import android.R.attr.data
import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class CategoryFragment : Fragment(R.layout.fragment_category){
    var adapter: CategoriesAdapter? = null
  /*  var arrayList : ArrayList<String> = ArrayList()*/
    private val categoryViewModel : CategoryViewModel by viewModels()
    private val arrayCategoryList : ArrayList<Category> = ArrayList()

    private val binding by viewBinding(FragmentCategoryBinding::bind)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }
    @SuppressLint("NotifyDataSetChanged")
    private fun initViews() {
        binding.rvCategory.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        adapter =
            CategoriesAdapter { seletedItem: Long -> listItemClicked(seletedItem) }
        binding.rvCategory.adapter = adapter
        categoryViewModel.allCategories.observe(requireActivity()) {
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
                    var strSub = strParentId.substring(0, 3)
                    if (strSub.equals("121"))
                        Log.d("CATEGORIESSParentId", strSub)
                }
            }
            Log.d("CATEGORIESS", it.data.toString())
        }
        categoryViewModel.apiGetCategoryies()
    }
    private fun listItemClicked(seletedItem: Long){
        var bundle: Bundle = Bundle()
        bundle.putLong("orderIDArgument" , seletedItem)
        Log.d("orderIDArgument", bundle.toString())
        findNavController().navigate(R.id.action_CategoryFragment, bundle)
        categoryViewModel.apiGetCategoryies()
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onPause() {
        super.onPause()
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
        arrayCategoryList.clear(); // clear list
        adapter!!.notifyDataSetChanged()
        binding.rvCategory.removeAllViewsInLayout();
    }
}