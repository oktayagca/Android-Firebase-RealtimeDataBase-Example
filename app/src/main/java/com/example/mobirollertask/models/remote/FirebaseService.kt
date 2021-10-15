package com.example.mobirollertask.models.remote

import com.example.mobirollertask.models.entity.Product
import com.example.mobirollertask.utils.Resource
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class FirebaseService {
    private lateinit var database: DatabaseReference

    private fun defineDB() {
        database = Firebase.database.reference
    }

    suspend fun addProduct(product: Product):String {
        defineDB()
        var message = ""
        database.child("products").push().setValue(product).addOnSuccessListener {
            message = "Adding successful"
        }.addOnFailureListener {
            message = "Add failed"
        }
        message ="Adding successful"
        return message
    }

    /*suspend fun getProducts() {
        defineDB()

        val list: MutableList<Product> = mutableListOf()
        database.child("products")
            .get().addOnSuccessListener {
                Log.i("firebase", "Got value ${it.value}")
                val children = it!!.children

                children.forEach {
                    val product = it.getValue(Product::class.java)
                    if (product != null) {
                        println(product)
                        list.add(product)
                    }
                }
            }.addOnFailureListener {
                Log.e("firebase", "Error getting data", it)
            }
    }
*/
}