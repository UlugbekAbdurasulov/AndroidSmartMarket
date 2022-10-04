package com.example.androidsmartmarket.activity.main.details

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.androidsmartmarket.R
import com.example.androidsmartmarket.adabter.CategoriesAdapterInner
import com.example.androidsmartmarket.databinding.FragmentDetailsBinding
import com.example.androidsmartmarket.model.DatumValue


class DetailsFragment: Fragment(R.layout.fragment_details) {

    private val binding by viewBinding(FragmentDetailsBinding::bind)
    var adapter: CategoriesAdapterInner? = null
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

/*    private fun initViews() {
        binding.rvCategoryIn.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL,false)
        adapter = CategoriesAdapterInner{seletedItem: Long -> listItemClicked(seletedItem)}
        binding.rvCategoryIn.adapter = adapter
        var text = arguments?.getSerializable("amount") as ArrayList<*>
*//*        text.values.forEach{
            Log.d("STRHASHMAP",it.title.toString())
        }
        var keys : ArrayList<DatumValue> = ArrayList(text.values)
        adapter!!.setItems(keys)*//*
    }

    private fun listItemClicked(seletedItem: Long) {
        seletedItem.values.forEach {
            Log.d("VARCHARVAR",it.toString())
        }
    }*/
private fun initViews() {
    binding.rvCategoryIn.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL,false)
    adapter = CategoriesAdapterInner{seletedItem: Long -> listItemClicked(seletedItem)}
    binding.rvCategoryIn.adapter = adapter
    var text = arguments?.getSerializable("amount") as ArrayList<*>


   // adapter!!.setItems(keys)
}

    private fun listItemClicked(seletedItem: Long) {
      //  seletedItem.values.forEach {
          //  Log.d("VARCHARVAR",it.toString())
       // }
    }
}