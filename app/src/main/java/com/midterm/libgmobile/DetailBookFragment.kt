package com.midterm.libgmobile

import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.midterm.libgmobile.model.BookModel

/**
 * A simple [Fragment] subclass.
 * Use the [DetailBookFragment] factory method to
 * create an instance of this fragment.
 */
@Suppress("DEPRECATION")
class DetailBookFragment : Fragment(R.layout.fragment_detail_book) {
    private var book: BookModel? = null

//    override fun onCreateView(
//        inflater: LayoutInflater,
//        container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        val view = inflater.inflate(R.layout.fragment_detail_book, container, false)
//        view.findViewById<EditText>(R.id.etBookNameDetail).setText(book?.name)
//        view.findViewById<EditText>(R.id.etBookAuthorDetail).setText(book?.author)
//        view.findViewById<EditText>(R.id.etBookDescriptionDetail).setText(book?.description)
//        view.findViewById<EditText>(R.id.etBookPriceDetail).setText(book?.price)
//        view.findViewById<EditText>(R.id.etBookRatingDetail).setText(book?.rating)
//        view.findViewById<ImageView>(R.id.ivBookDetail).setImageResource(book?.image!!)
//        return view
//    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (arguments != null) {
            book = arguments?.getSerializable("book") as BookModel
            view.findViewById<EditText>(R.id.etBookNameDetail).setText(book?.name)
            view.findViewById<EditText>(R.id.etBookAuthorDetail).setText(book?.author)
            view.findViewById<EditText>(R.id.etBookDescriptionDetail).setText(book?.description)
            view.findViewById<EditText>(R.id.etBookPriceDetail).setText(book?.price)
            view.findViewById<EditText>(R.id.etBookRatingDetail).setText(book?.rating)
            view.findViewById<ImageView>(R.id.ivBookDetail).setImageResource(book?.image!!)
        }
    }
//
//    // fun set book to view
//    private fun setBook(book: BookModel) {
//        findViewById<TextView>(R.id.tv_name).text = book.name
//    }

}