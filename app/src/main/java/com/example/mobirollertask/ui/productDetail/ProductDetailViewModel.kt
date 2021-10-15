package com.example.mobirollertask.ui.productDetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.mobirollertask.models.Repository
import com.example.mobirollertask.models.entity.Product
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

@HiltViewModel
class ProductDetailViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val repository: Repository
):ViewModel() {
    fun getProduct():LiveData<Product> {
        savedStateHandle.get<Product>("productItem").let { product->
            return liveData(Dispatchers.IO) {
                emit(product!!)
            }
        }
    }
}