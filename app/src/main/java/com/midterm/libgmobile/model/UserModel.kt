package com.midterm.libgmobile.model

import java.io.Serializable

class UserModel(
    var id: String ?= "",
    var name: String ?= "",
    var email: String ?= "",
    var password: String ?= "",
    var phone: String ?= "",
    var gender: String ?= "",
    var avatar: Int ?= 0,
    var isLogin: Boolean ?= false
): Serializable {
}