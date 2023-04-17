package com.midterm.libgmobile

import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.midterm.libgmobile.databinding.ActivityLoginBinding
import com.midterm.libgmobile.model.UserModel

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        // check box hidden password
        binding.cbHiddenPassword.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                binding.etPasswordLogin.transformationMethod = HideReturnsTransformationMethod.getInstance()
            } else {
                binding.etPasswordLogin.transformationMethod = PasswordTransformationMethod.getInstance()
            }
        }

        // button login
        binding.button.setOnClickListener {
//            val email = binding.etEmailLogin.text.toString()
//            val password = binding.etPasswordLogin.text.toString()
//            if (email.isNotEmpty() && password.isNotEmpty()) {
//
//            }
            //get reference to database
            val rfDB: DatabaseReference = Firebase.database.reference
            // create user object
            val user = UserModel(
                id = "Test0",
                name = "Test",
                email = "Test",
                password = "Test",
                phone = "Test",
                gender = "Test",
                avatar = "Test",
                isLogin = true
            )
            // add user to database
            rfDB.child("users").child(user.id).setValue(user).addOnCompleteListener {
                if (it.isSuccessful) {
                    // notify user
                    Toast.makeText(this, getString(R.string.dang_nhap_thanh_cong), Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, getString(R.string.dang_nhap_that_bai), Toast.LENGTH_SHORT).show()
                }
            }
        }

    }
}