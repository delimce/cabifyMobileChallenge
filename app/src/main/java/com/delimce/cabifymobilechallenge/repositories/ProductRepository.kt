package com.delimce.cabifymobilechallenge.repositories

import androidx.lifecycle.MutableLiveData
import com.delimce.cabifymobilechallenge.data.Product
import com.delimce.cabifymobilechallenge.networking.RetrofitService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProductRepository {


    fun getProducts(): MutableLiveData<List<Product>>? {
        val products = MutableLiveData<List<Product>>()
        val api = RetrofitService()
        api.getCabifyProducts().enqueue(object : Callback<List<Product>> {
            override fun onResponse(
                call: Call<List<Product>?>?,
                response: Response<List<Product>?>
            ) {
                if (response.isSuccessful) {
                    products.value = response.body()
                }
            }

            override fun onFailure(call: Call<List<Product>?>?, t: Throwable?) {
                products.value = null
            }
        })
        return products
    }

}