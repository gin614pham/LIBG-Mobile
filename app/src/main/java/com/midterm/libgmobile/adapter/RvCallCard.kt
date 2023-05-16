package com.midterm.libgmobile.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.midterm.libgmobile.R
import com.midterm.libgmobile.model.CallCardModel

class RvCallCard(private var list: List<CallCardModel>, private var listener: RvCallCardIF)
    : RecyclerView.Adapter<RvCallCard.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvNameUser: TextView = itemView.findViewById(R.id.tvNameUserCallCardItem)
        var tvTime: TextView = itemView.findViewById(R.id.tvTimeCallCardItem)
        var tvNameBook: TextView = itemView.findViewById(R.id.tvNameBookCallCardItem)
        var tvStatus: TextView = itemView.findViewById(R.id.tvStatus)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.view_call_card_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.apply {
            holder.tvNameUser.text = list[position].name_user
            holder.tvTime.text = list[position].date
            holder.tvNameBook.text = list[position].name_book
            holder.tvStatus.setCompoundDrawablesWithIntrinsicBounds(setStatus(list[position].status), 0, 0, 0)
//            holder.tvStatus.setTextColor(getColor(list[position].status))
            holder.tvStatus.text = list[position].status

        }
        holder.itemView.setOnClickListener {
            listener.onClick(position)
        }
    }

    private fun setStatus(status: String): Int {
        return when (status) {
            "Pending" -> R.drawable.pending_20px
            "Unpaid" -> R.drawable.hourglass_empty_20px
            "Done" -> R.drawable.done_all_20px
            else -> R.drawable.block_20px
        }
    }

//    private fun getColor(status: String): Int {
//        return when (status) {
//            "Pending" -> R.color.pending
//            "Unpaid" -> R.color.unpaid
//            "Done" -> R.color.done
//            else -> R.color.refuse
//        }
//    }


}