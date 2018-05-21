package com.rushabh.houmtech.adapter

import android.content.Context
import com.rushabh.houmtech.IResultListener
import com.rushabh.houmtech.adapter.helper.ParentRecyclerViewAdapter
import com.rushabh.houmtech.adapter.helper.ViewHolderFactoryImpl
import com.rushabh.houmtech.adapter.viewholders.FooterItemViewHolder

class FooterViewAdapter(internal var c:Context): ParentRecyclerViewAdapter<FooterItemViewHolder>(c,true) {
    var onLoadMoreRequestListener:IResultListener<Int>?=null
    override fun getItemCount(): Int {
        return if (isEnabled)  1 else 0
    }

    override fun getItemViewType(position: Int): Int {
        return ViewHolderFactoryImpl.FOOTER_ITEM
    }

    override fun onBindViewHolder(holder: FooterItemViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        holder.itemView.setOnClickListener {
            onLoadMoreRequestListener?.onResult(1)
        }
    }
}