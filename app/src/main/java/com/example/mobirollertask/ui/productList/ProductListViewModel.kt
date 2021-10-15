package com.example.mobirollertask.ui.productList

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.mobirollertask.models.Repository
import com.example.mobirollertask.models.entity.Product
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

@HiltViewModel
class ProductListViewModel@Inject constructor(
    private val repository: Repository
):ViewModel() {
    fun getProducts(): LiveData<List<Product>> {
        return liveData(Dispatchers.IO) {
            emit(repository.getProducts())
        }
    }
}