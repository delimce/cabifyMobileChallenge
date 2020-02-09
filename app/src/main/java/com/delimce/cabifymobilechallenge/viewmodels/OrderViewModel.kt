package com.delimce.cabifymobilechallenge.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.delimce.cabifymobilechallenge.data.Order
import com.delimce.cabifymobilechallenge.data.OrderDetail
import com.delimce.cabifymobilechallenge.data.Product
import com.delimce.cabifymobilechallenge.repositories.OrderRepository

class OrderViewModel : ViewModel() {

    private var liveOrder:MutableLiveData<Order> = MutableLiveData()
    private val orderRepository: OrderRepository = OrderRepository()

    fun setOrder(product:Product){
        liveOrder = orderRepository.updateOrder(product)
    }

    fun getOrder(): MutableLiveData<Order> {
        liveOrder.value = orderRepository.getOrder()
        return liveOrder
    }

    fun getOrderDetails(): List<OrderDetail>? {
        return orderRepository.getOrder().details
    }

    fun removeItemOrder(code:String){
        orderRepository.removeItem(code)
        liveOrder.value = orderRepository.getOrder()
    }

    fun resetOrder(){
        orderRepository.resetOrderDetails()
        liveOrder.value = orderRepository.getOrder()
    }
}

