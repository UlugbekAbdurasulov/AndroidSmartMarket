package com.example.androidsmartmarket.adabter


import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.androidsmartmarket.databinding.ItemLayoutCategoryInnerBinding
import com.example.androidsmartmarket.model.*

class CategoriesAdapterInner(var clickListener: (Long) -> Unit): RecyclerView.Adapter<CatViewInnerHolder>() {
    private var items = mutableListOf<Category>()
    @SuppressLint("NotifyDataSetChanged")
    fun setItems(items: List<Category>){
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
        clickListener(moviie.id)
    }
        if (moviie.name != null) {
            holder.binding.tvTitleCategory.text = moviie.name
            Log.d("Categories", moviie.name)
        }
    }


    override fun getItemCount(): Int {
        return items.size
    }
}
class CatViewInnerHolder(val binding: ItemLayoutCategoryInnerBinding) : RecyclerView.ViewHolder(binding.root) {

}