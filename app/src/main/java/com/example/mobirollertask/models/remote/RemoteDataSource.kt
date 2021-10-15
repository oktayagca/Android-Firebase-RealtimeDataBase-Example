package com.example.mobirollertask.models.remote

import com.example.mobirollertask.models.entity.Product
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val firebaseService:FirebaseService
) {
    suspend fun addProduct(product: Product) = firebaseService.addProduct(product)

    suspend fun getProducts() = firebaseService.getProducts()
}