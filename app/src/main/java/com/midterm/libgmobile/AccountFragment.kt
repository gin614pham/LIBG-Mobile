package com.midterm.libgmobile

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.viewbinding.ViewBindings
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
            val user = arguments?.getSerializable("user") as UserModel
        }
    }



}