package com.rushabh.houmtech

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import android.support.v4.view.PagerAdapter
import android.support.v4.view.ViewPager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.PagerSnapHelper
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), ViewPager.OnPageChangeListener {


    lateinit var viewPagerAdapter: PageAdapter
    lateinit var viewPagerTabAdapter:PageTabAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val listOfTitles: ArrayList<String> = ArrayList()
        listOfTitles.add("Tab 1")
        listOfTitles.add("Tab 2")
        listOfTitles.add("Tab 3")
        listOfTitles.add("Tab 4")

//        page_header.setPadding(150, 0, 150, 0);
//        page_header.setClipToPadding(false);
//        page_header.pageMargin = 10


        viewPagerTabAdapter= PageTabAdapter(this,listOfTitles)
        page_header.adapter=viewPagerTabAdapter



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


class PageTabAdapter(var context: Context,var titles:ArrayList<String>):PagerAdapter() {
    val layoutInflater=context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
     return view as LinearLayout==view
    }

    override fun getCount(): Int {
        return  titles.count()
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val view=layoutInflater.inflate(R.layout.tab_title,container,false)
        container.addView(view)
        return view
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as LinearLayout)
    }
}