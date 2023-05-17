package com.midterm.libgmobile

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.midterm.libgmobile.model.UserModel
import de.hdodenhof.circleimageview.CircleImageView


/**
 * A simple [Fragment] subclass.
 * Use the [AccountFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AccountFragment : Fragment(R.layout.fragment_account) {
    private var user = UserModel()

    @Suppress("DEPRECATION")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // get data from bundle
        if (arguments != null) {
            user = arguments?.getSerializable("user") as UserModel
        }
        val name : TextView = view.findViewById(R.id.tvNameUser)
        val image : CircleImageView = view.findViewById(R.id.civAvatar)
        Glide.with(this).load(user.avatar).into(image)
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
        val btnHistory: CardView = view?.findViewById(R.id.btnHistory)!!
        btnHistory.setOnClickListener {
            val intent = Intent(activity, HistoryActivity::class.java)
            intent.putExtra("user", user)
            startActivity(intent)
        }
    }
}