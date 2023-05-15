package com.midterm.libgmobile.model

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.util.Log
import android.widget.Toast
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.IgnoreExtraProperties
import com.google.firebase.database.ValueEventListener
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.ktx.storageMetadata
import com.midterm.libgmobile.LoginActivity
import com.midterm.libgmobile.MainActivity
import com.midterm.libgmobile.R
import java.io.Serializable

@IgnoreExtraProperties
data class UserModel(
    var id: String ?= "",
    var name: String ?= "",
    var email: String ?= "",
    var password: String ?= "",
    var phone: String ?= "",
    var gender: String ?= "",
    var avatar: String ?= "",
    var isLogin: Boolean ?= false
): Serializable {
    private lateinit var database : DatabaseReference
    constructor(): this("", "", "", "", "", "", "", false)

    fun login(activity: Activity, email: String, password: String) {
        database = FirebaseDatabase.getInstance().reference.child("users")
        database.orderByChild("email").equalTo(email).addListenerForSingleValueEvent(
            object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    if (snapshot.exists()) {
                        val user = snapshot.children.first().getValue(UserModel::class.java)
                        if (user != null) {
                            if (user.password == password) {
                                this@UserModel.isLogin = true
                                this@UserModel.id = user.id
                                this@UserModel.name = user.name
                                this@UserModel.email = user.email
                                this@UserModel.password = user.password
                                this@UserModel.phone = user.phone
                                this@UserModel.gender = user.gender
                                this@UserModel.avatar = user.avatar
                            } else {
                                Toast.makeText(
                                    activity,
                                    R.string.email_or_password_is_wrong,
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        } else {
                            Toast.makeText(
                                activity,
                                R.string.dang_nhap_that_bai,
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    } else {
                        Toast.makeText(
                            activity,
                            R.string.dang_nhap_that_bai,
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    Toast.makeText(
                        activity,
                        R.string.dang_nhap_that_bai,
                        Toast.LENGTH_SHORT
                    ).show()
                }

            }
        )
    }

    fun editAvatar(uri: Uri) {
        val metadata = storageMetadata {
            contentType = "image/jpeg|image/png|image/jpg"
        }
        val storage = FirebaseStorage.getInstance().reference.child("images/avatars/${name}.jpg")
        storage.putFile(uri, metadata).addOnSuccessListener {
            storage.downloadUrl.addOnSuccessListener {
                updateAvatar(it.toString())
            }
        }.addOnFailureListener {
            Log.d("uri", it.message.toString())
        }.addOnProgressListener {
            Log.d("uri", it.bytesTransferred.toString())
        }.addOnCanceledListener {
            Log.d("uri", "canceled")
        }
    }

    private fun updateAvatar(url : String){
        database = FirebaseDatabase.getInstance().reference.child("users").child(id!!)
        database.child("avatar").setValue(url)
        this.avatar = url
    }

    fun editPhoneNumber(phone: String) {
        database = FirebaseDatabase.getInstance().reference.child("users").child(id!!)
        database.child("phone").setValue(phone)
        this.phone = phone
    }

    fun changePassword(newPassword: String) {
        database = FirebaseDatabase.getInstance().reference.child("users").child(id!!)
        database.child("password").setValue(newPassword)
        this.password = newPassword
    }

    fun logout(activity: Activity) {
        this.isLogin = false
        this.id = ""
        this.name = ""
        this.email = ""
        this.password = ""
        this.phone = ""
        this.gender = ""
        this.avatar = ""
        activity.startActivity(Intent(activity, MainActivity::class.java))
    }

    fun checkLogin(activity: Activity){
        if (this@UserModel.isLogin != true) {
            activity.startActivity(Intent(activity, LoginActivity::class.java))
        }
    }


}