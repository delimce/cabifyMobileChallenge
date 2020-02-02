package com.delimce.cabifymobilechallenge.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.delimce.cabifymobilechallenge.R
import com.delimce.cabifymobilechallenge.data.Product
import com.delimce.cabifymobilechallenge.data.Products
import com.delimce.cabifymobilechallenge.ui.adapters.MyProductRecyclerViewAdapter

import com.delimce.cabifymobilechallenge.viewmodels.ProductViewModel

/**
 * A fragment representing a list of Items.
 * Activities containing this fragment MUST implement the
 * [ProductFragment.OnListFragmentInteractionListener] interface.
 */
class ProductFragment : Fragment() {

    // TODO: Customize parameters
    private var columnCount = 1
    private lateinit var productList:ArrayList<Product>
    private lateinit var productViewModel: ProductViewModel

    private var listener: OnListFragmentInteractionListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        productViewModel = ViewModelProvider(this).get(ProductViewModel::class.java)
        productList =  ArrayList()
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_product_list, container, false)
        productViewModel.getProductsRepository()?.observe(viewLifecycleOwner, Observer<Products> {
            productList.addAll(it.products)
            // Set the adapter
            if (view is RecyclerView) {
                with(view) {
                    layoutManager = when {
                        columnCount <= 1 -> LinearLayoutManager(context)
                        else -> GridLayoutManager(context, columnCount)
                    }
                    adapter =
                        MyProductRecyclerViewAdapter(
                            productList,
                            listener
                        )
                }
            }
        })


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
        fun onListFragmentInteraction(item: Product?)
    }


}
