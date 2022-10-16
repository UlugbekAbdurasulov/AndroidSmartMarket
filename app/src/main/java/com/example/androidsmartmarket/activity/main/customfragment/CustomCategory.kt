package com.example.androidsmartmarket.activity.main.customfragment

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.core.app.ActivityOptionsCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.androidsmartmarket.R
import com.example.androidsmartmarket.activity.DetailsActivity
import com.example.androidsmartmarket.activity.viewmodel.CustomViewModel
import com.example.androidsmartmarket.adabter.CustomCateAdapter
import com.example.androidsmartmarket.databinding.FragmentCategoryCustomBinding
import com.example.androidsmartmarket.model.Datas
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CustomCategory(enabled: Boolean) : Fragment(R.layout.fragment_category_custom) {
    private val binding by viewBinding(FragmentCategoryCustomBinding::bind)
    private val categoryViewModel : CustomViewModel by viewModels()
    var adapter : CustomCateAdapter? = null
    var arrayCategory : ArrayList<Datas> = ArrayList()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("OOOOOOO","onViewCreated")
        initViews()
    }
    private fun initViews() {
        superOnback()
        binding.rvCategoriy.layoutManager = GridLayoutManager(requireContext(),2)
        adapter = CustomCateAdapter{ seletedItem: Datas -> listItemClicked(seletedItem)}
        binding.rvCategoriy.adapter = adapter
        progressOnn()
        var getCategory = arguments?.getIntegerArrayList("orderIdArray")
        var arrayList : ArrayList<Int> = ArrayList(getCategory!!)
        categoryViewModel.apiGetListFamily(arrayList)
        categoryViewModel.allProducts.observe(viewLifecycleOwner) {
            arrayCategory.add(it!!.data!!)
            adapter!!.setItems(arrayCategory)
            progressOff()
        }
        Log.d("GETLONGARRAY",getCategory.toString())
    }
    private fun listItemClicked(seletedItem: Datas) {
        var intent = Intent(requireContext(), DetailsActivity::class.java)
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

    override fun onResume() {
        clear()
        super.onResume()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun clear() {
        arrayCategory.clear(); // clear list
        Log.d("ArrayCategoriess", arrayCategory.toString())
        adapter!!.notifyDataSetChanged()
        binding.rvCategoriy.removeAllViewsInLayout();
    }

    private fun superOnback() {
        val callback: OnBackPressedCallback =
            object : OnBackPressedCallback(true /* enabled by default */) {
                override fun handleOnBackPressed() {
                    var long : Long = 1
                    var bundle : Bundle = Bundle()
                    bundle.putLong("onBack",long)
                    findNavController().navigate(R.id.action_Custom, bundle)
                }
            }
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, callback)
    }
}