package com.delimce.cabifymobilechallenge.data

import com.github.kittinunf.fuel.core.ResponseDeserializable
import com.google.gson.Gson

data class Product(
    val code:String,
    val name:String,
    val price:Double
) {
    class Deserializer : ResponseDeserializable<Array<Product>> {
        override fun deserialize(content: String): Array<Product>? = Gson().fromJson(content, Array<Product>::class.java)
    }
}