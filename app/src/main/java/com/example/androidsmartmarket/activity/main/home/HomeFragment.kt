package com.example.androidsmartmarket.activity.main.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.androidsmartmarket.R
import com.example.androidsmartmarket.databinding.FragmentHomeBinding


class HomeFragment: Fragment(R.layout.fragment_home) {

    private val binding by viewBinding(FragmentHomeBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    private fun initViews() {

    }
}