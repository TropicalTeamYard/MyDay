package com.tty.myday.view.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.tty.myday.view.page.*

class MainFragmentAdapter(fm: FragmentManager): FragmentPagerAdapter(fm) {
    private var pages : ArrayList<Fragment> = ArrayList()
    init{
        pages.add(HomeFragment())
        pages.add(ScheduleFragment())
        pages.add(TeamFragment())
        pages.add(NewsFragment())
        pages.add(MyFragment())
    }

    override fun getItem(p0: Int): Fragment {
        return pages[p0]
    }

    override fun getCount(): Int {
        return pages.size
    }

}