package com.midterm.libgmobile

import android.os.Bundle
import android.transition.TransitionInflater
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.transition.platform.MaterialContainerTransformSharedElementCallback
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
            ), BookModel(
                "3",
                "Book 3",
                "Author 3",
                "Description 3",
                R.drawable.toi_tu_hoc_top_sach_hay_cho_hoc_sinh_sinh_vien_69ca39f78c,
                "20",
                "5"
            ), BookModel(
                "4",
                "Book 4",
                "Author 4",
                "Description 4",
                R.drawable.toi_tu_hoc_top_sach_hay_cho_hoc_sinh_sinh_vien_69ca39f78c,
                "20",
                "5"
            ), BookModel(
                "5",
                "Book 5",
                "Author 5",
                "Description 5",
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

                val changeTransfrom = TransitionInflater.from(requireContext())
                    .inflateTransition(R.transition.change_image_transform)
                val explodeTransform = TransitionInflater.from(requireContext())
                    .inflateTransition(android.R.transition.explode)
                // cài đặt hiệu ứng khi thoát khỏi fragment hiện tại
                fragment.sharedElementEnterTransition = changeTransfrom
                fragment.enterTransition = explodeTransform
                val imageView: ImageView = view.findViewById(R.id.ivBook)
                fragment.arguments = bundle
                requireActivity().supportFragmentManager.beginTransaction()
                    .replace(R.id.frame_layout, fragment)
                    .addSharedElement(imageView,"book_detail")
                    .addToBackStack("transaction")
                    .addToBackStack(null)
                    .commit()
            }
        })
        recyclerView.adapter = bookAdapter
        recyclerView.layoutManager =
            LinearLayoutManager(view.context, LinearLayoutManager.VERTICAL, false)

    }

}