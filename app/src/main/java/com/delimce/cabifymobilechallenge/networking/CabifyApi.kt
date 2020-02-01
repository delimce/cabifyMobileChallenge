package com.delimce.cabifymobilechallenge.networking
import com.delimce.cabifymobilechallenge.data.Products
import retrofit2.Call
import retrofit2.http.*

interface CabifyApi {
    @GET("bins/4bwec")
    fun getProducts(): Call<Products>
}