package com.iznan.bookapps.ui.detail

import android.content.Intent
import android.os.Bundle
import android.text.Html
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.iznan.bookapps.R
import com.iznan.bookapps.model.BookModel
import com.iznan.bookapps.ui.main.PlaceholderFragment
import com.iznan.bookapps.viewModel.ViewModelFactory
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity(), IRelatedBookClick {

    companion object {
        const val DETAIL_PAGE = "detail page"
    }

    override fun onClickRelatedBook(id: Int) {
        finish()
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra(PlaceholderFragment.ID_EXTRA, id)
        intent.putExtra(PlaceholderFragment.PAGE_EXTRA, DETAIL_PAGE)
        startActivity(intent)
    }

    private lateinit var detailViewModel: DetailViewModel
    private val adapter = RelatedBookRecyclerViewAdapter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        detailViewModel = obtainViewModel()
        setupRecyclerView()

        intent.getStringExtra(PlaceholderFragment.PAGE_EXTRA).let {
            when (it) {
                PlaceholderFragment.WRITER_PAGE -> {
                    toolbar.setTitle(R.string.writer_detail)
                    val itemId = intent.getIntExtra(PlaceholderFragment.ID_EXTRA, 0)
                    detailViewModel.setWriterDetail(itemId)
                    observeWriterViewModel()
                    textView_sub_title.setText("Author’s books")
                }
                else -> {
                    toolbar.setTitle(R.string.book_detail)
                    val itemId = intent.getIntExtra(PlaceholderFragment.ID_EXTRA, 0)
                    detailViewModel.setBookDetail(itemId)
                    observeBookViewModel()
                    textView_sub_title.setText("Related Book")
                }
            }
        }
        toolbar.setNavigationOnClickListener {
            onBackPressed()
        }

    }

    fun observeWriterViewModel() {
        observeViewModel()
        detailViewModel.karya.observe(this, Observer<List<String>> {
            textView_genre.setText("Karya: $it");

        })
        detailViewModel.email.observe(this, Observer<String> {
            textView_writer.setText("Email: $it");

        })
        detailViewModel.follower.observe(this, Observer<String> {
            textView_status.setText("Follower: $it");

        })
    }

    fun observeBookViewModel() {
        observeViewModel()
        detailViewModel.genres.observe(this, Observer<List<String>> {
            textView_genre.setText("Genre: $it");

        })
        detailViewModel.writer.observe(this, Observer<String> {
            textView_writer.setText("Writer: $it");

        })
        detailViewModel.status.observe(this, Observer<String> {
            textView_status.setText("Status: $it");

        })
    }

    fun observeViewModel(){
        detailViewModel.title.observe(this, Observer<String> {
            textView_title.setText(it)
        })
        detailViewModel.desc.observe(this, Observer<String> {
            textView_detail.setText(Html.fromHtml(it, Html.FROM_HTML_MODE_LEGACY));

        })
        detailViewModel.poster.observe(this, Observer<String> {
            Glide.with(this).load(it).into(imageView)
        })
        detailViewModel.relatedBooks.observe(this, Observer<List<BookModel>> {
            adapter.swapData(it)
        })
    }

    private fun obtainViewModel(): DetailViewModel {
        val factory = ViewModelFactory.getInstance()
        return ViewModelProviders.of(this, factory).get(DetailViewModel::class.java)
    }

    fun setupRecyclerView() {
        recyclerView_related_book.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        recyclerView_related_book.adapter = adapter
    }
}
