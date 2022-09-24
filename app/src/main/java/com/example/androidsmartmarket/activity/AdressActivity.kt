package com.example.androidsmartmarket.activity

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.RelativeLayout
import androidx.activity.viewModels
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidsmartmarket.R
import com.example.androidsmartmarket.activity.viewmodel.RegionViewModel
import com.example.androidsmartmarket.adabter.RegionAdapter
import com.example.androidsmartmarket.adabter.StreetAdapter
import com.example.androidsmartmarket.databinding.ActivityAdressBinding
import com.example.androidsmartmarket.manager.PrefsManager
import com.example.androidsmartmarket.model.Datum
import com.example.androidsmartmarket.model.District
import com.google.android.material.bottomsheet.BottomSheetBehavior
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AdressActivity : BaseActivity() {
    var context : Context? = null
    private lateinit var binding: ActivityAdressBinding
    var regionAdapter : RegionAdapter? = null
    var streetAdapter : StreetAdapter? = null
    var isTester = true
    var localeAddress = ""
    val regionViewModel: RegionViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAdressBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initViews()
    }

    @SuppressLint("ResourceAsColor")
    private fun initViews() {
        context = applicationContext
        binding.fragmentDrawer.rvShared.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false)
        binding.fragmentDrawerSec.rvShared.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false)
        regionAdapter = RegionAdapter({ seletedItem: Datum -> listItemClicked(seletedItem)})
        streetAdapter = StreetAdapter({ seletedItem: District -> listItemClickedS(seletedItem)})
        binding.fragmentDrawer.rvShared.adapter = regionAdapter
        binding.fragmentDrawerSec.rvShared.adapter = streetAdapter
        regionViewModel.apiGetRegions("ru")
        regionViewModel.allPosts.observe(this,{
            Log.d("REGION",it.data.toString())
            regionAdapter!!.setItems(it.data)
        })

        binding.apply {
            val bottomSheetBehavior = BottomSheetBehavior.from<RelativeLayout>(fragmentDrawer.relativeSheet)
            tvRegion.setOnClickListener {
                bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED)
                if (bottomSheetBehavior.equals(true))bgColor.setBackgroundColor(Color.GRAY.and(R.color.green))
                val bottomSheetBehavior = BottomSheetBehavior.from<RelativeLayout>(fragmentDrawerSec.relativeSheet)
                bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED)
            }

            val bottomSheetBehaviorSec = BottomSheetBehavior.from<RelativeLayout>(fragmentDrawerSec.relativeSheet)
            tvOblast.setOnClickListener {
                bottomSheetBehaviorSec.setState(BottomSheetBehavior.STATE_EXPANDED)
                if (bottomSheetBehaviorSec.equals(true))bgColor.setBackgroundColor(R.color.green)
                val bottomSheetBehavior = BottomSheetBehavior.from<RelativeLayout>(fragmentDrawer.relativeSheet)
                bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED)
            }
            btnSave.setOnClickListener {
                context?.let { PrefsManager.getInstance(it) }!!.saveData("address",localeAddress)
                backToFinish()
            }
        }
    }

    fun backToFinish(){
        var address : String? = PrefsManager.getInstance(context!!)!!.getData("address")
        var intent = Intent()
        intent.putExtra("result",address)
        setResult(Activity.RESULT_OK,intent)
        finish()
    }

    private fun listItemClickedS(seletedItem: District) {
        binding.tvRgOb.text = seletedItem.name
        val bottomSheetBehavior = BottomSheetBehavior.from<RelativeLayout>(binding.fragmentDrawerSec.relativeSheet)
        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED)
    }

    private fun listItemClicked(seletedItem: Datum) {
        Log.d("DATUM",seletedItem.districts.toString())
        binding.tvRgName.text = seletedItem.name
        localeAddress = seletedItem.name
        val bottomSheetBehavior = BottomSheetBehavior.from<RelativeLayout>(binding.fragmentDrawer.relativeSheet)
        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED)
        streetAdapter!!.setItems(seletedItem.districts)
    }
}