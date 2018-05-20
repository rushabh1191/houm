package com.rushabh.houmtech

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import android.support.v4.view.ViewPager
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), ViewPager.OnPageChangeListener {


    lateinit var viewPagerAdapter: PageAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val listOfPages: ArrayList<DataFragment> = ArrayList()


        view_pager.setClipToPadding(false);
        view_pager.setPadding(150, 0, 150, 0);
        view_pager.pageMargin = 10

        listOfPages.add(DataFragment.getInstance("Tab 1"))
        listOfPages.add(DataFragment.getInstance("Tab 2"))
        listOfPages.add(DataFragment.getInstance("Tab 3"))
        listOfPages.add(DataFragment.getInstance("Tab 4"))


        viewPagerAdapter = PageAdapter(supportFragmentManager, listOfPages)


        view_pager.adapter = viewPagerAdapter


        view_pager.addOnPageChangeListener(this)
        onPageSelected(0)

    }

    override fun onPageScrollStateChanged(state: Int) {

    }

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

    }

    override fun onPageSelected(position: Int) {

        val titles=viewPagerAdapter.getTitle(position)
        tv_inactive_1.text=titles.first
        tv_inactive_2.text=titles.third
        tv_active.text=titles.second
    }
}


class PageAdapter(fm: FragmentManager, listOfPages: ArrayList<DataFragment>) : FragmentStatePagerAdapter(fm) {

    var list: ArrayList<DataFragment>? = listOfPages

    override fun getItem(position: Int): Fragment {
        return list!![position]
    }

    fun getTitle(position: Int):Triple<String?,String,String?>{

        var left:String?=null
        var right:String?=null
        val center:String=list!![position].title!!

        if(position>0){
            left=list!![position].title
        }
        if(position<count-1) {
            right=list!![position].title
        }
        return Triple(left,center,right)
    }
    override fun getPageTitle(position: Int): CharSequence? {
        if(position<0 || position>count-1) {
            return null
        }
        return list!![position].title

    }
    override fun getCount(): Int {
        list?.let { return it.size }
        return 0
    }

}