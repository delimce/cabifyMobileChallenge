package com.delimce.cabifymobilechallenge.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.delimce.cabifymobilechallenge.data.Order
import com.delimce.cabifymobilechallenge.data.OrderDetail
import com.delimce.cabifymobilechallenge.data.User
import com.delimce.cabifymobilechallenge.repositories.OrderRepository

class OrderViewModel : ViewModel() {

    private val orderRepository: OrderRepository = OrderRepository()

    fun getUser(): LiveData<User> {
        return orderRepository.getOrderUser()
    }

    fun getOrderDetails(): LiveData<List<OrderDetail>> {
        return orderRepository.getOrderDetail()
    }

    fun getOrder(): LiveData<Order> {
        return orderRepository.getMyOrder()
    }
}

