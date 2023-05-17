package com.midterm.libgmobile

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.midterm.libgmobile.model.BookModel
import com.midterm.libgmobile.model.CallCardModel
import com.midterm.libgmobile.model.UserModel
import java.text.SimpleDateFormat


class CallCardRequestFragment : Fragment(R.layout.fragment_call_card_request) {
    private var userModel: UserModel = UserModel()
    private var bookModel: BookModel = BookModel()
    private var callCardModel: CallCardModel = CallCardModel()

    @Suppress("DEPRECATION")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (arguments != null) {
            userModel = arguments?.getSerializable("user") as UserModel
            bookModel = arguments?.getSerializable("book") as BookModel
        }
        createCallCard(userModel, bookModel)
        setData(callCardModel, view)
        view.findViewById<Button>(R.id.btnCancel).setOnClickListener() {
            activity?.onBackPressed()
        }
        view.findViewById<Button>(R.id.btnConfirm).setOnClickListener() {
            callCardModel.pushCallCard(requireActivity())
        }
        view.findViewById<Button>(R.id.btnSuccess).visibility = View.GONE
    }

    private fun createCallCard(user: UserModel, book: BookModel){
        callCardModel.id_user = user.id.toString()
        callCardModel.name_user = user.name.toString()
        callCardModel.id_book = book.id
        callCardModel.name_book = book.name
        callCardModel.status = "Pending"
        callCardModel.return_date = ""
        val dayFormat = SimpleDateFormat("dd/MM/yyyy", java.util.Locale.getDefault())
        callCardModel.date = dayFormat.format(System.currentTimeMillis())
        // set due date call card request to next 7 days
        callCardModel.due_date = dayFormat.format(System.currentTimeMillis() + 7 * 24 * 60 * 60 * 1000)
        // formatted date day, month, year, hour, minute, second
        val dayFormat2 = SimpleDateFormat("dd/MM/yyyy HH:mm:ss", java.util.Locale.getDefault())
        callCardModel.create_date = dayFormat2.format(System.currentTimeMillis())
    }

    private fun setData(callCardModel: CallCardModel, view: View) {
        view.findViewById<TextView>(R.id.tvIdUser).text = callCardModel.id_user
        view.findViewById<TextView>(R.id.tvIdBook).text = callCardModel.id_book
        view.findViewById<TextView>(R.id.tvNameUserCallCardRequest).text = userModel.name
        view.findViewById<TextView>(R.id.tvNameBookCallCardRequest).text = bookModel.name
        view.findViewById<TextView>(R.id.tvDayCallCardRequest).text = callCardModel.date
        view.findViewById<TextView>(R.id.tvDueDayCallCardRequest).text = callCardModel.due_date
        view.findViewById<TextView>(R.id.tvReturnDayCallCardRequest).text = callCardModel.return_date

    }

}