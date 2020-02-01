package com.delimce.cabifymobilechallenge.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.delimce.cabifymobilechallenge.data.Products
import com.delimce.cabifymobilechallenge.repositories.ProductRepository

class ProductViewModel : ViewModel() {

    private val mutableLiveData: MutableLiveData<Products>?
    private val productsRepository: ProductRepository = ProductRepository()

    init {
        mutableLiveData = productsRepository.getProducts()
    }

    fun getProductsRepository(): MutableLiveData<Products>? {
        return productsRepository.getProducts()
    }

}