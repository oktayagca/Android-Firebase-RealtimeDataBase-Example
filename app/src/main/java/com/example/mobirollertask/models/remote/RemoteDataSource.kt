package com.example.mobirollertask.data.remote

import com.example.mobirollertask.data.models.Product
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val firebaseService:FirebaseService,
) {
    suspend fun addProduct(product: Product) = firebaseService.addProduct(product)

    suspend fun getProducts() = firebaseService.getProducts()
}