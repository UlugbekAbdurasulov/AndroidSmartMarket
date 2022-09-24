package com.example.androidsmartmarket.adabter

import android.annotation.SuppressLint
import android.graphics.Region
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.androidsmartmarket.R
import com.example.androidsmartmarket.activity.main.home.HomeFragment
import com.example.androidsmartmarket.databinding.ItemLayoutDetailsBinding
import com.example.androidsmartmarket.databinding.ItemLayoutRegionBinding
import com.example.androidsmartmarket.databinding.ItemLayoutStreetBinding
import com.example.androidsmartmarket.databinding.ItemLayoutTechnicalsBinding
import com.example.androidsmartmarket.model.*
import java.text.DecimalFormat
import java.text.NumberFormat

class StreetAdapter(private val clickListener:(District)->Unit): RecyclerView.Adapter<Street>() {

    private var items = mutableListOf<District>()
    @SuppressLint("NotifyDataSetChanged")
    fun setItems(items: List<District>){
        this.items = items.toMutableList()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Street {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemLayoutStreetBinding.inflate(inflater, parent, false)
        return Street(binding)
    }
    override fun onBindViewHolder(holder: Street, position: Int) {
        var id : Long = 0
        val movie = items[position]
        holder.binding.rgName.text = movie.name
        holder.binding.llPosts.setOnClickListener {
            clickListener(movie)
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

}
class Street(val binding: ItemLayoutStreetBinding) : RecyclerView.ViewHolder(binding.root) {
}