package com.example.androidsmartmarket.adabter

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.androidsmartmarket.activity.main.home.HomeFragment
import com.example.androidsmartmarket.databinding.ItemLayoutTechnicalsBinding
import com.example.androidsmartmarket.model.*

class HomeAdapter(var homeFragment: HomeFragment,private var items: List<Datas>): RecyclerView.Adapter<HomeViewHolder>() {
//    @SuppressLint("NotifyDataSetChanged")
//    fun setItems(items: ArrayList<Datas>){
//        this.items = items.toMutableList()
//        notifyDataSetChanged()
//    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemLayoutTechnicalsBinding.inflate(inflater, parent, false)
        return HomeViewHolder(binding)
    }
    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        val movie = items[position]
        for (i in movie.photos) {
            if (i.is_main) {
                Glide
                    .with(holder.itemView)
                    .load(i.url)
                    .into(holder.binding.userTitle)
                break
            }
            holder.binding.userTitle.setOnClickListener {
                homeFragment.listItemClicked(movie)
            }

            holder.binding.btnSale.setOnClickListener {
                if (items.indexOf(movie) == position) {
                    holder.binding.llCount.visibility = View.VISIBLE
                    holder.binding.btnSale.visibility = View.GONE
                }
                Log.d("Stefan",position.toString())
                Log.d("Stefan",items.indexOf(movie).toString())
                Log.d("Stefan",itemCount.toString())
            }
        }
        val s = (String.format("%,d", movie.price)).replace(',', ' ')
        holder.binding.tvPrice.text = s
        holder.binding.tvName.text = movie.name

//        holder.binding.tvPriceOld.text = movie.price.toString()
//        homeFragment.addProduct(id,"ru",movie.seller.region_id,movie.seller.district_id)
    }

    private fun getItems(movie: Int, binding: ItemLayoutTechnicalsBinding) {
        for (i in 0 until items.size) {
            if (i == movie) {
                var count = 1
                binding.llCount.visibility = View.VISIBLE
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
                        binding.llCount.visibility = View.GONE
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
class HomeViewHolder(val binding: ItemLayoutTechnicalsBinding) : RecyclerView.ViewHolder(binding.root) {
}