package com.delimce.cabifymobilechallenge.ui.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.delimce.cabifymobilechallenge.R
import com.delimce.cabifymobilechallenge.data.Order
import com.delimce.cabifymobilechallenge.data.OrderDetail
import com.delimce.cabifymobilechallenge.repositories.OrderRepository
import com.delimce.cabifymobilechallenge.ui.FinalActivity
import com.delimce.cabifymobilechallenge.ui.adapters.MyOrderRecyclerViewAdapter
import com.delimce.cabifymobilechallenge.utils.Utility
import com.delimce.cabifymobilechallenge.viewmodels.OrderViewModel

class OrderFragment : Fragment() {

    private var columnCount = 1
    private lateinit var viewModel: OrderViewModel
    private var listener: OnListFragmentInteractionListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        OrderRepository.setContext(context!!)
        viewModel = ViewModelProvider(this).get(OrderViewModel::class.java)
        // TODO: Use the ViewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_order_container, container, false)
        val itemList = view.findViewById(R.id.orderItems) as RecyclerView
        val orderTotal = view.findViewById(R.id.orderTotal) as TextView
        val orderPlaceButton = view.findViewById(R.id.payOrderButton) as Button
        val orderTotalDiscount = view.findViewById(R.id.orderTotalDiscount) as TextView
        val orderDetailNone = view.findViewById(R.id.OrderDetailNone) as TextView
        val orderDiscountDescriptions =
            view.findViewById(R.id.orderDiscountDescriptions) as TextView
        val orderDiscountSection = view.findViewById(R.id.orderDiscount) as LinearLayout
        orderDiscountSection.isVisible = false
        orderDetailNone.isVisible = true

        viewModel.getOrder().observe(viewLifecycleOwner, Observer<Order> { order ->

            if(!order.details.isNullOrEmpty()){
                orderDetailNone.isVisible = false
            }

            itemList.layoutManager
            with(itemList) {
                layoutManager = when {
                    columnCount <= 1 -> LinearLayoutManager(context)
                    else -> GridLayoutManager(context, columnCount)
                }
                adapter =
                    order.details?.let { it1 ->
                        MyOrderRecyclerViewAdapter(
                            it1,
                            listener
                        )
                    }
            }

            if (order.discountTotal > 0.0) {
                orderDiscountSection.isVisible = true
                orderDiscountDescriptions.text = order.discounts?.joinToString { it.description }
                orderTotalDiscount.text = Utility.getCurrency(order.discountTotal)

            }

            orderTotal.text = Utility.getCurrency(order.total)

        })


        orderPlaceButton.setOnClickListener {
            viewModel.resetOrder().observe(viewLifecycleOwner, Observer<Order> {
                val intent = Intent(this.activity, FinalActivity::class.java)
                startActivity(intent)
            })
        }

        return view
    }


    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     *
     *
     * See the Android Training lesson
     * [Communicating with Other Fragments](http://developer.android.com/training/basics/fragments/communicating.html)
     * for more information.
     */
    interface OnListFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onListFragmentInteraction(item: OrderDetail?)
    }


}
