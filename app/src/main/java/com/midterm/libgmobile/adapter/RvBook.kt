package com.midterm.libgmobile.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.midterm.libgmobile.R
import com.midterm.libgmobile.model.BookModel

class RvBook(private var list : List<BookModel>, private var listener : RvBookIF)
    : RecyclerView.Adapter<RvBook.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvBookTitle: TextView = itemView.findViewById(R.id.tvBookTitle)
        var tvBookAuthor: TextView = itemView.findViewById(R.id.tvBookAuthor)
        var ivBook: ImageView = itemView.findViewById(R.id.ivBook)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.view_book_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.apply {
            holder.tvBookTitle.text = list[position].name
            holder.tvBookAuthor.text = list[position].author
            holder.ivBook.setImageResource(list[position].image)
        }
        holder.itemView.setOnClickListener {
            listener.onClick(position)
        }
    }
}