package com.example.androidsmartmarket.adabter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.recyclerview.widget.RecyclerView
import com.example.androidsmartmarket.activity.main.home.HomeFragment
import com.example.androidsmartmarket.activity.viewmodel.HomeViewModel
import com.example.androidsmartmarket.databinding.ItemLayoutTechnicalBinding
import com.example.androidsmartmarket.databinding.ItemLayoutTechnicalsBinding
import com.example.androidsmartmarket.model.*

class TechnicalsAdapter(var homeFragment: HomeFragment): RecyclerView.Adapter<TechnicalsViewHolder>() {

    private var items = mutableListOf<Datas>()
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
         holder.binding.userTitle.text = movie.seller.district_id.toString()
         movie.photos.forEach {
             holder.binding.userBody.text = it.url
//             homeFragment.addProduct(it.product_id)
        }

    }

    override fun getItemCount(): Int {
        return items.size
    }

}
class TechnicalsViewHolder(val binding: ItemLayoutTechnicalBinding) : RecyclerView.ViewHolder(binding.root) {
}