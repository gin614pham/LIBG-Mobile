package com.midterm.libgmobile.model

import java.io.Serializable

class CommentModel(
    var id: String,
    var id_book: String,
    var id_user: String,
    var comment: String,
    var name: String,
    var avatar: Int
): Serializable {
}