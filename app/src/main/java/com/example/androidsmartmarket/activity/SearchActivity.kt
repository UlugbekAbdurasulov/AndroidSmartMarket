package com.example.androidsmartmarket.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.activity.viewModels
import androidx.core.app.ActivityOptionsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidsmartmarket.activity.viewmodel.SearchViewModel
import com.example.androidsmartmarket.adabter.SearchAdapter
import com.example.androidsmartmarket.databinding.ActivitySearchBinding
import com.example.androidsmartmarket.model.Datas
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySearchBinding
    var searchAdapter : SearchAdapter? = null
    val searchViewModel: SearchViewModel by viewModels()
    var arrayList : ArrayList<Datas> = ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initViews()
    }

    private fun initViews() {
        binding.rvSearch.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false)
        searchAdapter = SearchAdapter({ seletedItem: Long -> listItemClicked(seletedItem)})
        binding.rvSearch.adapter = searchAdapter
        var name = ""
        binding.autoCompleteTextview2.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(s: Editable) {

            }

            override fun beforeTextChanged(s: CharSequence, start: Int,
                                           count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence, start: Int,
                                       before: Int, count: Int) {
                name = s.toString().toLowerCase()
                Log.d("SEARCH",name)
                name = name.replace("\\s".toRegex(), "")
                if (!name.equals("")) {
                    searchViewModel.apiSearchList(name)
                }
            }
        })
        searchViewModel.allPosts.observe(this,{
            Log.d("SEARCH",it.data.products.toString())
            searchAdapter!!.setItems(it.data.products)
        })

    }

    private fun listItemClicked(seletedItem: Long) {
        searchViewModel.apiGetList(seletedItem)
        searchViewModel.allPostsrter.observe(this,{
            var intent = Intent(this,DetailsActivity::class.java)
            intent.putExtra("datas", it.data)
            val options = ActivityOptionsCompat.makeSceneTransitionAnimation(this)
            startActivity(intent,options.toBundle())
            finish()
        })
    }
}