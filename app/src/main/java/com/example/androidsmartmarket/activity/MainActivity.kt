package com.example.androidsmartmarket.activity

import android.annotation.SuppressLint
import android.graphics.Color
import android.hardware.camera2.params.ColorSpaceTransform
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.viewpager.widget.ViewPager
import com.example.androidsmartmarket.R
import com.example.androidsmartmarket.adabter.ViewPagerAdapter
import com.example.androidsmartmarket.fragments.*
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    val TAG = MainActivity::class.java.simpleName
    var index = 0
    lateinit var homeActivity: HomeActivity
    lateinit var personActivity: PersonActivity
    lateinit var shopingCartActivity: ShopingCartActivity
    lateinit var widgetActivity: WidgetActivity
    lateinit var viewPager : ViewPager
    lateinit var bottomNavigationView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
    }

        override fun scrollToUpload(){
            index = 2
            scrollByIndex(index)
        }

    override fun scrollToHome() {
        index = 0
        scrollByIndex(index)
    }


    private fun scrollByIndex(index:Int){
        viewPager.setCurrentItem(index)
        bottomNavigationView.getMenu().getItem(index).setChecked(true);
    }

    private fun initViews() {
    viewPager = findViewById(R.id.viewPager)
        bottomNavigationView = findViewById(R.id.bottomNavigationView)

        bottomNavigationView.setOnItemSelectedListener { item ->
            when(item.itemId){
                R.id.homeFragment -> {
                    viewPager.setCurrentItem(0)
                }
                R.id.widgets_home->{
                    viewPager.setCurrentItem(1)
                }
                R.id.shoping_card_home->{
                    viewPager.setCurrentItem(2)
                }
                R.id.person_outline_home->{
                    viewPager.setCurrentItem(3)
                }
            }
            true
        }
        viewPager.addOnAdapterChangeListener(object : ViewPager.OnAdapterChangeListener {
    override fun onPageScrolled(
        position: Int,
        positionOffset:Float,
        positionOffsetPixels:Int
    ){
    }
            override fun onPageSelected(position: Int){
                index = position
                bottomNavigationView.getMenu().getItem(index).setChecked(true)
            }

            override fun onPageScrollStateChanged(state: Int) {

            }
        })

        homeActivity = HomeActivity()
        personActivity = PersonActivity()
        shopingCartActivity = ShopingCartActivity()
        widgetActivity = WidgetActivity()

    }
    private fun setUppViewPacer(viewPager: ViewPager){
    val adapter = ViewPagerAdapter(supportFragmentManager)
        adapter.addFragment(homeActivity)
        adapter.addFragment(SearchActivity())
        adapter.addFragment(personActivity)
        adapter.addFragment(shopingCartActivity)
        adapter.addFragment(widgetActivity)
        viewPager.adapter = adapter
    }
    @SuppressLint("ObsoleteSdkInt")
    fun setTransparentStatusBar(){
    window.decorView.systemUiVisibility =
        View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            window.statusBarColor = Color.TRANSPARENT
        }
    }
}
