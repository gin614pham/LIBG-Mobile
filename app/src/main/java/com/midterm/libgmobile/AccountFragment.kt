package com.midterm.libgmobile

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.TextView
import androidx.cardview.widget.CardView
import com.midterm.libgmobile.model.UserModel


/**
 * A simple [Fragment] subclass.
 * Use the [AccountFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AccountFragment : Fragment(R.layout.fragment_account) {
    private var user = UserModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // get data from bundle
        if (arguments != null) {
            user = arguments?.getSerializable("user") as UserModel
        }
        val name : TextView = view.findViewById(R.id.tvNameUser)
        name.text = user.name
        setOnClickItem()
    }

    private fun setOnClickItem(){
        val btnInfoAccount: CardView = view?.findViewById(R.id.btnInfoUser)!!
        btnInfoAccount.setOnClickListener {
            val intent = Intent(activity, AccountActivity::class.java)
            intent.putExtra("user", user)
            startActivity(intent)
        }
    }
}