package com.midterm.libgmobile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.PasswordTransformationMethod
import com.midterm.libgmobile.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //set onChangeListener for checkbox
        binding.cBHiddenPassword.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                // show password
                binding.eTPasswordLogin.transformationMethod = PasswordTransformationMethod.getInstance()
            } else {
                // hide password
                binding.eTPasswordLogin.transformationMethod = null
            }
        }

        // set onclick listener
        binding.btnLogin.setOnClickListener {
            // get text from edittext
            val username = binding.eTEmailLogin.text.toString()
            val password = binding.eTPasswordLogin.text.toString()
        }


    }
}