package com.rushabh.houmtech.adapter.helper

import android.support.v7.widget.RecyclerView
import android.view.View
import kotlinx.android.extensions.LayoutContainer


open class ParentViewHolder(override val containerView: View?) : RecyclerView.ViewHolder(containerView),LayoutContainer {
    var isInitialized = false
}