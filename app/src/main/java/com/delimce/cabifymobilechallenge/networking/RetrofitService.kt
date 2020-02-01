package com.delimce.cabifymobilechallenge.networking

import com.delimce.cabifymobilechallenge.data.Products
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitService {
    private val api: CabifyApi

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.myjson.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        api = retrofit.create(CabifyApi::class.java)
    }

    fun getCabifyProducts(): Call<Products> {
        return api.getProducts()
    }


}