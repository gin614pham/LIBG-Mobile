package com.midterm.libgmobile

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.speech.RecognizerIntent
import android.view.View
import android.widget.Button
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.midterm.libgmobile.adapter.RvBook
import com.midterm.libgmobile.adapter.RvBookIF
import com.midterm.libgmobile.model.UserModel
import com.midterm.libgmobile.service.BookService


class SearchFragment : Fragment(R.layout.fragment_search) {
    private val SPEECH_REQUEST_CODE = 0
    private lateinit var recyclerView: RecyclerView
    private lateinit var bookAdapter: RvBook
    private val bookService = BookService()
    private var userModel: UserModel? = null

    @Suppress("DEPRECATION")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if(arguments != null){
            userModel = arguments?.getSerializable("user") as UserModel
        }
        recyclerView = view.findViewById(R.id.recyclerviewSearch)
        recyclerView.layoutManager =
            LinearLayoutManager(view.context, LinearLayoutManager.VERTICAL, false)
        setData("")
        val search = view.findViewById<SearchView>(R.id.search_view)
        search.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                val text = query.toString()
                setData(text)
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                val text = newText.toString()
                setData(text)
                return true
            }
        })
        view.findViewById<Button>(R.id.btnSearch).setOnClickListener {
            displaySpeechRecognizer()
        }
    }

    private fun setData(text: String) {
        bookService.filterBook(text){list ->
            bookAdapter = RvBook(list, object : RvBookIF {
                override fun onClick(position: Int) {
                    val bundle = Bundle()
                    bundle.putSerializable("book", list[position])
                    bundle.putSerializable("user", userModel)
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
        }
    }

    @Suppress("DEPRECATION")
    private fun displaySpeechRecognizer() {
        val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH).apply {
            putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
        }
         startActivityForResult(intent, SPEECH_REQUEST_CODE)

    }

    @Suppress("DEPRECATION")
    @Deprecated("Deprecated in Java", ReplaceWith(
        "super.onActivityResult(requestCode, resultCode, data)",
        "androidx.fragment.app.Fragment"
    )
    )
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == SPEECH_REQUEST_CODE) {
            if (resultCode == Activity.RESULT_OK ) {
                val matches = data?.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)
                val text = matches?.get(0)
                setData(text.toString())
            }
        }
        super.onActivityResult(requestCode, resultCode, data)
    }

}