package com.iznan.bookapps.ui.detail

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.iznan.bookapps.R
import com.iznan.bookapps.model.BookModel
import kotlinx.android.synthetic.main.related_book_view.view.*

class RelatedBookRecyclerViewAdapter(val iRelatedBookClick: IRelatedBookClick) : RecyclerView.Adapter<ViewHolder>() {

    private var dataBook = mutableListOf<BookModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.related_book_view, parent, false)
        return ViewHolderItem(view, iRelatedBookClick)
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

    class ViewHolderItem(val view: View, val iRelatedBookClick: IRelatedBookClick) : ViewHolder(view) {
        fun bindViewBook(item: BookModel) {
            view.textView_related_book.setText(item.title)
            Glide.with(itemView).load(item.image).into(view.imageView_related_book)
            itemView.setOnClickListener {
                iRelatedBookClick.onClickRelatedBook(item.id)
            }
        }
    }

    fun swapData(newData: List<BookModel>){
        dataBook.clear()
        if (newData.isNotEmpty()) {
            dataBook.addAll(newData)
        }
        notifyDataSetChanged()
    }
}