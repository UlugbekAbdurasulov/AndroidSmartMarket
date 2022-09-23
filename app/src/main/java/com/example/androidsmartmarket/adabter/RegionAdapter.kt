package com.example.androidsmartmarket.adabter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.androidsmartmarket.R
import com.example.androidsmartmarket.activity.main.home.HomeFragment
import com.example.androidsmartmarket.databinding.ItemLayoutDetailsBinding
import com.example.androidsmartmarket.databinding.ItemLayoutRegionBinding
import com.example.androidsmartmarket.databinding.ItemLayoutTechnicalsBinding
import com.example.androidsmartmarket.model.*
import java.text.DecimalFormat
import java.text.NumberFormat

class RegionAdapter(): RecyclerView.Adapter<RegionViewHolder>() {

    private var items = mutableListOf<Datum>()
    @SuppressLint("NotifyDataSetChanged")
    fun setItems(items: List<Datum>){
        this.items = items.toMutableList()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RegionViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemLayoutRegionBinding.inflate(inflater, parent, false)
        return RegionViewHolder(binding)
    }
    override fun onBindViewHolder(holder: RegionViewHolder, position: Int) {
        var id : Long = 0
        val movie = items[position]
        holder.binding.rgName.text = movie.name
    }

    override fun getItemCount(): Int {
        return items.size
    }

}
class RegionViewHolder(val binding: ItemLayoutRegionBinding) : RecyclerView.ViewHolder(binding.root) {
}