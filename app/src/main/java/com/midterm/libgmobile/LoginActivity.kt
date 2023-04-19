package com.midterm.libgmobile

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.midterm.libgmobile.model.UserModel

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        // set OnClick btn login
        findViewById<Button>(R.id.btnLogin).setOnClickListener{
            val email = findViewById<EditText>(R.id.etEmailLogin).text.toString()
            val password = findViewById<EditText>(R.id.etPasswordLogin).text.toString()
            if (checkEmailAndPassword(email, password)) {
                checkLogin(email, password)
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
    // fun check login
    private fun checkLogin(email:String, password:String) {
        val database = Firebase.database
        val myRef: DatabaseReference = database.getReference("users")
        myRef.orderByChild("email").equalTo(email).addListenerForSingleValueEvent(object :
            ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    val user = snapshot.children.first().getValue(UserModel::class.java)
                    if (user != null) {
                        if (user.password == password) {
                            login(user)
                        }
                        else {
                            Toast.makeText(
                                this@LoginActivity,
                                getString(R.string.email_or_password_is_wrong),
                                Toast.LENGTH_SHORT
                            ).show()
                            clearPassword()
                        }
                    }
                } else {
                    Toast.makeText(
                        this@LoginActivity,
                        getString(R.string.dang_nhap_that_bai),
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(
                    this@LoginActivity,
                    getString(R.string.dang_nhap_that_bai),
                    Toast.LENGTH_SHORT
                ).show()
            }
        })
    }
    // fun login
    fun login(user: UserModel){
        val intent = Intent(this, MainActivity::class.java).apply {
            putExtra("id", user.id)
            putExtra("email", user.email)
            putExtra("password", user.password)
            putExtra("name", user.name)
            putExtra("phone", user.phone)
            putExtra("gender", user.gender)
            putExtra("avatar", user.avatar)
            putExtra("isLogin", true)
        }
        startActivity(intent)
    }
    // fun clear password
    fun clearPassword(){
        val editText = findViewById<EditText>(R.id.etPasswordLogin)
        editText.setText("")
    }




}