package com.delimce.cabifymobilechallenge.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.delimce.cabifymobilechallenge.data.*

class OrderRepository {


    private lateinit var order: Order
    /**
     * dummy data
     */
    fun getOrderDetail(): MutableLiveData<List<OrderDetail>> {
        val details = MutableLiveData<List<OrderDetail>>()
        details.value = getDetails()

        return details
    }

    private fun getDetails(): List<OrderDetail> {
        return listOf(
            OrderDetail(1, "VOUCHER", "Cabify Voucher ", 3, 15.0),
            OrderDetail(2, "TSHIRT", "Cabify T-Shirt", 3, 60.0),
            OrderDetail(3, "MUG", "Cabify Coffee Mug", 1, 7.50)
        )
    }

    fun getOrderUser(): LiveData<User> {
        val user = MutableLiveData<User>()
        user.value = User()
        return user
    }


    fun getMyOrder(): MutableLiveData<Order> {
        val result = MutableLiveData<Order>()
        order = Order()
        val details = getDetails()
        order.details = details
        order.subtotal = getOrderTotal()
        order.discounts = getApplyDiscounts(details)
        order.discountTotal = getOrderTotalDiscount(details)
        order.total = order.subtotal - order.discountTotal

        result.value = order
        return result
    }

    private fun getOrderTotal(): Double {
        return (getDetails().sumByDouble { it.total })
    }


    private fun getApplyDiscounts(details: List<OrderDetail>): List<Discount> {
        val discounts = ArrayList<Discount>()

        if (DiscountRepository.is3tshirtsDiscountApplicable(details)) {
            val discount = DiscountRepository.getDiscountByCode(ProductCode.TSHIRT.name)
            discounts.add(discount)
        }

        if (DiscountRepository.is2x1VoucherDiscountApplicable(details)) {
            val discount = DiscountRepository.getDiscountByCode(ProductCode.VOUCHER.name)
            discounts.add(discount)
        }
        return discounts
    }

    private fun getOrderTotalDiscount(details: List<OrderDetail>): Double {
        var discount = 0.0
        discount += DiscountRepository.applyDiscount3tshirts(details)
        discount += DiscountRepository.applyDiscount2x1Voucher(details)
        return discount
    }


}