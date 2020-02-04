package com.iznan.bookapps.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.iznan.bookapps.R
import kotlinx.android.synthetic.main.title_recycler_view.view.*

class AdapterRecyclerView(val iItemClick: IItemClick) : RecyclerView.Adapter<ViewHolder>() {

    private var dataBook = mutableListOf<Pair<Int, String>>()
    private var dataGenre = mutableListOf<Pair<Int, String>>()
    private var dataWriter = mutableListOf<Pair<Int, String>>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.title_recycler_view, parent, false)
        return ViewHolderItem(view, iItemClick)
    }

    override fun getItemCount(): Int {
        if (dataBook.size != 0) {
            return dataBook.size
        } else if (dataGenre.size != 0) {
            return dataGenre.size
        } else if (dataWriter.size != 0) {
            return dataWriter.size
        }
        return 0
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (holder is ViewHolderItem) {
            if (dataBook.size != 0) {
                holder.bindViewBook(dataBook[position])
            } else if (dataGenre.size != 0) {
                holder.bindViewGenre(dataGenre[position])
            } else if (dataWriter.size != 0) {
                holder.bindViewWriter(dataWriter[position])
            }
        }
    }

    class ViewHolderItem(val view: View, val iItemClick: IItemClick) : ViewHolder(view) {
        fun bindViewBook(item: Pair<Int, String>) {
            view.textView_title.setText(item.second)
            itemView.setOnClickListener {
                iItemClick.onItemBookClick(item.first)
            }
        }

        fun bindViewWriter(item: Pair<Int, String>) {
            view.textView_title.setText(item.second)
            itemView.setOnClickListener {
                iItemClick.onItemWriterClick(item.first)
            }
        }

        fun bindViewGenre(item: Pair<Int, String>) {
            view.textView_title.setText(item.second)
            itemView.setOnClickListener {
                iItemClick.onItemGenreClick(item.first)
            }
        }
    }

    fun swapDataBook(newData: List<Pair<Int, String>>) {
        dataBook.clear()
        if (newData.isNotEmpty()) {
            dataBook.addAll(newData)
        }
        notifyDataSetChanged()
    }

    fun swapDataGenre(newData: List<Pair<Int, String>>) {
        dataGenre.clear()
        if (newData.isNotEmpty()) {
            dataGenre.addAll(newData)
        }
        notifyDataSetChanged()
    }

    fun swapDataWriter(newData: List<Pair<Int, String>>) {
        dataWriter.clear()
        if (newData.isNotEmpty()) {
            dataWriter.addAll(newData)
        }
        notifyDataSetChanged()
    }

}