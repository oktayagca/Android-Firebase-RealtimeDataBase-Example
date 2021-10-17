package com.example.mobirollertask.models.remote

import android.net.Uri
import android.util.Log
import androidx.core.net.toUri
import com.example.mobirollertask.models.entity.Product
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import kotlinx.coroutines.tasks.await

class FirebaseService {
    private lateinit var database: DatabaseReference

    private fun defineDB() {
        database = Firebase.database.reference
    }

    fun saveProduct(product: Product): String {
        defineDB()
        var message = "Adding successful"
        database.child("products").push().setValue(product).addOnSuccessListener {
            message = "Adding successful"
        }.addOnFailureListener {
            message = "Add failed"
        }
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

    suspend fun addProduct(product: Product): String {
        var savedProduct = product
        var imageUrl: Uri? = null
        var message:String ="Adding successful"
        val name = System.currentTimeMillis().toString() + "uploadedImage"
        val storageReference: StorageReference = FirebaseStorage.getInstance().reference
        val image = storageReference.child("pictures/$name")
        image.putFile(product.imageUri.toUri()).addOnSuccessListener {
            image.downloadUrl.addOnSuccessListener {
                savedProduct.imageUri = it.toString()
                message = saveProduct(savedProduct)
                Log.v("firebase",imageUrl.toString())
                Log.v("firebase",message)
            }
        }
        return message
    }

}

