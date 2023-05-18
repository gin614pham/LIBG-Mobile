package com.midterm.libgmobile.service

import android.util.Log
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.midterm.libgmobile.model.BookModel
import java.util.Locale

class BookService {
    private lateinit var database : DatabaseReference

    fun getAllBook(callback: (List<BookModel>) -> Unit) {

        database = FirebaseDatabase.getInstance().getReference("books")
        database.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val list = ArrayList<BookModel>()
                // Get Post object and use the values to update the UI
                for (postSnapshot in dataSnapshot.children) {
                    val book = postSnapshot.getValue(BookModel::class.java)
                    list.add(book!!)
                }
                callback(list)
            }

            override fun onCancelled(error: DatabaseError) {
                Log.d("TAG", "loadPost:onCancelled")
            }
        })
    }

    fun filterBook(text: String, callback: (List<BookModel>) -> Unit) {
        database = FirebaseDatabase.getInstance().getReference("books")
        database.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val list = ArrayList<BookModel>()
                // Get Post object and use the values to update the UI
                for (postSnapshot in dataSnapshot.children) {
                    val book = postSnapshot.getValue(BookModel::class.java)
                    if (
                        book!!.name.lowercase(Locale.getDefault()).contains(text.lowercase(Locale.getDefault()))
                        ||
                        book.author.lowercase(Locale.getDefault()).contains(text.lowercase(Locale.getDefault()))
                        ||
                        book.publisher.lowercase(Locale.getDefault()).contains(text.lowercase(Locale.getDefault()))
                        ||
                        book.release.lowercase(Locale.getDefault()).contains(text.lowercase(Locale.getDefault()))
                    ) {
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
}