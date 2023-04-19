package com.midterm.libgmobile

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.SpannableString
import android.text.style.UnderlineSpan
import com.midterm.libgmobile.databinding.ActivityAccountBinding
import com.midterm.libgmobile.model.UserModel

class AccountActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAccountBinding
    private var user = UserModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_account)
        binding = ActivityAccountBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getUserFromIntent()
        checkLogin()
        setData()
        val text = getString(R.string.doi_mat_khau)
        val content = SpannableString(text)
        content.setSpan(
            UnderlineSpan(),0,text.length,0
        )
        binding.tvChangePassword.text = content
        // set OnClickListener for TextView tvChangePassword
        binding.tvChangePassword.setOnClickListener {
            println("change password")
        }

    }

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

    // fun check login
    private fun checkLogin(){
        if (user.isLogin != true){
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }
    // fun set data to layout
    @SuppressLint("SetTextI18n")
    private fun setData(){
        binding.tvId.text = getString(R.string.ma_sv) +": "+ user.id
        binding.tvEmail.text = getString(R.string.email) +": "+ user.email
        binding.tvName.text = getString(R.string.ten) +": "+ user.name
        binding.tvPhone.text = getString(R.string.sdt) +": "+ user.phone
        binding.tvGender.text = getString(R.string.gioi_tinh) +": "+ user.gender
    }
}