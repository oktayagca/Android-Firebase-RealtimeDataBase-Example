package com.example.mobirollertask.models

import com.example.mobirollertask.models.entity.Product
import com.example.mobirollertask.models.remote.RemoteDataSource
import javax.inject.Inject

class Repository @Inject constructor(
    private val remoteDataSource: RemoteDataSource
){
    suspend fun addProduct(product:Product) = remoteDataSource.addProduct(product)

    suspend fun getProducts() = remoteDataSource.getProducts()

}