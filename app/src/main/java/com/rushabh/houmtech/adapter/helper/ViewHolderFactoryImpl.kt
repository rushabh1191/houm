package com.rushabh.houmtech.adapter.helper

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import com.rushabh.houmtech.R
import com.rushabh.houmtech.adapter.viewholders.DataViewHolder
import com.rushabh.houmtech.adapter.viewholders.FooterItemViewHolder
import java.util.zip.Inflater

class ViewHolderFactoryImpl(internal var context:Context): IViewHolderFactory {

    companion object {
        val DATA_ITEM:Int=0
        val FOOTER_ITEM:Int=1
    }
    override fun onCreateView(parent: ViewGroup, type: Int): ParentViewHolder {
        when(type){
            DATA_ITEM ->{
                return DataViewHolder(LayoutInflater.from(context).inflate(R.layout.adapter_data_view,parent,false))
            }
            FOOTER_ITEM->{
                return FooterItemViewHolder(LayoutInflater.from(context).
                        inflate(R.layout.adapter_footer_view,parent,false))
            }
        }
        return ParentViewHolder(null)
    }

}