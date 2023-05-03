package com.midterm.libgmobile.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.midterm.libgmobile.R
import com.midterm.libgmobile.model.CommentModel

class RvComment(private var list : List<CommentModel>) : RecyclerView.Adapter<RvComment.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvComment: TextView = itemView.findViewById(R.id.tvCommentInfo)
        val tvNameComment: TextView = itemView.findViewById(R.id.tvNameComment)
        val imgAvatarComment: ImageView = itemView.findViewById(R.id.imgAvatarComment)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.view_book_comment, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.apply {
            holder.tvComment.text = list[position].comment
            holder.tvNameComment.text = list[position].name
            holder.imgAvatarComment.setImageResource(list[position].avatar)
        }
    }
}