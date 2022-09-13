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
import com.example.androidsmartmarket.databinding.ItemLayoutTechnicalsBinding
import com.example.androidsmartmarket.model.*

class HomeAdapter(var homeFragment: HomeFragment): RecyclerView.Adapter<HomeViewHolder>() {

    private var items = mutableListOf<Datas>()
    fun setItems(items: List<Datas>){
        this.items = items.toMutableList()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemLayoutTechnicalsBinding.inflate(inflater, parent, false)
        return HomeViewHolder(binding)
    }
    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        var id : Long = 0
        val movie = items[position]
        movie.photos.forEach {
            if (it.is_main == true) {
                holder.binding.userId.text = it.url
                Glide
                    .with(holder.itemView)
                    .load(it.url)
                    .placeholder(R.drawable.ic_launcher_background)
                    .error(R.drawable.ic_launcher_background)
                    .into(holder.binding.userTitle)
            }
        }
//        homeFragment.addProduct(id,"ru",movie.seller.region_id,movie.seller.district_id)

    }

    override fun getItemCount(): Int {
        return items.size
    }

}
class HomeViewHolder(val binding: ItemLayoutTechnicalsBinding) : RecyclerView.ViewHolder(binding.root) {
}