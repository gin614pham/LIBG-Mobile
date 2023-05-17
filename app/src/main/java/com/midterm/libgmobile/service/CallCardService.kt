package com.midterm.libgmobile.service

import android.util.Log
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.midterm.libgmobile.model.CallCardModel

class CallCardService {
    private lateinit var database : DatabaseReference

    fun getAllCallCard(status: String, callback: (List<CallCardModel>) -> Unit) {

        database = FirebaseDatabase.getInstance().getReference("call_cards")
        database.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val list = ArrayList<CallCardModel>()
                // Get Post object and use the values to update the UI
                for (postSnapshot in dataSnapshot.children) {
                    val book = postSnapshot.getValue(CallCardModel::class.java)
                    if (book != null) {
                        if (book.status.contains(status))
                            list.add(book)
                    }
                }
                callback(list)
            }

            override fun onCancelled(error: DatabaseError) {
                Log.d("TAG", "loadPost:onCancelled")
            }
        })
    }

    fun getCallCardByID(id: String, callback: (List<CallCardModel>) -> Unit) {
        database = FirebaseDatabase.getInstance().getReference("call_cards")
        database.orderByChild("create_date").addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val list = ArrayList<CallCardModel>()
                // Get Post object and use the values to update the UI
                for (postSnapshot in dataSnapshot.children) {
                    val book = postSnapshot.getValue(CallCardModel::class.java)
                    if (book != null) {
                        if (book.id_user.contains(id))
                            list.add(book)
                    }
                }
                callback(list)
            }

            override fun onCancelled(error: DatabaseError) {
                Log.d("TAG", "loadPost:onCancelled")
            }
        })
    }

    fun getById(id: String, callback: (CallCardModel) -> Unit){
        database = FirebaseDatabase.getInstance().getReference("call_cards")
        database.child(id).get().addOnSuccessListener {
            val book = it.getValue(CallCardModel::class.java)
            callback(book!!)
        }
    }
}