package com.example.mobirollertask.models.remote

import android.util.Log
import com.example.mobirollertask.models.entity.Product
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await

class FirebaseService {
    private lateinit var database: DatabaseReference

    private fun defineDB() {
        database = Firebase.database.reference
    }

     fun addProduct(product: Product): String {
        defineDB()
        var message = ""
        database.child("products").push().setValue(product).addOnSuccessListener {
            message = "Adding successful"
        }.addOnFailureListener {
            message = "Add failed"
        }
        message = "Adding successful"
        return message
    }

    suspend fun getProducts(): List<Product> {
        defineDB()
        var list: List<Product> = mutableListOf()

        try {
            list = database.child("products").get().await().children.map { snapShot ->
                snapShot.getValue(Product::class.java)!!
            }
        } catch (exception: Exception) {
            Log.v("firebase", exception.toString())
        }
        return list
    }

}

