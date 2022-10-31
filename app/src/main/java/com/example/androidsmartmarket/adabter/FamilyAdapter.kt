package com.example.androidsmartmarket.adabter

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.androidsmartmarket.database.entity.Product
import com.example.androidsmartmarket.databinding.ItemLayoutTechnicalBinding
import com.example.androidsmartmarket.model.Datas

class FamilyAdapter(var clickListener: (Datas) -> Unit,var clickListenerSave: (Datas,Int) -> Unit): RecyclerView.Adapter<TechnicalsViewHolder>() {

    private lateinit var baseSaveInfo: Product
    private var counter = 0
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
        val s = (String.format("%,d", movie.price)).replace(',', ' ')
        holder.binding.tvPrice.text = s
        holder.binding.tvName.text = movie.name
        holder.binding.btnSale.setOnClickListener {
            getItems(position,holder.binding,movie)
            Log.d("Stefan",position.toString())
        }
//        holder.binding.tvPriceOld.text = movie.price.toString()
//        homeFragment.addProduct(id,"ru",movie.seller.region_id,movie.seller.district_id)

    }
    private fun getItems(movie: Int, binding: ItemLayoutTechnicalBinding, movie1: Datas) {
        Log.d("ITEMPOSITIONADAPTER ",movie.toString())
        var count = 1
        binding.llCountt.visibility = View.VISIBLE
        binding.btnSale.visibility = View.GONE
        binding.tvCount.text = count.toString()
        counter++
        clickListenerSave(movie1,counter)
        binding.imgIncrement.setOnClickListener {
            count++
            counter++
            binding.tvCount.text = count.toString()
            clickListenerSave(movie1,counter)
        }
        binding.imgDecrement.setOnClickListener {
            if (count != 0) {
                --count
                binding.tvCount.text = count.toString()
            }
            if (count == 0) {
                binding.btnSale.visibility = View.VISIBLE
                binding.llCountt.visibility = View.GONE
                binding.tvCount.text = count.toString()
            }
        }
    }
    override fun getItemCount(): Int {
        return items.size
    }

}
class TechnicalsViewHolder(val binding: ItemLayoutTechnicalBinding) : RecyclerView.ViewHolder(binding.root) {

}