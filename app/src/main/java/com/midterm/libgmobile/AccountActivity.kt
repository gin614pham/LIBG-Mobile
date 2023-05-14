package com.midterm.libgmobile

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.SpannableString
import android.text.style.UnderlineSpan
import android.util.Log
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import com.midterm.libgmobile.databinding.ActivityAccountBinding
import com.midterm.libgmobile.databinding.LayoutUserInfoBinding
import com.midterm.libgmobile.model.UserModel
import de.hdodenhof.circleimageview.CircleImageView
import kotlin.math.log

class AccountActivity : AppCompatActivity() {
    private var user = UserModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_account)
        getUser()
        checkLogin()
        setData()
        val text = getString(R.string.doi_mat_khau)
        val content = SpannableString(text)
        content.setSpan(
            UnderlineSpan(),0,text.length,0
        )
        val pickerMedia = registerForActivityResult(
            ActivityResultContracts.PickVisualMedia()
        ) { uri ->
            if (uri != null) {
                Log.d("uri", uri.toString())
            } else {
                Log.d("uri", "null")
            }

        }
        val changeAvatar = findViewById<CircleImageView>(R.id.imageView_avatar_user)
        changeAvatar.setOnClickListener() {
            // register a photo picker in single mode
            pickerMedia.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
        }

//        binding.tvChangePassword.text = content
//        // set OnClickListener for TextView tvChangePassword
//        binding.tvChangePassword.setOnClickListener {
//            println("change password")
//        }

    }



    // get data from bundle
    private fun getUser(){
        val intent = intent
        user = intent.getSerializableExtra("user") as UserModel
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
//        binding.tvId.text = getString(R.string.ma_sv) +": "+ user.id
//        binding.tvEmail.text = getString(R.string.email) +": "+ user.email
//        binding.tvName.text = getString(R.string.ten) +": "+ user.name
//        binding.tvPhone.text = getString(R.string.sdt) +": "+ user.phone
//        binding.tvGender.text = getString(R.string.gioi_tinh) +": "+ user.gender
    }
}