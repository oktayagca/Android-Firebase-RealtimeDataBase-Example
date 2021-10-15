package com.example.mobirollertask.ui.productAdd

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.mobirollertask.models.Repository
import com.example.mobirollertask.models.entity.Product
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

@HiltViewModel
class ProductAddViewModel @Inject constructor(
    private val repository: Repository
):ViewModel(){

    fun addProduct(product:Product):LiveData<String>{
        return liveData(Dispatchers.IO) {
            emit(repository.addProduct(product))
        }
    }
}