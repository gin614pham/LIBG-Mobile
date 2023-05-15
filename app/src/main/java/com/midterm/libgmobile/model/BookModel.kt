package com.midterm.libgmobile.model

import android.app.Activity
import android.net.Uri
import android.widget.Toast
import androidx.databinding.adapters.NumberPickerBindingAdapter.setValue
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.ktx.storageMetadata
import com.midterm.libgmobile.MainActivity
import java.io.Serializable

class BookModel(
    var id: String,
    var name: String,
    var author: String,
    var description: String,
    var image: String,
    var price: String,
    var rating: String,
) : Serializable {
    private lateinit var database : DatabaseReference
    private lateinit var storage : FirebaseStorage

    constructor(): this("", "", "", "", "", "", "")

    fun pushBook(activity: Activity) {
        database = FirebaseDatabase.getInstance().reference.child("books")
        this.id = database.push().key.toString()
        this.rating = "0"
        database.child(this.id).setValue(this).addOnCompleteListener() {
            Toast.makeText(
                activity,
                "Thêm sách thành công",
                Toast.LENGTH_SHORT
            ).show()
        }.addOnFailureListener() {
            Toast.makeText(
                activity,
                "Thêm sách thất bại",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    fun  pushImage(uri: Uri) {
        storage = FirebaseStorage.getInstance()
        val storageRef = storage.reference
        val metadata = storageMetadata {
            contentType = "image/jpeg|image/png|image/jpg"
        }
        val uploadTask = storageRef.child("images/$name.jpg")
        uploadTask.putFile(uri, metadata).addOnSuccessListener {
            uploadTask.downloadUrl.addOnSuccessListener {
                updateImage(it.toString())
            }
        }
    }

    private fun updateImage(toString: String) {
        database = FirebaseDatabase.getInstance().reference.child("books").child(id)
        database.child("image").setValue(toString)
        this.image = toString
    }
}