package com.midterm.libgmobile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.midterm.libgmobile.adapter.RvBook
import com.midterm.libgmobile.adapter.RvBookIF
import com.midterm.libgmobile.model.BookModel

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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = view.findViewById(R.id.recyclerview_list_book)
        val list = listOf(
            BookModel(
                "1",
                "Book 1",
                "Author 1",
                "Description 1",
                R.drawable.user,
                "20",
                "5"
            ), BookModel(
                "2",
                "Book 2",
                "Author 2",
                "Description 2",
                R.drawable.toi_tu_hoc_top_sach_hay_cho_hoc_sinh_sinh_vien_69ca39f78c,
                "20",
                "5"
            )
        )
        bookAdapter = RvBook(list, object : RvBookIF {
            override fun onClick(position: Int) {
                val bundle = Bundle()
                bundle.putSerializable("book", list[position])
                val fragment = DetailBookFragment()
                fragment.arguments = bundle
                requireActivity().supportFragmentManager.beginTransaction()
                    .replace(R.id.frame_layout, fragment)
                    .addToBackStack(null)
                    .commit()
            }
        })
        recyclerView.adapter = bookAdapter
        recyclerView.layoutManager =
            LinearLayoutManager(view.context, LinearLayoutManager.VERTICAL, false)

    }

    // fun On Pause
    override fun onPause() {
        super.onPause()
        recyclerView.adapter = null
        recyclerView.layoutManager = null
        recyclerView.adapter = bookAdapter
        recyclerView.layoutManager =
            LinearLayoutManager(requireView().context, LinearLayoutManager.VERTICAL, false)
        println("onPause")
    }

    // fun on Resume
    override fun onResume() {
        super.onResume()
        recyclerView.adapter = bookAdapter
        recyclerView.layoutManager =
            LinearLayoutManager(requireView().context, LinearLayoutManager.VERTICAL, false)
        println("onResume")
    }
}