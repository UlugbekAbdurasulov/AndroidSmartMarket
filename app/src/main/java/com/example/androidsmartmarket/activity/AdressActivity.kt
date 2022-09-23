package com.example.androidsmartmarket.activity

import android.annotation.SuppressLint
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.RelativeLayout
import androidx.activity.viewModels
import androidx.compose.ui.graphics.Color
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidsmartmarket.R
import com.example.androidsmartmarket.activity.viewmodel.HomeViewModel
import com.example.androidsmartmarket.activity.viewmodel.RegionViewModel
import com.example.androidsmartmarket.adabter.DetailsAdapter
import com.example.androidsmartmarket.adabter.RegionAdapter
import com.example.androidsmartmarket.databinding.ActivityAdressBinding
import com.example.androidsmartmarket.databinding.ActivityDetailsBinding
import com.google.android.material.bottomsheet.BottomSheetBehavior
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AdressActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAdressBinding
    var regionAdapter : RegionAdapter? = null
    var isTester = true
    val regionViewModel: RegionViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAdressBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initViews()
    }

    @SuppressLint("ResourceAsColor")
    private fun initViews() {
        binding.fragmentDrawer.rvShared.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false)
        regionAdapter = RegionAdapter()
        binding.fragmentDrawer.rvShared.adapter = regionAdapter
        regionViewModel.apiGetRegions("ru")
        regionViewModel.allPosts.observe(this,{
            Log.d("REGION",it.data.toString())
            regionAdapter!!.setItems(it.data)
        })
        val bottomSheetBehavior = BottomSheetBehavior.from<RelativeLayout>(binding.fragmentDrawer.relativeSheet)
        binding.tvRegion.setOnClickListener {
            bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED)
            if (bottomSheetBehavior.equals(true))binding.bgColor.setBackgroundColor(R.color.green)

        }
    }
}