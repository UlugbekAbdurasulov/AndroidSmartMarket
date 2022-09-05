package com.example.androidsmartmarket.activity.main.category

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.androidsmartmarket.R
import com.example.androidsmartmarket.databinding.FragmentBasketBinding
import com.example.androidsmartmarket.databinding.FragmentCategoryBinding
import com.example.androidsmartmarket.databinding.FragmentHomeBinding


class CategoryFragment: Fragment(R.layout.fragment_category) {

    private val binding by viewBinding(FragmentCategoryBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    private fun initViews() {

    }
}