package com.example.androidsmartmarket.adabter


import android.annotation.SuppressLint
import android.view.KeyCharacterMap.load
import android.view.LayoutInflater
import android.view.PointerIcon.load
import android.view.ViewGroup
import androidx.core.view.PointerIconCompat.load
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.androidsmartmarket.databinding.ItemLayoutCategoryBinding
import com.example.androidsmartmarket.databinding.ItemLayoutCategoryInnerBinding
import com.example.androidsmartmarket.model.*
import java.lang.System.load

class CategoriesAdapterInner(var clickListener: (Map<String,ValueValueClass>) -> Unit): RecyclerView.Adapter<CatViewInnerHolder>() {
    private var items = mutableListOf<DatumValue>()
    @SuppressLint("NotifyDataSetChanged")
    fun setItems(items: List<DatumValue>){
        this.items = items.toMutableList()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatViewInnerHolder {
     val inflater = LayoutInflater.from(parent.context)
     val binding = ItemLayoutCategoryInnerBinding.inflate(inflater, parent,false)
   return CatViewInnerHolder(binding)
    }

    override fun onBindViewHolder(holder: CatViewInnerHolder, position: Int) {

        val moviie = items[position]

    holder.binding.tvTitleCategory.setOnClickListener{
        clickListener(moviie.value)
    }
        if (moviie.title != null) {
            holder.binding.tvTitleCategory.text = moviie.title
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }
}
class CatViewInnerHolder(val binding: ItemLayoutCategoryInnerBinding) : RecyclerView.ViewHolder(binding.root) {

}