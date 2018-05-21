package com.rushabh.houmtech.screens.fragments

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.rushabh.houmtech.R
import com.rushabh.houmtech.adapter.FooterViewAdapter
import com.rushabh.houmtech.adapter.helper.MergeAdapter
import com.rushabh.houmtech.adapter.helper.ParentRecyclerViewAdapter
import com.rushabh.houmtech.adapter.helper.ParentViewHolder
import com.rushabh.houmtech.adapter.helper.ViewHolderFactoryImpl
import com.rushabh.houmtech.adapter.viewholders.DataViewHolder
import kotlinx.android.synthetic.main.adapter_data_view.*
import kotlinx.android.synthetic.main.fragment_data.*

class DataFragment : Fragment() {

    var title: String? = null
        get() = field
        private set

    lateinit var mergeAdapter:MergeAdapter
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_data, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mergeAdapter=MergeAdapter(context!!)
        val dataListAdapter = DataListAdapter(context!!);

        val footerIteAdapter=FooterViewAdapter(context!!)

        recycler_view.layoutManager = LinearLayoutManager(context!!)
        mergeAdapter.addAdapter(dataListAdapter as ParentRecyclerViewAdapter<ParentViewHolder>)
        mergeAdapter.addAdapter(footerIteAdapter as ParentRecyclerViewAdapter<ParentViewHolder>)
        recycler_view.adapter = mergeAdapter

    }

    companion object {
        fun getInstance(title: String) =
                DataFragment().apply {
                    this.title = title
                }
    }
}


class DataListAdapter(context: Context) : ParentRecyclerViewAdapter<DataViewHolder>(context,true) {


    override fun getItemViewType(position: Int): Int {
        return ViewHolderFactoryImpl.DATA_ITEM
    }

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        holder.tv_row_title.text="Hello ${position}"
    }

    override fun getItemCount(): Int {
        return 10
    }

}
