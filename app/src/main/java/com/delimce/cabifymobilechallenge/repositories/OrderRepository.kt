package com.delimce.cabifymobilechallenge.repositories

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.couchbase.lite.*
import com.couchbase.lite.DataSource.database
import com.couchbase.lite.Function
import com.delimce.cabifymobilechallenge.data.*
import com.delimce.cabifymobilechallenge.utils.CouchbaseHelper


class OrderRepository {

    var db: CouchbaseHelper = CouchbaseHelper(context)

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
        return getProductListBeta()
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
        if(!details.isNullOrEmpty()){
            order.details = details
            order.subtotal = getOrderTotal()
            order.discounts = getApplyDiscounts(details)
            order.discountTotal = getOrderTotalDiscount(details)
            order.total = order.subtotal - order.discountTotal
        }

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

    companion object {

        private lateinit var context: Context

        fun setContext(ctx: Context) {
            context = ctx
        }
    }


    fun getProductListBeta(): List<OrderDetail> {
        val detailList = java.util.ArrayList<OrderDetail>()
        val query = QueryBuilder
            .select(
                SelectResult.expression(Function.count(Expression.string("*"))),
                SelectResult.property("code"),
                SelectResult.property("name"),
                SelectResult.property("price")
            )
            .from(DataSource.database(db.database))
            .where(Expression.property("type").equalTo(Expression.string("product")))
            .groupBy(Expression.property("code"))

        try {
            val rs = query.execute()
            rs.forEach {
                val detail = OrderDetail()
                detail.quantity = it.getInt("$1")
                detail.code = it.getString("code")
                detail.name = it.getString("name")
                detail.total = it.getDouble("price") * detail.quantity
                detailList.add(detail)
            }
        } catch (e: CouchbaseLiteException) {
            Log.e(LogDomain.ALL.toString(), e.toString())
        } finally {
            return detailList.toList()
        }
    }

    /**
     * delete order items
     */
    fun resetOrderDetails(): MutableLiveData<Order> {
        val result = MutableLiveData<Order>()
        result.value = Order()
        val query: Query = QueryBuilder.select(SelectResult.expression(Meta.id))
            .from(database(db.database))
            .where(Expression.property("type").equalTo(Expression.string("product")))
        try {
            val rs = query.execute()
            rs.forEach {
                val id = it.getString(0)
                db.deleteDoc(id)
            }
        } catch (e: CouchbaseLiteException) {
            Log.e(LogDomain.ALL.toString(), e.toString())
        } finally {
            return result
        }

    }


}