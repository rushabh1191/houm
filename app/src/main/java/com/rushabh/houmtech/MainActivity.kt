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
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
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

        page_header.setPadding(400, 0, 400, 0);
        page_header.setClipToPadding(false);
        page_header.pageMargin = 10


        page_header.addOnPageChangeListener(object:ViewPager.OnPageChangeListener{
            override fun onPageScrollStateChanged(state: Int) {

            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

            }

            override fun onPageSelected(position: Int) {
                view_pager.setCurrentItem(position)
            }

        })
        viewPagerTabAdapter= PageTabAdapter(this,listOfTitles)
        page_header.adapter=viewPagerTabAdapter



        val listOfPages: ArrayList<DataFragment> = ArrayList()

        view_pager.setPadding(150, 0, 150, 0);
        view_pager.setClipToPadding(false);
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

        viewPagerTabAdapter.centerPosition=position

        viewPagerTabAdapter.notifyDataSetChanged()
        page_header.setCurrentItem(position,true)
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

    var centerPosition:Int=0
    override fun isViewFromObject(view: View, `object`: Any): Boolean {
     return `object` as LinearLayout==view
    }

    override fun getCount(): Int {
        return  titles.count()
    }

    override fun getPageWidth(position: Int): Float {
        return 1f
    }

    override fun getItemPosition(`object`: Any): Int {
        val position=super.getItemPosition(`object`)

        val pagePosition=(`object` as ViewGroup).tag as Int
        updateView(`object`,pagePosition)

        return position
    }

    fun updateView(viewGroup: ViewGroup,position: Int){

        val tv=viewGroup.findViewById(R.id.tv_tab_title) as TextView
        tv.text=titles[position]
        if(position==centerPosition){
            tv.textSize=30f
        }else{
            tv.textSize=15f
        }
    }
    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val inflater = LayoutInflater.from(context)
        val viewInfo = inflater.inflate(R.layout.tab_title, container, false) as ViewGroup
        viewInfo.setTag(position)
        container.addView(viewInfo)
        updateView(viewInfo,position)
        return viewInfo
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as LinearLayout)
    }
}