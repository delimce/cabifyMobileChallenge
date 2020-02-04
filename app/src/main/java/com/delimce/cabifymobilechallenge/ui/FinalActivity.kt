package com.delimce.cabifymobilechallenge.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.delimce.cabifymobilechallenge.R
import com.delimce.cabifymobilechallenge.repositories.OrderRepository
import com.delimce.cabifymobilechallenge.viewmodels.OrderViewModel

class FinalActivity : AppCompatActivity() {

    private lateinit var viewModel: OrderViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_final)
        OrderRepository.setContext(this)
        viewModel = ViewModelProvider(this).get(OrderViewModel::class.java)
        viewModel.resetOrder()

    }

}
