package com.example.androidsmartmarket.adabter

import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.androidsmartmarket.R
import com.example.androidsmartmarket.fragments.HomeActivity
import com.example.androidsmartmarket.model.Post
import com.google.android.material.imageview.ShapeableImageView

class RecyclerviewAdabterOne(var items:ArrayList<Post>) {


    fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int){
    val post: Post = items[position]
        if (holder is PosterViewHolder) {
            val linearLayout = holder.linearLayout
            val action_imageRecyclerview_adabter1 = holder.action_imageRecyclerview_adabter1
            val acsiya_number_adabter1 = holder.acsiya_number_adabter1
            val tv_product_money_adabter1 = holder.tv_product_money_adabter1
            val tv_product_name_adabter1  = holder.tv_product_name_adabter1
            val btn_productAdabter1  = holder.btn_productAdabter1

            acsiya_number_adabter1.setText(post.title)
            tv_product_money_adabter1.setText(post.body.toUpperCase())
            tv_product_name_adabter1.setText(post.name)

            linearLayout.setOnClickListener{
            setClicklesener(post)
                false
            }
        }

    }

    private fun setClicklesener(post: Post) {
    HomeActivity()
    }


    inner class PosterViewHolder(var view:View) : RecyclerView.ViewHolder(view){
        var linearLayout : LinearLayout
        var action_imageRecyclerview_adabter1 : ShapeableImageView
        var acsiya_number_adabter1 : TextView
        var tv_product_money_adabter1 : TextView
        var tv_product_name_adabter1 : TextView
        var btn_productAdabter1 : Button




        init {
            linearLayout = view.findViewById(R.id.LinearLayout1)
            action_imageRecyclerview_adabter1 = view.findViewById(R.id.action_imageRecyclerview_adabter1)
            acsiya_number_adabter1 =  view.findViewById(R.id.acsiya_number_adabter1)
            tv_product_money_adabter1 = view.findViewById(R.id.tv_product_money_adabter1)
            tv_product_name_adabter1 = view.findViewById(R.id.tv_product_name_adabter1)
            btn_productAdabter1 = view.findViewById(R.id.btn_productAdabter1)
        }
    }
}