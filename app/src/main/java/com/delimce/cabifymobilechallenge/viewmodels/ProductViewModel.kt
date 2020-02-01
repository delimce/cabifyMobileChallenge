package com.delimce.cabifymobilechallenge.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.delimce.cabifymobilechallenge.data.Product
import com.delimce.cabifymobilechallenge.repositories.ProductRepository

class ProductViewModel : ViewModel() {

    private val mutableLiveData: MutableLiveData<List<Product>>?
    private val productRepository: ProductRepository = ProductRepository()

    init {
        mutableLiveData = productRepository.getProducts()
    }

    fun getProducRepository(): MutableLiveData<List<Product>>? {
        return mutableLiveData
    }

}