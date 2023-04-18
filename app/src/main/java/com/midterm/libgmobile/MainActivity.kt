package com.midterm.libgmobile

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.midterm.libgmobile.databinding.ActivityMainBinding
import com.midterm.libgmobile.model.UserModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var user = UserModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getUserFromIntent()
        // check login
        if(user.isLogin == true){
            putUserToIntent(AccountActivity::class.java)
        } else {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }

    // fun get user from intent
    private fun getUserFromIntent() {
        val intent = intent
        val id = intent.getStringExtra("id")
        val email = intent.getStringExtra("email")
        val password = intent.getStringExtra("password")
        val name = intent.getStringExtra("name")
        val phone = intent.getStringExtra("phone")
        val gender = intent.getStringExtra("gender")
        val avatar = intent.getStringExtra("avatar")
        val isLogin = intent.getBooleanExtra("isLogin", false)
        user = UserModel(id, email, password, name, phone, gender, avatar, isLogin)
    }
    // fun put user to intent
    private fun putUserToIntent(c : Class<*>) {
        val intent = Intent(this, c).apply {
            putExtra("id", user.id)
            putExtra("email", user.email)
            putExtra("password", user.password)
            putExtra("name", user.name)
            putExtra("phone", user.phone)
            putExtra("gender", user.gender)
            putExtra("avatar", user.avatar)
            putExtra("isLogin", user.isLogin)
        }
        startActivity(intent)
    }


}