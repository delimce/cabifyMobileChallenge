package com.delimce.cabifymobilechallenge.networking
import com.delimce.cabifymobilechallenge.data.Product
import retrofit2.Call
import retrofit2.http.*

interface CabifyApi {
    @GET("bins/4bwec")
    fun getProducts(): Call<List<Product>>
}