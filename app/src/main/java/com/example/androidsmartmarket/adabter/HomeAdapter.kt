package com.example.androidsmartmarket.adabter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.recyclerview.widget.RecyclerView
import com.example.androidsmartmarket.activity.main.home.HomeFragment
import com.example.androidsmartmarket.activity.viewmodel.HomeViewModel
import com.example.androidsmartmarket.databinding.ItemLayoutTechnicalsBinding
import com.example.androidsmartmarket.model.*

class HomeAdapter(var homeFragment: HomeFragment): RecyclerView.Adapter<HomeViewHolder>() {

    private var items = mutableListOf<Welcomes>()
    fun setItems(items: List<Welcomes>){
        this.items = items.toMutableList()
        notifyDataSetChanged()
    }

    private var movies = mutableListOf<Datas>()
    fun setMovieList(movies: List<Datas>) {
        this.movies = movies.toMutableList()
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
        movie.data.photos.forEach {
            holder.binding.userTitle.text = it.url
        }

//        homeFragment.addProduct(id,"ru",movie.seller.region_id,movie.seller.district_id)

    }

    override fun getItemCount(): Int {
        return items.size
    }

}
class HomeViewHolder(val binding: ItemLayoutTechnicalsBinding) : RecyclerView.ViewHolder(binding.root) {
}