package com.delimce.cabifymobilechallenge.data

data class Discount(
    var description: String,
    var season: String,
    var productCode: String, //product to apply
    var value: Double //percent of discount
)