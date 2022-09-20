package com.example.androidsmartmarket.activity

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.SnapHelper
import com.example.androidsmartmarket.adabter.DetailsAdapter
import com.example.androidsmartmarket.databinding.ActivityDetailsBinding
import com.example.androidsmartmarket.model.Datas

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

    private fun initViews() {
        binding.tvShows.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false)
        detailsAdapter = DetailsAdapter()
        val member = intent.getSerializableExtra("datas") as Datas
        binding.apply {
            tvShows.adapter = detailsAdapter
            detailsAdapter!!.setItems(member.photos)
            val snapHelper: SnapHelper = PagerSnapHelper()
            snapHelper.attachToRecyclerView(tvShows)
        }
    }
}