package com.rushabh.houmtech

import android.content.Context
import android.support.v4.view.ViewPager
import android.support.v7.widget.RecyclerView
import android.util.AttributeSet

class CusomTabsView(context: Context, attri: AttributeSet) : RecyclerView(context, attri) {

    var viewPager: ViewPager? = null
        set(value){
            field=value
        }


    fun updateViews(){

    }
}