package com.rushabh.houmtech.adapter

import android.content.Context
import android.view.View
import com.rushabh.houmtech.adapter.helper.ParentRecyclerViewAdapter
import com.rushabh.houmtech.adapter.helper.ParentViewHolder
import com.rushabh.houmtech.adapter.helper.ViewHolderFactoryImpl
import com.rushabh.houmtech.adapter.viewholders.FooterItemViewHolder

class FooterViewAdapter(internal var c:Context): ParentRecyclerViewAdapter<FooterItemViewHolder>(c,true) {
    override fun getItemCount(): Int {
        return if (isEnabled)  1 else 0
    }

    override fun getItemViewType(position: Int): Int {
        return ViewHolderFactoryImpl.FOOTER_ITEM
    }

}