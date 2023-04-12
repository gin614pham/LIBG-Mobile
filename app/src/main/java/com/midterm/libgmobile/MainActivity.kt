package com.midterm.libgmobile

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // create intent to go to login activity
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)

    }
}