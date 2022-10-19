package com.example.androidsmartmarket.activity.main.basket

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.core.app.ActivityOptionsCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.androidsmartmarket.R
import com.example.androidsmartmarket.activity.DetailsActivity
import com.example.androidsmartmarket.activity.viewmodel.BacketViewModel
import com.example.androidsmartmarket.adabter.BasketAdabter
import com.example.androidsmartmarket.databinding.FragmentBasketBinding
import com.example.androidsmartmarket.model.Datas


class BasketFragment: Fragment(R.layout.fragment_basket) {
    var adapter: BasketAdabter? = null
    private val backetViewModel : BacketViewModel by viewModels()
    private val binding by viewBinding(FragmentBasketBinding::bind)

    var arrayCategory : ArrayList<Datas> = ArrayList()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("OOOOOOO","onViewCreated")
        initViews()
    }
    private fun initViews() {
        superOnback()
        binding.rvBacket.layoutManager = GridLayoutManager(requireContext(),2)
        adapter = BasketAdabter{ seletedItem: Datas -> listItemClicked(seletedItem)}
        binding.rvBacket.adapter = adapter

        var getCategory = arguments?.getIntegerArrayList("orderIdArray")
        var arrayList : ArrayList<Int> = ArrayList(getCategory!!)
        backetViewModel.apiGetListFamily(arrayList)
        backetViewModel.allProducts.observe(viewLifecycleOwner) {
            arrayCategory.add(it!!.data!!)
            adapter!!.setItems(arrayCategory)

        }
        Log.d("GETLONGARRAY",getCategory.toString())
    }
    private fun listItemClicked(seletedItem: Datas) {
        var intent = Intent(requireContext(), DetailsActivity::class.java)
        intent.putExtra("datas", seletedItem)
        val options = ActivityOptionsCompat.makeSceneTransitionAnimation(requireActivity())
        startActivity(intent,options.toBundle())
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
        binding.rvBacket.removeAllViewsInLayout();
    }
    private fun superOnback() {
        val callback: OnBackPressedCallback =
            object : OnBackPressedCallback(true /* enabled by default */) {
                override fun handleOnBackPressed() {
                    var long : Long = 1
                    var bundle : Bundle = Bundle()
                    bundle.putLong("onBack",long)
                    // findNavController().navigate(R.id.action_Custom, bundle)
                }
            }
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, callback)
    }



}