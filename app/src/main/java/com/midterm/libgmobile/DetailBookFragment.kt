package com.midterm.libgmobile

import android.animation.LayoutTransition
import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.transition.AutoTransition
import android.transition.Transition
import android.transition.TransitionManager
import android.view.SurfaceControl.Transaction
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.ScrollView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.transition.platform.MaterialContainerTransform
import com.google.android.material.transition.platform.MaterialContainerTransformSharedElementCallback
import com.midterm.libgmobile.adapter.RvComment
import com.midterm.libgmobile.model.BookModel
import com.midterm.libgmobile.model.CommentModel

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
        listComment = listOf(
            CommentModel("1", "1", "1", "this is comment 1", "user 1", R.drawable.baseline_account_circle_24),
            CommentModel("2", "2", "2", "this is comment 2", "user 2", 2),
            CommentModel("3", "3", "3", "this is comment 3", "user 3", 3),
        )
        rvComment = view.findViewById(R.id.recyclerViewComment)
        cvComment = view.findViewById(R.id.tvCommentDetail)
        tvNoComment = view.findViewById(R.id.tvNoComment)
        cvLayout = view.findViewById(R.id.cvlayout)
        scrollView = view.findViewById(R.id.scrollView)
        cvLayout?.layoutTransition?.enableTransitionType(LayoutTransition.CHANGING)

        // set adapter for recyclerView
        setOnClickComment()
    }


    private fun setOnClickComment(){
        // set OnClickListener for textView Comment
        cvComment?.setOnClickListener {
            // set Transition for CardView
            val transition = AutoTransition()
            transition.duration = 1000
            TransitionManager.beginDelayedTransition(cvLayout, transition)
            if(rvComment?.visibility == View.GONE && tvNoComment?.visibility == View.GONE){
                if (listComment != null) {
                    rvComment?.visibility = View.VISIBLE
                    adapterComment = RvComment(listComment!!)
                    rvComment?.adapter = adapterComment
                    rvComment?.layoutManager =
                        LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
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