package com.iznan.bookapps.ui.sub

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.iznan.bookapps.R
import kotlinx.android.synthetic.main.activity_detail.view.*

class SubDetailRecyclerViewAdapter(val iSubItemClick: ISubItemClick) : RecyclerView.Adapter<ViewHolder>() {

    private var dataBook = mutableListOf<Pair<Int, String>>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.title_recycler_view, parent, false)
        return ViewHolderItem(view, iSubItemClick)
    }

    override fun getItemCount(): Int {
        if (dataBook.size != 0) {
            return dataBook.size
        }
        return 0
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (holder is ViewHolderItem) {
            if (dataBook.size != 0) {
                holder.bindViewBook(dataBook[position])
            }
        }
    }

    class ViewHolderItem(val view: View, val iSubItemClick: ISubItemClick) :
        ViewHolder(view) {
        fun bindViewBook(item: Pair<Int, String>) {
            view.textView_title.setText(item.second)
            itemView.setOnClickListener {
                iSubItemClick.onGenreClick(item.first)
            }
        }
    }

    fun swapData(newData: List<Pair<Int, String>>) {
        dataBook.clear()
        if (newData.isNotEmpty()) {
            dataBook.addAll(newData)
        }
        notifyDataSetChanged()
    }
}