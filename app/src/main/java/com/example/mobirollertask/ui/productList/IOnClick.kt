package com.example.mobirollertask.ui.productList

import com.example.mobirollertask.models.entity.Product

interface IOnClick {
    fun onClick(product: Product) {
    }
}