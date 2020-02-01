package com.delimce.cabifymobilechallenge.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "order")
data class Order(
    @PrimaryKey
    var number: Int = 1,
    var discount: Double = 0.0,
    var total: Double = 0.0,
    var user: String = ""

) : Serializable