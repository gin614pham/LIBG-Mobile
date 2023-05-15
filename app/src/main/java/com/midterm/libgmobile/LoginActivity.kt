package com.midterm.libgmobile

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.midterm.libgmobile.model.UserModel

class LoginActivity : AppCompatActivity() {
    private lateinit var user : UserModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        user = UserModel()
        // set OnClick btn login
        findViewById<Button>(R.id.btnLogin).setOnClickListener{
            val email = findViewById<EditText>(R.id.etEmailLogin).text.toString()
            val password = findViewById<EditText>(R.id.etPasswordLogin).text.toString()
            if (checkEmailAndPassword(email, password)) {
                user.login(this, email, password)
                if (user.isLogin == true) {
                    login(user)
                    clearPassword()
                }
            }
        }

    }

    // fun check email and password is not empty
    private fun checkEmailAndPassword(email:String, password:String): Boolean {
        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(
                this,
                getString(R.string.email_hoac_mat_khau_khong_duoc_de_trong),
                Toast.LENGTH_SHORT
            ).show()
            return false
        }
        return true
    }
    // fun login
    private fun login(user: UserModel){
        val intent = Intent(this, MainActivity::class.java).apply {
            putExtra("id", user.id)
            putExtra("email", user.email)
            putExtra("password", user.password)
            putExtra("name", user.name)
            putExtra("phone", user.phone)
            putExtra("gender", user.gender)
            putExtra("avatar", user.avatar)
            putExtra("role", user.role)
            putExtra("isLogin", user.isLogin)
        }
        startActivity(intent)
    }
    // fun clear password
    private fun clearPassword(){
        val editText = findViewById<EditText>(R.id.etPasswordLogin)
        editText.setText("")
    }




}