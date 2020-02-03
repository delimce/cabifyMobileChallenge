package com.delimce.cabifymobilechallenge.ui.fragments

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.delimce.cabifymobilechallenge.R
import com.delimce.cabifymobilechallenge.data.Product
import com.delimce.cabifymobilechallenge.data.Products
import com.delimce.cabifymobilechallenge.ui.MainActivity
import com.delimce.cabifymobilechallenge.ui.adapters.MyProductRecyclerViewAdapter
import com.delimce.cabifymobilechallenge.utils.CouchbaseHelper
import com.delimce.cabifymobilechallenge.viewmodels.ProductViewModel
import com.google.android.material.snackbar.Snackbar

class ProductFragment : Fragment() {

    private var columnCount = 1
    private lateinit var db: CouchbaseHelper
    private lateinit var productList: ArrayList<Product>
    private lateinit var productViewModel: ProductViewModel

    private var listener: MainActivity.OnListFragmentInteractionListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        db = CouchbaseHelper(context!!)
        productViewModel = ViewModelProvider(this).get(ProductViewModel::class.java)
        productList = ArrayList()
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

                view.addOnItemTouchListener(
                    MainActivity.RecyclerTouchListener(
                        this.activity!!,
                        view,
                        object : MainActivity.ClickListener {
                            override fun onClick(view: View, position: Int) {
                                val product = productList[position]
                                ///set parameters
                                val prod = db.createDoc()
                                prod.setString("code", product.code)
                                prod.setString("name", product.name)
                                prod.setDouble("price", product.price)
                                prod.setString("type", "product")
                                db.saveDoc(prod)

                                Snackbar.make(
                                    view,
                                    "New item of ${product.name} added!",
                                    Snackbar.LENGTH_LONG
                                )
                                    .setAction("Action", null).show()
                            }

                            override fun onLongClick(view: View, position: Int) {

                            }
                        })
                )
            }
        })

        return view
    }


}
