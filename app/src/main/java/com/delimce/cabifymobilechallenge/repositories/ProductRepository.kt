package com.delimce.cabifymobilechallenge.repositories

import androidx.lifecycle.MutableLiveData
import com.delimce.cabifymobilechallenge.data.Products
import com.delimce.cabifymobilechallenge.networking.RetrofitService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProductRepository {


    fun getProducts(): MutableLiveData<Products>? {
        val products = MutableLiveData<Products>()
        val api = RetrofitService()
        api.getCabifyProducts().enqueue(object : Callback<Products> {
            override fun onResponse(
                call: Call<Products?>?,
                response: Response<Products?>
            ) {
                if (response.isSuccessful) {
                    products.value = response.body()
                }
            }

            override fun onFailure(call: Call<Products?>?, t: Throwable?) {
                products.value = null
            }
        })
        return products
    }

}