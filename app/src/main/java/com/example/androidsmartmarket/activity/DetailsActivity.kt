package com.example.androidsmartmarket.activity

import android.annotation.SuppressLint
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.SnapHelper
import com.example.androidsmartmarket.adabter.DetailsAdapter
import com.example.androidsmartmarket.databinding.ActivityDetailsBinding
import com.example.androidsmartmarket.model.Datas
import com.example.androidsmartmarket.model.Sellers

class DetailsActivity : AppCompatActivity() {
    lateinit var context: Context
    var detailsAdapter : DetailsAdapter? = null
    private lateinit var binding: ActivityDetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initViews()
    }

    @SuppressLint("SetTextI18n")
    private fun initViews() {
        binding.tvShows.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false)
        detailsAdapter = DetailsAdapter()
        val member = intent.getSerializableExtra("datas") as Datas
     /*   val membert = intent.getSerializableExtra("seller") as Sellers*/

        binding.apply {
            tvShows.adapter = detailsAdapter
            detailsAdapter!!.setItems(member.photos)
            val snapHelper: SnapHelper = PagerSnapHelper()
            snapHelper.attachToRecyclerView(tvShows)

            tvPrice.text = "${member.price} UZS"
            tvNames.text = member.name
            tvTechnicalParameters.text = member.technicalParameters
            tvUnit.text = member.unit
            tvUnitId.text = member.min_amount.toString()
            tvExpirationLife.text = member.expirationLife
            tvFreeServiceLife.text = member.freeServiceLife
            tvAyear.text = member.ayear.toString()
            tvMakeName.text = member.makeName
            tvTv.text = member.seller.name


          //  tvName.text = member.name
        }

    }

/*    private fun initviews(){
        binding.tvShows.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false)
        val members = intent.getSerializableExtra("Sellers") as Sellers
        binding.apply {
            tvName.text = members.name
        }
    }*/
}