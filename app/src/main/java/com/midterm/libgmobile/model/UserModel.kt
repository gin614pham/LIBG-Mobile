package com.midterm.libgmobile.model

class UserModel(
    var id: String ?= "",
    var name: String ?= "",
    var email: String ?= "",
    var password: String ?= "",
    var phone: String ?= "",
    var gender: String ?= "",
    var avatar: String ?= "",
    var isLogin: Boolean ?= false
) {
}