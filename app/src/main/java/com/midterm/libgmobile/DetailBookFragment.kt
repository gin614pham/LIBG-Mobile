package com.midterm.libgmobile

import android.animation.LayoutTransition
import android.annotation.SuppressLint
import android.os.Bundle
import android.transition.AutoTransition
import android.transition.TransitionManager
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ScrollView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.midterm.libgmobile.adapter.RvComment
import com.midterm.libgmobile.model.BookModel
import com.midterm.libgmobile.model.CommentModel
import com.midterm.libgmobile.model.UserModel
import com.midterm.libgmobile.service.DialogMenuBook

/**
 * A simple [Fragment] subclass.
 * Use the [DetailBookFragment] factory method to
 * create an instance of this fragment.
 */
@Suppress("DEPRECATION")
class DetailBookFragment : Fragment(R.layout.fragment_detail_book) {
    private var book: BookModel? = null
    private var rvComment: RecyclerView? = null
    private var cvComment: TextView? = null
    private var tvNoComment: TextView? = null
    private var adapterComment: RvComment? = null
    private var listComment: List<CommentModel>? = null
    private var cvLayout: CardView? = null
    private var scrollView: ScrollView? = null
    private var userModel: UserModel = UserModel()

    @SuppressLint("ResourceAsColor")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    @SuppressLint("CutPasteId")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (arguments != null) {
            book = arguments?.getSerializable("book") as BookModel
            userModel = arguments?.getSerializable("user") as UserModel
            val menu = view.findViewById<Button>(R.id.btnMenuDetail)
            if (userModel.role == "Admin") {
                menu.visibility = View.VISIBLE
                menu.setOnClickListener {
                    showDialog()
                }
            } else {
                menu.visibility = View.GONE
            }
            view.findViewById<TextView>(R.id.etBookNameDetail).text = book?.name
            view.findViewById<EditText>(R.id.etBookAuthorDetail).setText(book?.author)
            view.findViewById<EditText>(R.id.etBookDescriptionDetail).setText(book?.description)
            view.findViewById<EditText>(R.id.etBookPriceDetail).setText(book?.price)
            view.findViewById<EditText>(R.id.etBookRatingDetail).setText(book?.rating)
            Glide.with(this).load(book?.image).into(view.findViewById(R.id.ivBookDetail))
        }
        // set OnClickListener for Button Back to do back to home
        view.findViewById<Button>(R.id.btnBackDetail).setOnClickListener {
            activity?.onBackPressed()
        }
        view.findViewById<FloatingActionButton>(R.id.fabFavorite).setOnClickListener {
            createCallCardRequest()
        }
        listComment = listOf(
            CommentModel("1", "1", "1", "this is comment 1", "user 1", R.drawable.baseline_account_circle_24),
            CommentModel("2", "2", "2", "this is comment 2", "user 2", 2),
            CommentModel("3", "3", "3", "this is comment 3", "user 3", 3),
        )
        rvComment = view.findViewById(R.id.recyclerViewComment)
        cvComment = view.findViewById(R.id.tvCommentDetail)
        tvNoComment = view.findViewById(R.id.tvNoComment)
        cvLayout = view.findViewById(R.id.cvlayout)
        scrollView = view.findViewById(R.id.scrollview)
        cvLayout?.layoutTransition?.enableTransitionType(LayoutTransition.CHANGING)

        // set adapter for recyclerView
        setOnClickComment()
    }

    private fun createCallCardRequest() {
        val callCardRequestFragment = CallCardRequestFragment()
        val bundle = Bundle()
        bundle.putSerializable("book", book)
        bundle.putSerializable("user", userModel)
        callCardRequestFragment.arguments = bundle
        val fragmentManager = requireActivity().supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.setCustomAnimations(
            android.R.anim.slide_in_left,
            android.R.anim.slide_out_right
        )
        fragmentTransaction.replace(R.id.frame_layout, callCardRequestFragment)
        fragmentTransaction.addToBackStack(this.tag)
        fragmentTransaction.commit()
    }

    private fun showDialog() {
        val dialog = DialogMenuBook()
        val editListener = View.OnClickListener {
            // push data to fragment AddBookFragment
            val addBookFragment = AddBookFragment()
            val bundle = Bundle()
            bundle.putSerializable("book", book)
            bundle.putSerializable("user", userModel)
            addBookFragment.arguments = bundle

            val fragmentManager = requireActivity().supportFragmentManager
            val fragmentTransaction = fragmentManager.beginTransaction()
            fragmentTransaction.setCustomAnimations(
                android.R.anim.slide_in_left,
                android.R.anim.slide_out_right
            )
            fragmentTransaction.replace(R.id.frame_layout, addBookFragment)
            fragmentTransaction.addToBackStack(this.tag)
            fragmentTransaction.commit()
            dialog.dismiss()
        }
        val deleteListener = View.OnClickListener {
            dialog.dismiss()
            // create dialog for confirm delete
            val builder = androidx.appcompat.app.AlertDialog.Builder(requireContext())
            builder.setTitle(getString(R.string.delete_book))
            builder.setMessage(getString(R.string.are_you_sure_you_want_to_delete_this_book)+"\n"+book?.name)
            builder.setPositiveButton("Yes") { dialog, _ ->
                dialog.dismiss()
                book?.deleteBook(requireActivity())
                activity?.onBackPressed()
            }
            builder.setNegativeButton("No") { dialog, _ ->
                dialog.dismiss()
            }
            builder.show()
        }
        dialog.show(requireContext(), editListener, deleteListener)

    }


    private fun setOnClickComment(){
        // set OnClickListener for textView Comment
        cvComment?.setOnClickListener {
            // set Transition for CardView
            val transition = AutoTransition()
            transition.duration = 500
            TransitionManager.beginDelayedTransition(cvLayout, transition)
            if(rvComment?.visibility == View.GONE && tvNoComment?.visibility == View.GONE){
                if (listComment != null) {
                    rvComment?.visibility = View.VISIBLE
                    adapterComment = RvComment(listComment!!)
                    rvComment?.adapter = adapterComment
                    rvComment?.layoutManager =
                        LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                    tvNoComment?.visibility = View.GONE
                    scrollView!!.post {
                        scrollView!!.fullScroll(View.FOCUS_DOWN)
                    }
                } else {
                    rvComment?.visibility = View.GONE
                    tvNoComment?.visibility = View.VISIBLE
                    scrollView!!.post {
                        scrollView!!.fullScroll(View.FOCUS_DOWN)
                    }
                }
            } else {
                rvComment?.visibility = View.GONE
                tvNoComment?.visibility = View.GONE
            }

        }
    }


}