package com.example.androidsmartmarket.activity.main.category

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
import com.example.androidsmartmarket.activity.viewmodel.CatInnerHomeViewModel
import com.example.androidsmartmarket.activity.viewmodel.CategoryViewModel
import com.example.androidsmartmarket.adabter.CategoriesAdapter
import com.example.androidsmartmarket.databinding.FragmentCategoryBinding
import com.example.androidsmartmarket.model.Category
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class CategoryFragment : Fragment(R.layout.fragment_category){
    var adapter: CategoriesAdapter? = null
  /*  var arrayList : ArrayList<String> = ArrayList()*/
    public var isTester : Boolean = false
    private val categoryViewModel : CategoryViewModel by viewModels()
    private val categoryViewModelSec : CatInnerHomeViewModel by viewModels()
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
        var getCategory = arguments?.getLong("orderIDFamily")
        Log.d("CATEGORIESSGGGG", getCategory.toString())
        if (getCategory==null) {
            categoryViewModel.allCategories.observe(requireActivity()) {
                it.data.categories.forEach {
                    if (it.parent_id.toInt() == 0) {
                        arrayCategoryList.add(it)
                        Log.d("CATEGORIESSArrayCateg", arrayCategoryList.toString())
                        Log.d("CATEGORIESSToLLLL", it.name.toString())
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
            }
            categoryViewModel.apiGetCategoryies()
        }
        var getBoolean = arguments?.getBoolean("orderIdBoolean")
        if (getBoolean == true && getCategory!=null) {
            openCategory(getBoolean,getCategory)
        }
    }
    private fun listItemClicked(seletedItem: Long){
        var bundle: Bundle = Bundle()
        bundle.putLong("orderIDArgument" , seletedItem)
        Log.d("orderIDArgument", bundle.toString())
        findNavController().navigate(R.id.action_CategoryFragment, bundle)
        categoryViewModel.apiGetCategoryies()
    }

    public fun openCategory(getBoolean: Boolean, getCategory: Long?) {
        var hashset : ArrayList<Int> = ArrayList()
        var bundle: Bundle = Bundle()
        if (getBoolean) {
            categoryViewModel.apiGetCategoriesId(getCategory!!)
            categoryViewModel.allCategoriesId.observe(requireActivity()) {
                for (i in it.data.products) {
                    for (j in i.photos) {
                        hashset.add(j.product_id.toInt())
                        break
                    }
                }
                bundle.putIntegerArrayList("orderIdArray",hashset)
                findNavController().navigate(R.id.action_CategoryFragmentSec, bundle)
            }
        }
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