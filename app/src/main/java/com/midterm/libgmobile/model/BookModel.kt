package com.midterm.libgmobile.model

import java.io.Serializable

class BookModel(
    var id: String,
    var name: String,
    var author: String,
    var description: String,
    var image: Int,
    var price: String,
    var rating: String,
) : Serializable {
}