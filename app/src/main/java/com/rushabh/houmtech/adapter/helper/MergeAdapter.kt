package com.rushabh.houmtech.adapter.helper

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import java.util.*
import android.content.Context

class MergeAdapter(internal var context:Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    protected var mPieces: ArrayList<ParentRecyclerViewAdapter<ParentViewHolder>> = ArrayList()
    private val mObserverMap: HashMap<Int, CascadeDataSetObserver> = HashMap()
    var viewHolderFactory: IViewHolderFactory = ViewHolderFactoryImpl(context = context)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return viewHolderFactory.onCreateView(parent, viewType)
    }

    override fun getItemCount(): Int {
        try {
            var total = 0
            for (piece in mPieces) {
                total += piece.itemCount
            }
            return total
        } catch (e: ConcurrentModificationException) {
            return 0
        }

    }

    override fun getItemViewType(position: Int): Int {
        var i = position
        for (piece in mPieces) {
            val size = piece.itemCount

            if (i < size) {

                return piece.getItemViewType(i)
            }
            i = i - size
        }
        return -1
    }

    override fun getItemId(position: Int): Long {
        var position = position
        for (piece in mPieces) {
            val size = piece.itemCount

            if (position < size) {
                return piece.getItemId(position)
            }

            position -= size
        }
        return 0
    }

    fun removeAll() {
        for (piece in mPieces)
            removeDataOberver(piece)
        mPieces.clear()
    }

    fun addAdapter(adapter: ParentRecyclerViewAdapter<ParentViewHolder>) {
        mPieces.add(adapter)
        addDataOberver(adapter)
    }


    fun addAdapterToTop(adapter: ParentRecyclerViewAdapter<ParentViewHolder>) {
        mPieces.add(0, adapter)
        addDataOberver(adapter)
    }

    fun remove(adapter: ParentRecyclerViewAdapter<ParentViewHolder>) {
        mPieces.remove(adapter)
        removeDataOberver(adapter)
    }

    private fun addDataOberver(adapter: ParentRecyclerViewAdapter<ParentViewHolder>) {
        removeDataOberver(adapter)
        val observer = CascadeDataSetObserver(adapter)
        mObserverMap.put(adapter.hashCode(), observer)
        adapter.registerAdapterDataObserver(observer)
    }

    private fun removeDataOberver(adapter: RecyclerView.Adapter<*>) {
        val observer = mObserverMap.remove(adapter.hashCode())
        if (observer != null)
            adapter.unregisterAdapterDataObserver(observer)
    }

    override fun onBindViewHolder(vh: RecyclerView.ViewHolder, position: Int) {
        var i = position
        for (piece in mPieces) {
            val size = piece.itemCount
            if (i < size) {
                if (vh is ParentViewHolder) {
                    if (!vh.isInitialized) {
                        (piece).onNewViewHolder(vh)
                        vh.isInitialized = true
                    }
                }
                piece.onBindViewHolder(vh as ParentViewHolder, i)
                return
            }

            i -= size
        }
    }

    private inner class CascadeDataSetObserver(internal var adapter: ParentRecyclerViewAdapter<ParentViewHolder>)
        : RecyclerView.AdapterDataObserver() {

        override fun onChanged() {
            notifyItemRangeChanged(0, adapter.itemCount)
        }

        override fun onItemRangeChanged(positionStart: Int, itemCount: Int) {
            notifyItemRangeChanged(positionStart, itemCount)
        }

        override fun onItemRangeMoved(fromPosition: Int, toPosition: Int, itemCount: Int) {
            throw IllegalAccessError("Not implemented  onItemRangeMoved")
        }

        override fun onItemRangeInserted(positionStart: Int, itemCount: Int) {
            notifyItemRangeInserted(positionStart, itemCount)
        }

        override fun onItemRangeRemoved(positionStart: Int, itemCount: Int) {
            notifyItemRangeRemoved(positionStart, itemCount)
        }
    }
}