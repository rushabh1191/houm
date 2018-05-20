package com.rushabh.houmtech

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var viewPagerAdapter:PageAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val listOfPages:ArrayList<Fragment> = ArrayList()


        listOfPages.add(DataFragment())
        listOfPages.add(DataFragment())
        listOfPages.add(DataFragment())
        listOfPages.add(DataFragment())
        viewPagerAdapter=PageAdapter(supportFragmentManager,listOfPages)


        view_pager.adapter=viewPagerAdapter

        view_pager.pageMargin=30

    }
}


class PageAdapter(fm: FragmentManager, listOfPages: ArrayList<Fragment>) : FragmentStatePagerAdapter(fm) {

    var list: ArrayList<Fragment>? = listOfPages

    override fun getItem(position: Int): Fragment {
        return list!![position]
    }

    override fun getCount(): Int {
        list?.let { return it.size }
        return 0
    }

}