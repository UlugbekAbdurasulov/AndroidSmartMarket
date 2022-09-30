package com.example.androidsmartmarket.adabter

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.androidsmartmarket.databinding.*
import com.example.androidsmartmarket.model.*

class CategoryInnerRvAdapter(var clickListener: (Producta) -> Unit): RecyclerView.Adapter<CategoryInnerViewHolder>() {
        private var items = mutableListOf<Producta>()
        @SuppressLint("NotifyDataSetChanged")
        fun setItems(items: List<Producta>){
            this.items = items.toMutableList()
            notifyDataSetChanged()
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryInnerViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val binding = ItemLayoutCategoryInnerRvBinding.inflate(inflater, parent,false)
            return CategoryInnerViewHolder(binding)
        }

        override fun onBindViewHolder(holder: CategoryInnerViewHolder, position: Int) {

            val uzmovi = items[position]
          /*  for (i in uzmovi.photos){
                if (i.is_main == true){
                    Glide
                        .with(holder.itemView)
                        .load(i.product_ID)
                        .into(holder.binding.url)
            }

            holder.binding.userTitle.setOnClickListener{
                clickListener(uzmovi)
            }
            }*/

         //   holder.binding.tvPrice.text = uzmovi.price
            var s = (String.format("%,d", uzmovi.price)).replace(',', ' ')
            holder.binding.tvPrice.text = s
            holder.binding.tvName.text = uzmovi.name
            holder.binding.btnSale.setOnClickListener {
                getItems(position,holder.binding)
                Log.d("Stefan",position.toString())

            }
        }










    private fun getItems(movie: Int, binding: ItemLayoutCategoryInnerRvBinding) {
        for (i in 0 until items.size) {
            if (i == movie) {
                var count = 1
                binding.llCountt.visibility = View.VISIBLE
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
                        binding.llCountt.visibility = View.GONE
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

class CategoryInnerViewHolder(val binding: ItemLayoutCategoryInnerRvBinding) : RecyclerView.ViewHolder(binding.root) {

}