package com.midterm.libgmobile

import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ImageView
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.midterm.libgmobile.model.BookModel
import com.midterm.libgmobile.model.UserModel


/**
 * A simple [Fragment] subclass.
 * Use the [AddBookFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AddBookFragment : Fragment(R.layout.fragment_add_book) {
    private var user = UserModel()
    private var book = BookModel()
    private var imageUri: Uri? = null
    private var isUpdateImage = false

    @Suppress("DEPRECATION")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // get data from bundle
        if (arguments != null) {
            user = arguments?.getSerializable("user") as UserModel
            book = arguments?.getSerializable("book") as BookModel
            setView(view)
        }
        val image = view.findViewById<ImageView>(R.id.ivBookAdd)
        val pickerMediaRequest = registerForActivityResult(
            ActivityResultContracts.PickVisualMedia()
        ) {uri ->
            if (uri != null) {
                book.image = uri.toString()
                Glide.with(this).load(uri).into(image)
                image.visibility = View.VISIBLE
                imageUri = uri
                isUpdateImage = true
            }
        }

        view.findViewById<ImageButton>(R.id.imgBtnAdd).setOnClickListener() {
            pickerMediaRequest.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
        }

        view.findViewById<Button>(R.id.btnAddBook).setOnClickListener(){
            book.name = view.findViewById<EditText>(R.id.etNameBookAdd).text.toString()
            book.author = view.findViewById<EditText>(R.id.etBookAuthorAdd).text.toString()
            book.description = view.findViewById<EditText>(R.id.etBookDescriptionAdd).text.toString()
            book.price = view.findViewById<EditText>(R.id.etBookPriceAdd).text.toString()
            book.pushBook(requireActivity())
            if (isUpdateImage) {
                book.pushImage(imageUri!!)
            }
            clearView(view)
        }

        view.findViewById<Button>(R.id.btnBackAdd).setOnClickListener(){
            clearView(view)
            // close this fragment
            requireActivity().supportFragmentManager.popBackStack()
        }


    }

    private fun clearView(view: View){
        view.findViewById<EditText>(R.id.etNameBookAdd).text.clear()
        view.findViewById<EditText>(R.id.etBookAuthorAdd).text.clear()
        view.findViewById<EditText>(R.id.etBookDescriptionAdd).text.clear()
        view.findViewById<EditText>(R.id.etBookPriceAdd).text.clear()
        view.findViewById<ImageView>(R.id.ivBookAdd).visibility = View.GONE
    }

    private fun setView(view: View){
        if (book.id != ""){
            view.findViewById<EditText>(R.id.etNameBookAdd).setText(book.name)
            view.findViewById<EditText>(R.id.etBookAuthorAdd).setText(book.author)
            view.findViewById<EditText>(R.id.etBookDescriptionAdd).setText(book.description)
            view.findViewById<EditText>(R.id.etBookPriceAdd).setText(book.price)
            Glide.with(this).load(book.image).into(view.findViewById(R.id.ivBookAdd))
            view.findViewById<ImageView>(R.id.ivBookAdd).visibility = View.VISIBLE
            imageUri = Uri.parse(book.image)
        }

    }

}