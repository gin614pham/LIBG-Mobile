package com.midterm.libgmobile

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.databinding.DataBindingUtil.*
import com.bumptech.glide.Glide
import com.midterm.libgmobile.model.UserModel
import de.hdodenhof.circleimageview.CircleImageView

class AccountActivity : AppCompatActivity() {
    private var user = UserModel()

    @SuppressLint("CutPasteId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_account)
        getUser()
        user.checkLogin(this)
        setData()
        val pickerMedia = registerForActivityResult(
            ActivityResultContracts.PickVisualMedia()
        ) { uri ->
            if (uri != null) {
                Log.d("uri", uri.toString())
                user.editAvatar(uri)
                Glide.with(this).load(uri).into(findViewById<CircleImageView>(R.id.imageView_avatar_user))
                onResume()
            } else {
                Log.d("uri", "null")
            }

        }
        val changeAvatar = findViewById<CircleImageView>(R.id.imageView_avatar_user)
        changeAvatar.setOnClickListener {
            // register a photo picker in single mode
            pickerMedia.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
        }
        val btnBack = findViewById<Button>(R.id.btnBackInfo)
        btnBack.setOnClickListener {
            finish()
        }
        val btnChagePassword = findViewById<CardView>(R.id.cdChagePassword)
        btnChagePassword.setOnClickListener {
            showChangePasswordDialog()
        }
        val btnChangePhone = findViewById<CardView>(R.id.cdChangePhone)
        btnChangePhone.setOnClickListener {
            showChangePhoneDialog()
        }
        val btnLogout = findViewById<Button>(R.id.btnLogout)
        btnLogout.setOnClickListener {
            user.logout(this)
        }
    }

    override fun onResume() {
        super.onResume()
        user.checkLogin(this)
        setData()
        Log.d("Resume", "onResume")
    }

    @SuppressLint("InflateParams")
    private fun showChangePasswordDialog() {
        val builder = AlertDialog.Builder(this)
        val view = layoutInflater.inflate(R.layout.layout_dialog_change_password, null)
        builder.setTitle("Change Password")
        builder.setView(view)
        builder.setPositiveButton("OK") { dialog, which ->
            val oldPassword = view.findViewById<EditText>(R.id.etOldPassword).text.toString()
            val newPassword = view.findViewById<EditText>(R.id.etNewPassword).text.toString()
            val confirmPassword = view.findViewById<EditText>(R.id.etConfirmPassword).text.toString()
            if (oldPassword == user.password) {
                if (newPassword == confirmPassword) {
                    user.changePassword(newPassword)
                    onResume()
                    Toast.makeText(this, getString(R.string.password_changed), Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, getString(R.string.password_not_match), Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, getString(R.string.old_password_not_match), Toast.LENGTH_SHORT).show()
            }
        }
        builder.setNegativeButton("Cancel") { dialog, which ->
            dialog.dismiss()
        }
        builder.show()
    }

    private fun showChangePhoneDialog() {
        val builder = AlertDialog.Builder(this)
        val view = EditText(this).apply {
            hint = getString(R.string.sdt)
            inputType = android.text.InputType.TYPE_CLASS_PHONE
        }
        builder.setTitle("Change Phone")
        builder.setView(view)
        builder.setPositiveButton("OK") { dialog, which ->
            view.text.toString().let {
                if (it.isNotEmpty() && it.length == 10) {
                    user.editPhoneNumber(it)
                    onResume()
                    Toast.makeText(this, getString(R.string.phone_changed), Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, getString(R.string.phone_not_valible), Toast.LENGTH_SHORT).show()
                }

            }
        }
        builder.setNegativeButton("Cancel") { dialog, which ->
            dialog.dismiss()
        }
        builder.show()
    }


    // get data from bundle
    @Suppress("DEPRECATION")
    private fun getUser(){
        val intent = intent
        user = intent.getSerializableExtra("user") as UserModel
    }

    // fun set data to layout
    @SuppressLint("SetTextI18n")
    private fun setData(){
        findViewById<TextView>(R.id.tvNameUserInfo).text = user.name
        findViewById<TextView>(R.id.tvEmailUserInfo).text = user.email
        findViewById<TextView>(R.id.tvPhoneUserInfo).text = user.phone
        findViewById<TextView>(R.id.tvIdUserInfo).text = user.id
        findViewById<TextView>(R.id.tvGenderUserInfo).text = user.gender
        val image = findViewById<CircleImageView>(R.id.imageView_avatar_user)
        Glide.with(this).load(user.avatar).into(image)
    }
}