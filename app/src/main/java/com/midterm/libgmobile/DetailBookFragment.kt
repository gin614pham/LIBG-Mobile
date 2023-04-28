package com.midterm.libgmobile

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.google.android.material.transition.platform.MaterialContainerTransform
import com.google.android.material.transition.platform.MaterialContainerTransformSharedElementCallback
import com.midterm.libgmobile.model.BookModel

/**
 * A simple [Fragment] subclass.
 * Use the [DetailBookFragment] factory method to
 * create an instance of this fragment.
 */
@Suppress("DEPRECATION")
class DetailBookFragment : Fragment(R.layout.fragment_detail_book) {
    private var book: BookModel? = null

    @SuppressLint("ResourceAsColor")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (arguments != null) {
            book = arguments?.getSerializable("book") as BookModel
            view.findViewById<TextView>(R.id.etBookNameDetail).text = book?.name
            view.findViewById<EditText>(R.id.etBookAuthorDetail).setText(book?.author)
            view.findViewById<EditText>(R.id.etBookDescriptionDetail).setText(book?.description)
            view.findViewById<EditText>(R.id.etBookPriceDetail).setText(book?.price)
            view.findViewById<EditText>(R.id.etBookRatingDetail).setText(book?.rating)
            view.findViewById<ImageView>(R.id.ivBookDetail).setImageResource(book?.image!!)
        }
        // set OnClickListener for Button Back to do back to home
        view.findViewById<Button>(R.id.btnBackDetail).setOnClickListener {
            activity?.onBackPressed()
        }
    }


}