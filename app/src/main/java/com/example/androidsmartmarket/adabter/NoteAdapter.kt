package com.example.androidsmartmarket.adabter

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.androidsmartmarket.R
import com.example.androidsmartmarket.activity.main.home.HomeFragment
import com.example.androidsmartmarket.activity.viewmodel.HomeViewModel
import com.example.androidsmartmarket.databinding.ItemLayoutCompBinding
import com.example.androidsmartmarket.databinding.ItemLayoutTechnicalBinding
import com.example.androidsmartmarket.databinding.ItemLayoutTechnicalsBinding
import com.example.androidsmartmarket.model.*

class NoteAdapter(var clickListener: (Datas) -> Unit): RecyclerView.Adapter<CompViewHolder>() {

    
    private var items = mutableListOf<Datas>()
    @SuppressLint("NotifyDataSetChanged")
    fun setItems(items: List<Datas>){
        this.items = items.toMutableList()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CompViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemLayoutCompBinding.inflate(inflater, parent, false)
        return CompViewHolder(binding)
    }
    override fun onBindViewHolder(holder: CompViewHolder, position: Int) {
        var id : Long = 0
        val movie = items[position]
        for (i in movie.photos) {
            if (i.is_main == true) {
                Glide
                    .with(holder.itemView)
                    .load(i.url)
                    .into(holder.binding.userTitle)
            }
        }

        holder.binding.userTitle.setOnClickListener {
            clickListener(movie)
        }
        var s = (String.format("%,d", movie.price)).replace(',', ' ')
        holder.binding.tvPrice.text = s
        holder.binding.tvName.text = movie.name
        holder.binding.btnSale.setOnClickListener {
            getItems(position,holder.binding)
            Log.d("Stefan",position.toString())
        }
//        holder.binding.tvPriceOld.text = movie.price.toString()
//        homeFragment.addProduct(id,"ru",movie.seller.region_id,movie.seller.district_id)

    }



    private fun getItems(movie: Int, binding: ItemLayoutCompBinding) {
        for (i in 0 until items.size) {
            if (i == movie) {
                var count = 1
                binding.llCoount.visibility = View.VISIBLE
                binding.btnSale.visibility = View.GONE
                binding.tvCount.text = count.toString()
                binding.imgIncrement.setOnClickListener {
                    count++
                    binding.tvCount.text = count.toString()
                }
                binding.imgDecrement.setOnClickListener {
                    if (count != 0) {
                        --count
                        binding.tvCount.text = count.toString()
                    }
                    if (count == 0) {
                        binding.btnSale.visibility = View.VISIBLE
                        binding.llCoount.visibility = View.GONE
                        binding.tvCount.text = count.toString()
                    }
                }
            }
        }
    }





    override fun getItemCount(): Int {
        return items.size
    }

}
class CompViewHolder(val binding: ItemLayoutCompBinding) : RecyclerView.ViewHolder(binding.root) {

}