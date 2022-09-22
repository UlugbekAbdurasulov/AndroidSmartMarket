package com.example.androidsmartmarket.adabter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.androidsmartmarket.R
import com.example.androidsmartmarket.activity.main.home.HomeFragment
import com.example.androidsmartmarket.databinding.ItemLayoutDetailsBinding
import com.example.androidsmartmarket.databinding.ItemLayoutSearchBinding
import com.example.androidsmartmarket.databinding.ItemLayoutTechnicalsBinding
import com.example.androidsmartmarket.model.*
import java.text.DecimalFormat
import java.text.NumberFormat

class SearchAdapter(private val clickListener:(id : Long)->Unit): RecyclerView.Adapter<SearchViewHolder>() {

    private var items = mutableListOf<Product>()
    @SuppressLint("NotifyDataSetChanged")
    fun setItems(items: List<Product>){
        this.items = items.toMutableList()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemLayoutSearchBinding.inflate(inflater, parent, false)
        return SearchViewHolder(binding)
    }
    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        var id : Long = 0
        val movie = items[position]
        Glide
            .with(holder.itemView)
            .load(movie.main_photo)
            .into(holder.binding.imgSearch)

        holder.binding.apply {
            tvPrice.text = movie.price.toString()
            tvTitle.text = movie.name
        }
        holder.binding.userTitle.setOnClickListener {
            clickListener(movie.id)
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

}
class SearchViewHolder(val binding: ItemLayoutSearchBinding) : RecyclerView.ViewHolder(binding.root) {
}