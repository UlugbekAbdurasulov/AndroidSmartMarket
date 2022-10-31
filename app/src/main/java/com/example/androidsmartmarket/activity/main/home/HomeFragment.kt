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
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
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
import com.example.androidsmartmarket.database.entity.Product
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
    private var isTester : Boolean = false
    private val binding by viewBinding(FragmentHomeBinding::bind)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("OOOOOOO","onViewCreated")
        initViews()
    }
    @SuppressLint("SuspiciousIndentation", "NotifyDataSetChanged")
    private fun initViews() {
        homeViewModel.clearAll()
        homeViewModel.subscribers_base.observe(requireActivity(), Observer {
            Log.d("MyTag",it.toString())
        })
        binding.rvFamily.setLayoutManager(LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false))
        binding.apply {
            rvItem.layoutManager = LinearLayoutManager(requireActivity(), LinearLayoutManager.HORIZONTAL,false)
            rvComp.layoutManager = GridLayoutManager(requireActivity(),2,GridLayoutManager.HORIZONTAL,false)
//            adapter = HomeAdapter{ seletedItem: Datas -> listItemClicked(seletedItem)}
            familyAdapter = FamilyAdapter({ seletedItem: Datas -> listItemClicked(seletedItem)},{seletedItemSave: Datas,int : Int -> listItemClickedSave(seletedItemSave,int)})
            noteAdapter = NoteAdapter { seletedItem: Datas -> listItemClicked(seletedItem) }
//            rvItem.adapter = adapter
            rvFamily.adapter = familyAdapter
            rvComp.adapter = noteAdapter
        }
        progressOnn()
        val address : String? = PrefsManager.getInstance(requireContext())!!.getData("address")
        binding.getAddress.text = address
        homeViewModel.apiPostList()
        homeViewModel.allPostsrter.observe(this.viewLifecycleOwner) {
            arrayList.add(it!!.data!!)
            if (arrayList.size == 20) {
                adapter = HomeAdapter(this,arrayList)
                binding.rvItem.adapter = adapter
            }
            if (homeViewModel.allPostsrter.value!=null) {
                progressOff()
            }
            Log.d("IIIIIIIIII",arrayList.size.toString())
        }
        homeViewModel.allPostsFamily.observe(viewLifecycleOwner) {
            arrayList_FM.add(it!!.data!!)
            if (arrayList_FM.size == 20) {
                familyAdapter!!.setItems(arrayList_FM)
            }
            if (homeViewModel.allPostsFamily.value!=null) {
                progressOff()
            }
        }

//        homeViewModel.allPostsComp.observe(viewLifecycleOwner) {
//            arrayList_COMP.add(it!!.data!!)
//            if (arrayList_COMP.size == 20) {
//                noteAdapter!!.setItems(arrayList_COMP)
//            }
//            Log.d("Comp", arrayList_COMP.toString())
//            if (homeViewModel.allPostsComp.value!=null) {
//                progressOff()
//            }
//        }
        !booleans

        binding.apply {
            autoCompleteTextview2.setOnClickListener {
                val intent = Intent(requireContext(), SearchActivity::class.java)
                startActivity(intent)
            }
            setAddress.setOnClickListener {
                val intent = Intent(requireContext(),AdressActivity::class.java)
                detail.launch(intent)
            }
        }
        binding.tvFamily.setOnClickListener {
            isTester = true
            long = 121
            openFamilyCategory(long,isTester)
        }
    }

    private fun listItemClickedSave(seletedItemSave: Datas, int: Int) {
        val product = Product(null,seletedItemSave,int)
        homeViewModel.insertBase(product)
    }

    private fun openFamilyCategory(long: Long, isTester: Boolean) {
        val bundle = Bundle()
        bundle.putLong("orderIDFamily" , long)
        bundle.putBoolean("orderIdBoolean",isTester)
        findNavController().navigate(R.id.action_CategoryFragmentHome, bundle)
    }

     fun listItemClicked(seletedItem: Datas) {
        val intent = Intent(requireContext(),DetailsActivity::class.java)
        intent.putExtra("datas", seletedItem)
        val options = ActivityOptionsCompat.makeSceneTransitionAnimation(requireActivity())
        startActivity(intent,options.toBundle())
    }

    var detail = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()){ result->
        if (result.resultCode == Activity.RESULT_OK){
            val user = result.data
            val user1  = user!!.getStringExtra("result")
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

    @SuppressLint("NotifyDataSetChanged", "SuspiciousIndentation")
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
        if (adapter != null)
        adapter!!.notifyDataSetChanged()
        binding.rvItem.removeAllViewsInLayout()
        arrayList_FM.clear() // clear list
        if (familyAdapter != null)
        familyAdapter!!.notifyDataSetChanged()
        binding.rvFamily.removeAllViewsInLayout();
        arrayList_COMP.clear() // clear list
        if (noteAdapter != null)
        noteAdapter!!.notifyDataSetChanged()
        binding.rvComp.removeAllViewsInLayout()
        Log.d("SSSSSSSSS", arrayList.size.toString())
    }

}