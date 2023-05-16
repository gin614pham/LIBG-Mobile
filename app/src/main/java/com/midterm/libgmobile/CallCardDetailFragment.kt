package com.midterm.libgmobile

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.midterm.libgmobile.model.CallCardModel


class CallCardDetailFragment : Fragment(R.layout.fragment_call_card_detail_pending) {
    private var callCardModel: CallCardModel = CallCardModel()


    @Suppress("DEPRECATION")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (arguments != null) {
            callCardModel = arguments?.getSerializable("callCard") as CallCardModel
        }
        view.findViewById<TextView>(R.id.tvIdCallCard).text = callCardModel.id
        view.findViewById<TextView>(R.id.tvIdUser).text = callCardModel.id_user
        view.findViewById<TextView>(R.id.tvNameUserCallCardRequest).text = callCardModel.name_user
        view.findViewById<TextView>(R.id.tvIdBook).text = callCardModel.id_book
        view.findViewById<TextView>(R.id.tvNameBookCallCardRequest).text = callCardModel.name_book
        view.findViewById<TextView>(R.id.tvDayCallCardRequest).text = callCardModel.date
        view.findViewById<TextView>(R.id.tvDueDayCallCardRequest).text = callCardModel.due_date

        view.findViewById<Button>(R.id.btnBackCallCardetail).setOnClickListener() {
            requireActivity().onBackPressed()
        }
        view.findViewById<Button>(R.id.btnConfirm).setOnClickListener() {
            callCardModel.changeStatus(requireActivity(), "Unpaid")
            requireActivity().onBackPressed()
        }
        view.findViewById<Button>(R.id.btnCancel).setOnClickListener() {
            callCardModel.changeStatus(requireActivity(), "Refuse")
            requireActivity().onBackPressed()
        }
        view.findViewById<Button>(R.id.btnSuccess).setOnClickListener() {
            callCardModel.changeStatus(requireActivity(), "Done")
            requireActivity().onBackPressed()
        }
        checkStatus(callCardModel.status, view)
    }

    @SuppressLint("CutPasteId")
    fun checkStatus(status: String, view: View) {
        when (status) {
            "Refuse" -> {
                view.findViewById<Button>(R.id.btnConfirm).visibility = View.GONE
                view.findViewById<Button>(R.id.btnCancel).visibility = View.GONE
                view.findViewById<Button>(R.id.btnSuccess).visibility = View.GONE
            }
            "Unpaid" -> {
                view.findViewById<Button>(R.id.btnConfirm).visibility = View.GONE
                view.findViewById<Button>(R.id.btnCancel).visibility = View.GONE
                view.findViewById<Button>(R.id.btnSuccess).visibility = View.VISIBLE
            }
            "Done" -> {
                view.findViewById<Button>(R.id.btnConfirm).visibility = View.GONE
                view.findViewById<Button>(R.id.btnCancel).visibility = View.GONE
                view.findViewById<Button>(R.id.btnSuccess).visibility = View.GONE
            }
            "Pending" -> {
                view.findViewById<Button>(R.id.btnConfirm).visibility = View.VISIBLE
                view.findViewById<Button>(R.id.btnCancel).visibility = View.VISIBLE
                view.findViewById<Button>(R.id.btnSuccess).visibility = View.GONE
            }
        }
    }

}