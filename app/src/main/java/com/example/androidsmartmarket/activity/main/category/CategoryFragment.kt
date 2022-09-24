package com.example.androidsmartmarket.activity.main.category

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.androidsmartmarket.R
import com.example.androidsmartmarket.databinding.FragmentCategoryBinding
import android.content.Intent
import androidx.core.app.ActivityOptionsCompat
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidsmartmarket.activity.DetailsActivity
import com.example.androidsmartmarket.activity.viewmodel.SearchViewModel
import com.example.androidsmartmarket.adabter.CategoryAdapter
import com.example.androidsmartmarket.model.Datas
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CategoryFragment : Fragment(R.layout.fragment_category){
    var categoryAdapter : CategoryAdapter? = null
    val searchViewModel: SearchViewModel by viewModels()
    var arrayList : ArrayList<Datas> = ArrayList()
    private val binding by viewBinding(FragmentCategoryBinding::bind)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        this.initViews()
    }

    private fun initViews() {
        binding.rvCategory.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL,false)
//         categoryAdapter= CategoryAdapter {  }({ seletedItem: Long -> listItemClicked(seletedItem)})
        binding.rvCategory.adapter = categoryAdapter

    }

    private fun listItemClicked(seletedItem: Long) {
        searchViewModel.apiGetList(seletedItem)
        searchViewModel.allPostsrter.observe(requireActivity(),{
            var intent = Intent(requireContext(), DetailsActivity::class.java)
            intent.putExtra("datas", it.data)
            val options = ActivityOptionsCompat.makeSceneTransitionAnimation(requireActivity())
            startActivity(intent,options.toBundle())
            requireActivity().finish()
        })
    }
}