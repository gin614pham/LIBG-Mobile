package com.midterm.libgmobile.model

import android.app.Activity
import android.widget.Toast
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.IgnoreExtraProperties
import java.io.Serializable

@IgnoreExtraProperties
class CallCardModel(
    var id: String,
    var id_user: String,
    var name_user: String,
    var id_book: String,
    var name_book: String,
    var status: String,
    var date: String,
    var due_date: String,
): Serializable {

    @Transient
    private var database: DatabaseReference? = null

    constructor(): this("", "", "", "", "", "", "", "")

    @Suppress("DEPRECATION")
    fun pushCallCard(activity: Activity) {
        database = FirebaseDatabase.getInstance().reference.child("call_cards")
        this.id = database!!.push().key.toString()
        database!!.child(this.id).setValue(this).addOnCompleteListener() {
            Toast.makeText(
                activity,
                "Tạo phiếu mượn thành công!",
                Toast.LENGTH_SHORT
            ).show()
            activity.onBackPressed()
        }.addOnFailureListener(){
            Toast.makeText(
                activity,
                "Lỗi",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    fun changeStatus(activity: Activity, status: String) {
        database = FirebaseDatabase.getInstance().reference.child("call_cards")
        database!!.child(this.id).child("status").setValue(status).addOnCompleteListener() {
            Toast.makeText(
                activity,
                "Cập nhật thành công!",
                Toast.LENGTH_SHORT
            ).show()
        }
        this.status = status
    }


}