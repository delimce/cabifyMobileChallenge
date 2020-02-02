package com.delimce.cabifymobilechallenge.ui.adapters

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.delimce.cabifymobilechallenge.R
import com.delimce.cabifymobilechallenge.data.OrderDetail

import com.delimce.cabifymobilechallenge.ui.fragments.OrderFragment.OnListFragmentInteractionListener
import com.delimce.cabifymobilechallenge.utils.Utility
import kotlinx.android.synthetic.main.fragment_order_detail.view.*


class MyOrderRecyclerViewAdapter(
    private val mValues: List<OrderDetail>,
    private val mListener: OnListFragmentInteractionListener?
) : RecyclerView.Adapter<MyOrderRecyclerViewAdapter.ViewHolder>() {

    private val mOnClickListener: View.OnClickListener

    init {
        mOnClickListener = View.OnClickListener { v ->
            val item = v.tag as OrderDetail
            // Notify the active callbacks interface (the activity, if the fragment is attached to
            // one) that an item has been selected.
            mListener?.onListFragmentInteraction(item)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_order_detail, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = mValues[position]
        holder.mName.text = item.name
        holder.mQty.text = item.quantity.toString()
        holder.mPrice.text = Utility.getCurrency(item.total)

        with(holder.mView) {
            tag = item
            setOnClickListener(mOnClickListener)
        }
    }

    override fun getItemCount(): Int = mValues.size

    inner class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        val mName: TextView = mView.detailName
        val mQty: TextView = mView.detailQuantity
        val mPrice: TextView = mView.detailPrice

        override fun toString(): String {
            return super.toString() + " '" + mName.text + "'"
        }
    }
}
