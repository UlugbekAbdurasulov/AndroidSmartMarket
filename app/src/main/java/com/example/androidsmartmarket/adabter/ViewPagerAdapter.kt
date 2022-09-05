package com.example.androidsmartmarket.adabter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.androidsmartmarket.fragments.HomeActivity
import com.example.androidsmartmarket.fragments.PersonActivity
import com.example.androidsmartmarket.fragments.SearchActivity
import com.example.androidsmartmarket.fragments.ShopingCartActivity

class ViewPagerAdapter(manager: FragmentManager?): FragmentPagerAdapter(manager!!) {
    private val mFragmentList: MutableList<Fragment> = ArrayList()

    override fun getItem(position: Int): Fragment {
        return mFragmentList[position]
    }

    override fun getCount(): Int {
        return mFragmentList.size
    }
    fun addFragment(fragment: SearchActivity){
        mFragmentList.add(fragment)
    }
}

private fun <E> MutableList<E>.add(element: SearchActivity) {

}

private fun <E> MutableList<E>.add(element: HomeActivity) {

}

private fun Any.add(fragment: ShopingCartActivity) {

}


