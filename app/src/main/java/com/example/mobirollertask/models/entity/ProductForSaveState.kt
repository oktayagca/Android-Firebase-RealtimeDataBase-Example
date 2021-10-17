package com.example.mobirollertask.models.entity

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ProductForSaveState(
    var imageUri: String?,
    val title: String?,
    val category:String?,
    val description:String?,
    val price:String?,
): Parcelable {
    constructor() : this("","","","","")
}
