package com.delimce.cabifymobilechallenge.data

import androidx.room.Entity
import java.io.Serializable

@Entity(tableName = "order_detail")
data class OrderDetail(
    var number: Int = 1,
    var code: String = "",
    var name: String = "",
    var quantity: Int = 0,
    var total: Double = 0.0
) : Serializable