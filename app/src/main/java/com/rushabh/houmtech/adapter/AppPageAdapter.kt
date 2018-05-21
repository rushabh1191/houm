package com.rushabh.houmtech.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import com.rushabh.houmtech.screens.fragments.DataFragment

class AppPageAdapter(fm: FragmentManager, listOfPages: ArrayList<DataFragment>) : FragmentStatePagerAdapter(fm) {

    var list: ArrayList<DataFragment>? = listOfPages

    override fun getItem(position: Int): Fragment {
        return list!![position]
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return list!![position].title

    }
    override fun getCount(): Int {
        list?.let { return it.size }
        return 0
    }

}
