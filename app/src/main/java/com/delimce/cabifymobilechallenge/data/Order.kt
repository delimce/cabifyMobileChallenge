package com.delimce.cabifymobilechallenge.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "order")
data class Order(
    @PrimaryKey
    var number: Int = 1,
    var details: List<OrderDetail>? = null,
    var discounts: List<Discount>? = null,
    var subtotal: Double = 0.0,
    var discountTotal: Double = 0.0,
    var total: Double = 0.0,
    var user: User = User()

) : Serializable