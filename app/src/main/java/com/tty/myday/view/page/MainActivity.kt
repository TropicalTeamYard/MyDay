package com.tty.myday.view.page

import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.annotation.NonNull
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.tty.myday.R
import com.tty.myday.R.*
import com.tty.myday.data.DataSource
import com.tty.myday.util.TagConst
import com.tty.myday.view.adapter.MainFragmentAdapter
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() , BottomNavigationView.OnNavigationItemSelectedListener, ViewPager.OnPageChangeListener {

    private lateinit var adapter:MainFragmentAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout.activity_main)
        adapter = MainFragmentAdapter(supportFragmentManager)
        contentView.adapter = adapter
        contentView.isScroll = true

        contentView.addOnPageChangeListener(this)
        navigation.setOnNavigationItemSelectedListener(this)

        DataSource.initIfNotLoadAsync(this)
    }

    //region events
    override fun onNavigationItemSelected(@NonNull menuItem: MenuItem): Boolean {
        return when(menuItem.itemId){
            R.id.navigation_home->{
                contentView.currentItem = 0
                true
            }
            R.id.navigation_schedule->{
                contentView.currentItem = 1
                true
            }
            R.id.navigation_team->{
                contentView.currentItem = 2
                true
            }
            R.id.navigation_news->{
                contentView.currentItem = 3
                true
            }
            R.id.navigation_my ->{
                contentView.currentItem = 4
                true
            }
            else -> false
        }
    }

    override fun onPageScrollStateChanged(p0: Int) {

    }

    override fun onPageScrolled(p0: Int, p1: Float, p2: Int) {
    }

    override fun onPageSelected(p0: Int) {
        Log.d(TagConst.UI,"main::页面第${p0}项被选中")

        tbx_main_title.text = when(p0){
            0-> getString(R.string.title_home)
            1->getString(R.string.title_schedule)
            2->getString(R.string.title_team)
            3-> getString(R.string.title_news)
            4->getString(R.string.title_my)
            else->"unset"
        }

        navigation.selectedItemId = when(p0){
            0-> R.id.navigation_home
            1->R.id.navigation_schedule
            2->R.id.navigation_team
            3->R.id.navigation_news
            4->R.id.navigation_my
            else -> -1
        }

    }

//    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
//        return super.onCreateOptionsMenu(menu)
//    }
//
//    override fun menu

}
