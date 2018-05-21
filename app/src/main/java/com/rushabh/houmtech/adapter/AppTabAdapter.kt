package com.rushabh.houmtech.adapter

import android.content.Context
import android.support.v4.content.ContextCompat
import android.support.v4.view.PagerAdapter
import android.support.v4.view.ViewPager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import com.rushabh.houmtech.IResultListener
import com.rushabh.houmtech.R

class AppTabAdapter(var context: Context) : PagerAdapter() {

    var centerPosition: Int = 0
    var onChangePositionRequestListener: IResultListener<Int>? = null
    var pager: ViewPager? = null
        set(value)  {
            if (value == null) {
                throw IllegalStateException("View pager not present")
            }
            if (value.adapter == null) {
                throw IllegalStateException("ViewPager does not have adapter")
            }
            field = value

        }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return `object` as LinearLayout == view
    }

    override fun getCount(): Int {
        return pager!!.adapter!!.count
    }

    override fun getPageWidth(position: Int): Float {
        return 1f
    }


    override fun getItemPosition(`object`: Any): Int {

        val pagePosition = (`object` as ViewGroup).tag as Int
        updateView(`object`, pagePosition)

        `object`.setOnClickListener {
            onChangePositionRequestListener?.onResult(pagePosition)
        }
        return super.getItemPosition(`object`)
    }

    fun updateView(viewGroup: ViewGroup, position: Int) {

        val tv = viewGroup.findViewById(R.id.tv_tab_title) as TextView
        tv.text = pager!!.adapter!!.getPageTitle(position)
        tv.setTextColor(ContextCompat.getColor(context, android.R.color.black))
        if (position == centerPosition) {
            tv.textSize = 30f
            tv.alpha = 1f
        } else {
            tv.alpha = 0.5f
            tv.textSize = 15f
        }
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val inflater = LayoutInflater.from(context)
        val viewInfo = inflater.inflate(R.layout.tab_title, container, false) as ViewGroup
        viewInfo.setTag(position)
        container.addView(viewInfo)
        updateView(viewInfo, position)
        return viewInfo
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as LinearLayout)
    }
}