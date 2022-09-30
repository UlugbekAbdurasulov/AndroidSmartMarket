package com.example.androidsmartmarket.adabter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.androidsmartmarket.R
import com.example.androidsmartmarket.activity.main.home.HomeFragment
import com.example.androidsmartmarket.databinding.ItemLayoutDetailsBinding
import com.example.androidsmartmarket.databinding.ItemLayoutTechnicalsBinding
import com.example.androidsmartmarket.model.*
import java.text.DecimalFormat
import java.text.NumberFormat

class CategoryDetailsAdapter(): RecyclerView.Adapter<CategoryDetailsViewHolder>() {

    private var items = mutableListOf<Photo>()
    @SuppressLint("NotifyDataSetChanged")
    fun setItems(items: List<Photo>){
        this.items = items.toMutableList()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryDetailsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemLayoutDetailsBinding.inflate(inflater, parent, false)
        return CategoryDetailsViewHolder(binding)
    }
    override fun onBindViewHolder(holder: CategoryDetailsViewHolder, position: Int) {
        var id : Long = 0
        val movie = items[position]
        Glide
            .with(holder.itemView)
            .load(movie.url)
            .placeholder(R.drawable.ic_launcher_background)
            .error(R.drawable.ic_launcher_background)
            .into(holder.binding.userTitle)
    }

    override fun getItemCount(): Int {
        return items.size
    }

}
class CategoryDetailsViewHolder(val binding: ItemLayoutDetailsBinding) : RecyclerView.ViewHolder(binding.root) {
}