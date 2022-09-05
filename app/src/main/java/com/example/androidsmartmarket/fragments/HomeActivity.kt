package com.example.androidsmartmarket.fragments

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.androidsmartmarket.R
import com.example.androidsmartmarket.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {
    private lateinit var homeActivity: ActivityHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?):
            View {

        homeActivity = ActivityHomeBinding.bind(inflater.inflate(R.layout.activity_home, container, false))

        return homeActivity.root
    }
}