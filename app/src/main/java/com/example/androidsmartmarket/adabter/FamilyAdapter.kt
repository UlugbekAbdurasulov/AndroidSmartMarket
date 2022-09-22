package com.example.androidsmartmarket.adabter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.androidsmartmarket.R
import com.example.androidsmartmarket.activity.main.home.HomeFragment
import com.example.androidsmartmarket.activity.viewmodel.HomeViewModel
import com.example.androidsmartmarket.databinding.ItemLayoutTechnicalBinding
import com.example.androidsmartmarket.databinding.ItemLayoutTechnicalsBinding
import com.example.androidsmartmarket.model.*

class FamilyAdapter(var clickListener: (Datas) -> Unit): RecyclerView.Adapter<TechnicalsViewHolder>() {

    
    private var items = mutableListOf<Datas>()
    @SuppressLint("NotifyDataSetChanged")
    fun setItems(items: List<Datas>){
        this.items = items.toMutableList()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TechnicalsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemLayoutTechnicalBinding.inflate(inflater, parent, false)
        return TechnicalsViewHolder(binding)
    }
    override fun onBindViewHolder(holder: TechnicalsViewHolder, position: Int) {
        var id : Long = 0
        val movie = items[position]
        movie.photos.forEach {
            if (it.is_main == true) {
                Glide
                    .with(holder.itemView)
                    .load(it.url)
                    .into(holder.binding.userTitle)
            }
        }
        holder.binding.userTitle.setOnClickListener {
            clickListener(movie)
        }
        var s = (String.format("%,d", movie.price)).replace(',', ' ')
        holder.binding.tvPrice.text = s
        holder.binding.tvName.text = movie.name
//        holder.binding.tvPriceOld.text = movie.price.toString()
//        homeFragment.addProduct(id,"ru",movie.seller.region_id,movie.seller.district_id)

    }

    override fun getItemCount(): Int {
        return items.size
    }

}
class TechnicalsViewHolder(val binding: ItemLayoutTechnicalBinding) : RecyclerView.ViewHolder(binding.root) {

}