package com.example.androidsmartmarket.adabter


import android.annotation.SuppressLint
import android.util.Log
import android.view.KeyCharacterMap.load
import android.view.LayoutInflater
import android.view.PointerIcon.load
import android.view.ViewGroup
import androidx.core.view.PointerIconCompat.load
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.androidsmartmarket.databinding.ItemLayoutCategoryBinding
import com.example.androidsmartmarket.model.*
import java.lang.System.load

class CategoriesAdapter(var clickListener: (parent_id : Long) -> Unit): RecyclerView.Adapter<CatVeiwHolder>() {
    private var items = mutableListOf<Category>()
    @SuppressLint("NotifyDataSetChanged")
    fun setItems(items: List<Category>){
        this.items = items.toMutableList()
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatVeiwHolder {
     val inflater = LayoutInflater.from(parent.context)
     val binding = ItemLayoutCategoryBinding.inflate(inflater, parent,false)
   return CatVeiwHolder(binding)
    }

    override fun onBindViewHolder(holder: CatVeiwHolder, position: Int) {

        val moviie = items[position]
        if (moviie.parent_id.toInt() == 0) {
            holder.binding.tvTitleCategory.text = moviie.name
        }

        holder.binding.tvTitleCategory.setOnClickListener{
            clickListener(moviie.id)
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }
}
class CatVeiwHolder(val binding: ItemLayoutCategoryBinding) : RecyclerView.ViewHolder(binding.root) {

}