package com.example.androidsmartmarket.activity.main.profile

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.androidsmartmarket.R
import com.example.androidsmartmarket.databinding.FragmentHomeBinding
import com.example.androidsmartmarket.databinding.FragmentProfileBinding


class ProfileFragment: Fragment(R.layout.fragment_profile) {
    private val binding by viewBinding(FragmentProfileBinding::bind)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    private fun initViews() {

    }
}