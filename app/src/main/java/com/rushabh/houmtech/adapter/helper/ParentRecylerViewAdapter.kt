package com.rushabh.houmtech.adapter.helper

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup

abstract class ParentRecyclerViewAdapter<T:ParentViewHolder>(
        internal var context:Context,internal var isEnabled:Boolean) : RecyclerView.Adapter<T>() {
    fun onNewViewHolder(holder: ParentViewHolder){

    }

    var viewHolderFactory:IViewHolderFactory?=null
    override fun onBindViewHolder(holder: T, position: Int) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): T {
        return viewHolderFactory!!.onCreateView(parent,viewType) as T
    }
}