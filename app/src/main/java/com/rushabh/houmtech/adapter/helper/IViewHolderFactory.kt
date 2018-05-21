package com.rushabh.houmtech.adapter.helper

import android.view.ViewGroup

interface IViewHolderFactory {

    fun onCreateView(parent:ViewGroup,type:Int): ParentViewHolder
}