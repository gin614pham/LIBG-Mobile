package com.midterm.libgmobile.service

import android.content.Context
import android.view.View
import androidx.appcompat.app.AlertDialog
import com.midterm.libgmobile.R

class DialogMenuBook {
    private lateinit var dialog: AlertDialog
    fun show(context: Context, editListener: View.OnClickListener, deleteListener: View.OnClickListener) {
        val builder = AlertDialog.Builder(context)
        val view = View.inflate(context, R.layout.layout_dialog_menu_book, null)
        val btnEdit = view.findViewById<View>(R.id.btnEditBookDetail)
        val btnDelete = view.findViewById<View>(R.id.btnDeleteBookDetail)
        btnEdit.setOnClickListener(editListener)
        btnDelete.setOnClickListener(deleteListener)
        builder.setView(view)
        builder.setCancelable(true)
        dialog = builder.create()
        dialog.show()
    }

    fun dismiss() {
        dialog.dismiss()
    }
}