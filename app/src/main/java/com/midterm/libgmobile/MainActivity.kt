package com.midterm.libgmobile

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.midterm.libgmobile.databinding.ActivityMainBinding
import com.midterm.libgmobile.model.UserModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var user = UserModel()
    private var homeFragment = HomeFragment()
    private var accountFragment = AccountFragment()
    private var searchFragment = SearchFragment()
    private var addBookFragment = AddBookFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getUserFromIntent()
        // check login
        user.checkLogin(this)
        checkRole()
        // set fragment home
        replaceFragment(homeFragment)

        // button navigation view
        binding.bottomNavigationView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.navigation_home -> {
                    // set fragment home
                    replaceFragment(homeFragment)
                }
                R.id.navigation_search -> {
                    replaceFragment(searchFragment)
                }
                R.id.navigation_account -> {
                    // put user to fragment account
                    // set fragment account
                    putUserToAccountFragment(accountFragment, user)
                    replaceFragment(accountFragment)

                }
            }
            true
        }
    }

    override fun onResume() {
        super.onResume()
        user.checkLogin(this)
        checkRole()
    }

    // fun get user from intent
    private fun getUserFromIntent() {
        val intent = intent
        val id = intent.getStringExtra("id")
        val email = intent.getStringExtra("email")
        val password = intent.getStringExtra("password")
        val name = intent.getStringExtra("name")
        val phone = intent.getStringExtra("phone")
        val gender = intent.getStringExtra("gender")
        val avatar = intent.getStringExtra("avatar")
        val role = intent.getStringExtra("role")
        val isLogin = intent.getBooleanExtra("isLogin", false)
        user = UserModel(id, name, email, password, phone, gender, avatar, role, isLogin)
    }

    // fun put user to fragment account
    private fun putUserToAccountFragment(fragment: Fragment, user: UserModel) {
        val bundle = Bundle()
        bundle.putSerializable("user", user)
        fragment.arguments = bundle
    }
    // fun replace fragment
    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.addToBackStack(homeFragment.tag)
        fragmentTransaction.replace(R.id.frame_layout, fragment)
        fragmentTransaction.commit()
    }

    private fun checkRole() {
        if (user.role == "Admin") {
            binding.fabAdminAddBook.visibility = View.VISIBLE
            binding.fabAdminAddBook.setOnClickListener {
                replaceFragment(addBookFragment)
            }
        }

    }


}