package com.rushabh.houmtech.screens.activities

import android.os.Bundle
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import com.rushabh.houmtech.IResultListener
import com.rushabh.houmtech.R
import com.rushabh.houmtech.adapter.AppPageAdapter
import com.rushabh.houmtech.adapter.AppTabAdapter
import com.rushabh.houmtech.screens.fragments.DataFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


    private lateinit var pageAdapter: AppPageAdapter
    private lateinit var tabAdapter: AppTabAdapter

    val tabPadding:Int=400
    val pagePadding=150

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initializeViewPager()

        val listOfPages: ArrayList<DataFragment> = ArrayList()

        listOfPages.add(DataFragment.getInstance("Tab 1"))
        listOfPages.add(DataFragment.getInstance("Tab 2"))
        listOfPages.add(DataFragment.getInstance("Tab 3"))
        listOfPages.add(DataFragment.getInstance("Tab 4"))
        listOfPages.add(DataFragment.getInstance("Tab 5"))
        listOfPages.add(DataFragment.getInstance("Tab 6"))


        pageAdapter = AppPageAdapter(supportFragmentManager, listOfPages)
        view_pager.adapter = pageAdapter

        tabAdapter = AppTabAdapter(this)
        tabAdapter.pager = view_pager

        page_tabs.adapter = tabAdapter
        tabAdapter.onChangePositionRequestListener = changePositionListener

        page_tabs.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {

            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

            }

            override fun onPageSelected(position: Int) {
                view_pager.setCurrentItem(position)
            }

        })

        view_pager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

            }

            override fun onPageSelected(position: Int) {
                page_tabs.setCurrentItem(position)
                tabAdapter.centerPosition=position
                tabAdapter.notifyDataSetChanged()
            }

            override fun onPageScrollStateChanged(state: Int) {

            }

        })

    }

    fun initializeViewPager() {
        page_tabs.setPadding(tabPadding, 0, tabPadding, 0);
        page_tabs.setClipToPadding(false);
        page_tabs.pageMargin = 0

        view_pager.setPadding(pagePadding, 0, pagePadding, 0);
        view_pager.setClipToPadding(false);
        view_pager.pageMargin = 10

    }

    val changePositionListener = object : IResultListener<Int> {
        override fun onResult(t: Int) {
            view_pager.setCurrentItem(t)
        }

    }
}
