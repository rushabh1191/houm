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
        page_view.offscreenPageLimit=listOfPages.size-1
        page_tabs.offscreenPageLimit=listOfPages.size-1

        pageAdapter = AppPageAdapter(supportFragmentManager, listOfPages)
        page_view.adapter = pageAdapter

        tabAdapter = AppTabAdapter(this)
        tabAdapter.pager = page_view

        page_tabs.adapter = tabAdapter
        tabAdapter.onChangePositionRequestListener = changePositionListener

        page_tabs.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {

            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

            }

            override fun onPageSelected(position: Int) {
                page_view.setCurrentItem(position)
            }

        })

        page_view.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
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

        page_view.setPadding(pagePadding, 0, pagePadding, 0);
        page_view.setClipToPadding(false);
        page_view.pageMargin = 10

    }

    val changePositionListener = object : IResultListener<Int> {
        override fun onResult(t: Int) {
            page_view.setCurrentItem(t)
        }

    }
}
