package com.example.mobirollertask.data

import com.example.mobirollertask.data.models.Product
import com.example.mobirollertask.data.remote.RemoteDataSource
import javax.inject.Inject

class Repository @Inject constructor(
    private val remoteDataSource: RemoteDataSource
){
    suspend fun addProduct(product:Product) = remoteDataSource.addProduct(product)

    suspend fun getProducts() = remoteDataSource.getProducts()
}