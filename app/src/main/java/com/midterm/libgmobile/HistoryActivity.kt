package com.midterm.libgmobile

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.midterm.libgmobile.adapter.RvCallCard
import com.midterm.libgmobile.adapter.RvCallCardIF
import com.midterm.libgmobile.databinding.ActivityHistoryBinding
import com.midterm.libgmobile.databinding.LayoutUserInfoBinding
import com.midterm.libgmobile.model.UserModel
import com.midterm.libgmobile.service.CallCardService

class HistoryActivity : AppCompatActivity() {
    private val callCardService: CallCardService = CallCardService()
    private lateinit var recyclerView: RecyclerView
    private lateinit var rvCallCardAdapter: RvCallCard
    private lateinit var user: UserModel

    @Suppress("DEPRECATION")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history)
        // get user from intent
        user = intent.getSerializableExtra("user") as UserModel
        recyclerView = findViewById(R.id.recyclerviewHistory)

        findViewById<Button>(R.id.btnBackHistory).setOnClickListener {
            finish()
        }
        setData(user.id.toString())
    }

    private fun setData(id: String){
        callCardService.getCallCardByID(id) {list->
            rvCallCardAdapter = RvCallCard(list, object : RvCallCardIF {
                override fun onClick(position: Int) {
                    val intent = Intent(this@HistoryActivity, DetailHistoryActivity::class.java)
                    intent.putExtra("callCard", list[position])
                    startActivity(intent)
                }

            })
            recyclerView.adapter = rvCallCardAdapter
            recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        }
    }

}