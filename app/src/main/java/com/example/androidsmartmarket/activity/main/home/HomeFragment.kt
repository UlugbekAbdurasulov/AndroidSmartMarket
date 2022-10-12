package com.example.androidsmartmarket.activity.main.home

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityOptionsCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.androidsmartmarket.R
import com.example.androidsmartmarket.activity.AdressActivity
import com.example.androidsmartmarket.activity.DetailsActivity
import com.example.androidsmartmarket.activity.SearchActivity
import com.example.androidsmartmarket.activity.viewmodel.HomeViewModel
import com.example.androidsmartmarket.adabter.FamilyAdapter
import com.example.androidsmartmarket.adabter.HomeAdapter
import com.example.androidsmartmarket.adabter.NoteAdapter
import com.example.androidsmartmarket.databinding.FragmentHomeBinding
import com.example.androidsmartmarket.manager.PrefsManager
import com.example.androidsmartmarket.model.*
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment: Fragment(R.layout.fragment_home) {

    var adapter : HomeAdapter? = null
    var familyAdapter : FamilyAdapter? = null
    var noteAdapter : NoteAdapter? = null
    val homeViewModel: HomeViewModel by viewModels()
    var arrayList : ArrayList<Datas> = ArrayList()
    var arrayList_FM : ArrayList<Datas> = ArrayList()
    var arrayList_COMP : ArrayList<Datas> = ArrayList()
    var getHashSet : HashSet<Long> = HashSet()
    var long : Long = 0L
    var booleans = false
    public var isTester : Boolean = false
    private val binding by viewBinding(FragmentHomeBinding::bind)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("OOOOOOO","onViewCreated")
        initViews(view)
    }

    @SuppressLint("SuspiciousIndentation", "NotifyDataSetChanged")
    private fun initViews(view: View) {
        binding.rvItem.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL,false)
        binding.rvFamily.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL,false)
        binding.rvComp.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL,false)
        adapter = HomeAdapter{ seletedItem: Datas -> listItemClicked(seletedItem,view)}
        familyAdapter = FamilyAdapter { seletedItem: Datas -> listItemClicked(seletedItem, view) }
        noteAdapter = NoteAdapter { seletedItem: Datas -> listItemClicked(seletedItem, view) }
        binding.rvItem.adapter = adapter
        binding.rvFamily.adapter = familyAdapter
        binding.rvComp.adapter = noteAdapter
        progressOnn()
        var address : String? = PrefsManager.getInstance(requireContext())!!.getData("address")
        binding.getAddress.text = address
        homeViewModel.apiPostList()
        homeViewModel.allPostsrter.observe(this.viewLifecycleOwner) {
            arrayList.add(it!!.data!!)
            if (arrayList.size == 20) {
                adapter!!.setItems(arrayList)
            }
            Log.d("IIIIIIIIII",arrayList.size.toString())
        }
        homeViewModel.allPostsFamily.observe(this.viewLifecycleOwner) {
            arrayList_FM.add(it!!.data!!)
            if (arrayList_FM.size == 20) {
                familyAdapter!!.setItems(arrayList_FM)
            }
        }

        homeViewModel.allPostsComp.observe(viewLifecycleOwner) {
            arrayList_COMP.add(it!!.data!!)
            if (arrayList_COMP.size == 20) {
                noteAdapter!!.setItems(arrayList_COMP)
            }
            Log.d("Comp", arrayList_COMP.toString())
            if (homeViewModel.allPostsComp.value!=null) {
                progressOff()
            }
        }
        !booleans

        binding.apply {
            autoCompleteTextview2.setOnClickListener {
                var intent : Intent = Intent(requireContext(), SearchActivity::class.java)
                startActivity(intent)
            }
            setAddress.setOnClickListener {
                var intent : Intent = Intent(requireContext(),AdressActivity::class.java)
                detail.launch(intent)
            }
        }
        binding.tvFamily.setOnClickListener {
            isTester = true
            long = 121
            openFamilyCategory(long,isTester)
        }
    }

    private fun openFamilyCategory(long: Long, isTester: Boolean) {
        var bundle: Bundle = Bundle()
        bundle.putLong("orderIDFamily" , long)
        bundle.putBoolean("orderIdBoolean",isTester)
        findNavController().navigate(R.id.action_CategoryFragmentHome, bundle)
    }

    private fun listItemClicked(seletedItem: Datas, view: View) {
        var intent = Intent(requireContext(),DetailsActivity::class.java)
        intent.putExtra("datas", seletedItem)
        val options = ActivityOptionsCompat.makeSceneTransitionAnimation(requireActivity())
        startActivity(intent,options.toBundle())
    }

    var detail = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()){ result->
        if (result.resultCode == Activity.RESULT_OK){
            var user = result.data
            var user1  = user!!.getStringExtra("result")
            binding.getAddress.text = user1
        }
    }

    fun progressOff() {
        binding.progressBar.visibility = View.GONE
    }

    fun progressOnn() {
        binding.progressBar.visibility = View.VISIBLE
    }
/*    private fun listItemClickedd(seletedItem: Datas, view: View) {
        var intent = Intent(requireContext(),DetailsActivity::class.java)
        intent.putExtra("datas", seletedItem)
        val options = ActivityOptionsCompat.makeSceneTransitionAnimation(requireActivity())
        startActivity(intent,options.toBundle())
    }*/

    override fun onPause() {
        clear()
        super.onPause()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun clear() {
//        val size: Int = arrayList.size
//        if (size > 0) {
//            for (i in 0 until size) {
//                arrayList.removeAt(0)
//            }
//            adapter!!.notifyItemRangeRemoved(0, size)
//            binding.rvItem.removeAllViewsInLayout()
//        }
        arrayList.clear()  // clear list
        adapter!!.notifyDataSetChanged()
        binding.rvItem.removeAllViewsInLayout()
        arrayList_FM.clear() // clear list
        familyAdapter!!.notifyDataSetChanged()
        binding.rvFamily.removeAllViewsInLayout();
        arrayList_COMP.clear() // clear list
        noteAdapter!!.notifyDataSetChanged()
        binding.rvComp.removeAllViewsInLayout()
        Log.d("SSSSSSSSS", arrayList.size.toString())
    }

}