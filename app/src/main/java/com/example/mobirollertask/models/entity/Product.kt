package com.example.mobirollertask.models.entity

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Product(
    val title: String,
    val category:String,
    val description:String,
    val price:String,
    val uploadDate:String,
): Parcelable {
    constructor() : this("","","","", "")
}
