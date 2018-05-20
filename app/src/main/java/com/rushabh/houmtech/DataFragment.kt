package com.rushabh.houmtech

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_data.*

class DataFragment : Fragment() {

    lateinit var adapter: DataListAdapter
    var title: String? = null
        get() = field
        private set

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_data, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = DataListAdapter(context!!);

        recycler_view.layoutManager = LinearLayoutManager(context!!)
        recycler_view.adapter = adapter

    }

    companion object {
        fun getInstance(title: String) =
                DataFragment().apply {
                    this.title = title
                }
    }
}


class DataListAdapter(var context: Context) : RecyclerView.Adapter<DataViewHolder>() {

    val layoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater


    override fun getItemCount(): Int {
        return 10
    }

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {

        val view = layoutInflater.inflate(R.layout.adapter_data_view, parent, false);

        return DataViewHolder(view)
    }

}

class DataViewHolder(override val containerView: View?) : RecyclerView.ViewHolder(containerView), LayoutContainer {

}