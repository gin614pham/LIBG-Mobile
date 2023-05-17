package com.midterm.libgmobile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.midterm.libgmobile.model.CallCardModel

class DetailHistoryActivity : AppCompatActivity() {
    private var callCardModel: CallCardModel = CallCardModel()

    @Suppress("DEPRECATION")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_history)
        callCardModel = intent.getSerializableExtra("callCard") as CallCardModel

        findViewById<Button>(R.id.btnConfirm).visibility = View.GONE
        findViewById<Button>(R.id.btnCancel).visibility = View.GONE
        findViewById<Button>(R.id.btnSuccess).visibility = View.GONE

        findViewById<TextView>(R.id.tvIdCallCard).text = callCardModel.id
        findViewById<TextView>(R.id.tvIdUser).text = callCardModel.id_user
        findViewById<TextView>(R.id.tvNameUserCallCardRequest).text = callCardModel.name_user
        findViewById<TextView>(R.id.tvIdBook).text = callCardModel.id_book
        findViewById<TextView>(R.id.tvNameBookCallCardRequest).text = callCardModel.name_book
        findViewById<TextView>(R.id.tvDayCallCardRequest).text = callCardModel.date
        findViewById<TextView>(R.id.tvDueDayCallCardRequest).text = callCardModel.due_date
        findViewById<TextView>(R.id.tvReturnDayCallCardRequest).text = callCardModel.return_date

        findViewById<Button>(R.id.btnBackHistoryDetail).setOnClickListener() {
            finish()
        }
    }
}