package com.delimce.cabifymobilechallenge.ui.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.delimce.cabifymobilechallenge.R
import com.delimce.cabifymobilechallenge.data.Order
import com.delimce.cabifymobilechallenge.data.OrderDetail
import com.delimce.cabifymobilechallenge.repositories.OrderRepository
import com.delimce.cabifymobilechallenge.ui.FinalActivity
import com.delimce.cabifymobilechallenge.ui.MainActivity
import com.delimce.cabifymobilechallenge.ui.adapters.MyOrderRecyclerViewAdapter
import com.delimce.cabifymobilechallenge.utils.Utility
import com.delimce.cabifymobilechallenge.viewmodels.OrderViewModel
import org.jetbrains.anko.cancelButton
import org.jetbrains.anko.support.v4.alert
import org.jetbrains.anko.yesButton

class OrderFragment : Fragment() {

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
        orderDetailNone.visibility = View.VISIBLE

        viewModel.getOrder().observe(viewLifecycleOwner, Observer<Order> { order ->

            orderDetailNone.visibility = View.VISIBLE
            orderDiscountSection.visibility = View.INVISIBLE

            itemList.layoutManager
            with(itemList) {
                layoutManager = LinearLayoutManager(context)
                adapter =
                    order.details?.let { it1 ->
                        MyOrderRecyclerViewAdapter(
                            it1,
                            listener
                        )
                    }
            }

            if (order.total > 0.0) {
                orderDetailNone.visibility = View.INVISIBLE
            }

            if (order.discountTotal > 0.0) {
                orderDiscountSection.visibility = View.VISIBLE
                orderDiscountDescriptions.text = order.discounts?.joinToString { it.description }
                orderTotalDiscount.text = Utility.getCurrency(order.discountTotal)
            }

            orderTotal.text = Utility.getCurrency(order.total)

        })

        itemList.addOnItemTouchListener(
            MainActivity.RecyclerTouchListener(
                this.activity!!,
                itemList,
                object : MainActivity.ClickListener {
                    override fun onClick(view: View, position: Int) {
                        alert(R.string.remove_item) {
                            yesButton {
                                viewModel.removeItemOrder(viewModel.getOrderDetails()?.
                                    get(position)!!.code)

                            }
                            cancelButton {  }
                        }.show()
                    }

                    override fun onLongClick(view: View, position: Int) {

                    }
                })
        )


        orderPlaceButton.setOnClickListener {
            val intent = Intent(this.activity, FinalActivity::class.java)
            startActivity(intent)
        }

        return view
    }

    override fun onResume() {
        super.onResume()
        viewModel.getOrder()
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
