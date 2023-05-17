package com.midterm.libgmobile.model

import android.app.Activity
import android.net.Uri
import android.widget.Toast
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.IgnoreExtraProperties
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.ktx.storageMetadata
import java.io.Serializable
import java.util.concurrent.SubmissionPublisher

@IgnoreExtraProperties
class BookModel(
    var id: String,
    var name: String,
    var author: String,
    var description: String,
    var image: String,
    var publisher: String,
    var release: String,
) : Serializable {
    @Transient
    private var database: DatabaseReference? = null
    @Transient
    private var storage : FirebaseStorage? = null

    constructor(): this("", "", "", "", "", "", "")

    fun pushBook(activity: Activity) {
        database = FirebaseDatabase.getInstance().reference.child("books")
        if (this.id.isEmpty()) {
            this.id = database!!.push().key.toString()
        }
        database!!.child(this.id).setValue(this).addOnCompleteListener() {
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
        val storageRef = storage!!.reference
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
        database!!.child("image").setValue(toString)
        this.image = toString
    }

    fun deleteBook(activity: Activity) {
        database = FirebaseDatabase.getInstance().reference.child("books").child(id)
        database!!.removeValue().addOnSuccessListener {
            Toast.makeText(
                activity,
                "Xoá sách thành công",
                Toast.LENGTH_SHORT
            ).show()
        }

    }
}