package com.example.androidsmartmarket.activity

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.util.Log
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

            //    tvPrice.text = "${member.price} UZS"
            tvNames.text = member.name
            tvPriceOld.setText(Html.fromHtml(member.technical_parameters))
            tvUnit.text = member.unit
            tvUnitId.text = member.min_amount.toString()
            tvExpirationLife.text = member.expiration_life
            tvFreeServiceLife.text = member.free_service_life
            tvAyear.text = member.ayear.toString()
            tvMakeName.text = member.country_name
            tvNameSeller.text = member.seller.name
            tvAdressSeller.text = member.seller.address
            Log.d("tvTechnicalParameters",tvPriceOld.text.toString())
            // tvPriceT.text = "${member.price} UZS"

            var s = (String.format("%,d", member.price)).replace(',', ' ')
            binding.tvPrice.text = "${s} UZS"
            var d = (String.format("%,d", member.price)).replace(',', ' ')
            binding.tvPriceT.text = "${d} UZS"
            binding.btnCall.setOnClickListener {
                openCallContact(member.seller.mobile_phone)
            }
            //  tvName.text = member.name
        }

    }

    private fun openCallContact(tvCall: String) {
        val callIntent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+ Uri.encode(tvCall)))
        startActivity(callIntent)
    }

/*    private fun initviews(){
        binding.tvShows.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false)
        val members = intent.getSerializableExtra("Sellers") as Sellers
        binding.apply {
            tvName.text = members.name
        }
    }*/
}

