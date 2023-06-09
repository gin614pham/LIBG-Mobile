package com.midterm.libgmobile

import android.os.Bundle
import android.transition.TransitionInflater
import android.view.View
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.midterm.libgmobile.adapter.RvBook
import com.midterm.libgmobile.adapter.RvBookIF
import com.midterm.libgmobile.model.UserModel
import com.midterm.libgmobile.service.BookService

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : Fragment(R.layout.fragment_home) {
    // TODO: Rename and change types of parameters
    private lateinit var recyclerView: RecyclerView
    private lateinit var bookAdapter: RvBook
    private val bookService = BookService()
    private var user: UserModel = UserModel()

    @Suppress("DEPRECATION")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (arguments != null) {
            user = arguments?.getSerializable("user") as UserModel
        }
        setData(view)

    }

    override fun onResume() {
        super.onResume()
        view?.let { setData(it) }
    }

    private fun setData(view: View){
        recyclerView = view.findViewById(R.id.recyclerview_list_book)
        bookService.getAllBook{list ->
            bookAdapter = RvBook(list, object : RvBookIF {
                override fun onClick(position: Int) {
                    val bundle = Bundle()
                    bundle.putSerializable("book", list[position])
                    bundle.putSerializable("user", user)
                    val fragment = DetailBookFragment()
                    fragment.arguments = bundle
                    requireActivity().supportFragmentManager.beginTransaction()
                        .setCustomAnimations(
                            R.anim.slide_in_1,
                            R.anim.slide_out_1,
                            R.anim.slide_in_2,
                            R.anim.slide_out_2
                        )
                        .replace(R.id.frame_layout, fragment)
                        .addToBackStack(HomeFragment::class.java.name)
                        .commit()
                }
            })
            recyclerView.adapter = bookAdapter
            recyclerView.layoutManager =
                LinearLayoutManager(view.context, LinearLayoutManager.VERTICAL, false)
        }
    }

}