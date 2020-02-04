package com.iznan.bookapps.ui.main

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.iznan.bookapps.R
import com.iznan.bookapps.ui.detail.DetailActivity
import com.iznan.bookapps.ui.sub.SubActivity
import com.iznan.bookapps.viewModel.ViewModelFactory
import kotlinx.android.synthetic.main.fragment_main.view.*

class PlaceholderFragment : Fragment(), IItemClick {
    override fun onItemGenreClick(id: Int) {
        val intent = Intent(context, SubActivity::class.java)
        intent.putExtra(GENRE_EXTRA, id)
        startActivity(intent)
    }

    override fun onItemWriterClick(id: Int) {
        val intent = Intent(context, DetailActivity::class.java)
        intent.putExtra(ID_EXTRA, id)
        intent.putExtra(PAGE_EXTRA, WRITER_PAGE)
        startActivity(intent)
    }

    override fun onItemBookClick(id: Int) {
        val intent = Intent(context, DetailActivity::class.java)
        intent.putExtra(PAGE_EXTRA, NEW_BOOK_PAGE)
        intent.putExtra(ID_EXTRA, id)
        startActivity(intent)
    }

    private lateinit var pageViewModel: PageViewModel
    private lateinit var root: View
    private val adapter = AdapterRecyclerView(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        pageViewModel = obtainViewModel().apply {
            when (arguments?.getInt(ARG_SECTION_NUMBER)) {
                2 -> setGenreList()
                3 -> setWriterList()
                else -> setBookList()
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        root = inflater.inflate(R.layout.fragment_main, container, false)
        setupRecyclerView()
        when (arguments?.getInt(ARG_SECTION_NUMBER)) {
            2 -> pageViewModel.genreList.observe(this, Observer<List<Pair<Int, String>>> {
                adapter.swapDataGenre(it)
            })
            3 -> pageViewModel.writerList.observe(this, Observer<List<Pair<Int, String>>> {
                adapter.swapDataWriter(it)
            })
            else -> pageViewModel.bookList.observe(this, Observer<List<Pair<Int, String>>> {
                adapter.swapDataBook(it)
            })
        }

        return root
    }

    companion object {
        private const val ARG_SECTION_NUMBER = "section_number"
        const val ID_EXTRA = "id xtra"
        const val PAGE_EXTRA = "page xtra"
        const val WRITER_PAGE = "writer page"
        const val NEW_BOOK_PAGE = "new book page"
        const val GENRE_PAGE = "genre page"
        const val GENRE_EXTRA = "genre extra"
        @JvmStatic
        fun newInstance(sectionNumber: Int): PlaceholderFragment {
            return PlaceholderFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_SECTION_NUMBER, sectionNumber)
                }
            }
        }
    }

    private fun obtainViewModel(): PageViewModel {
        val factory = ViewModelFactory.getInstance()
        return ViewModelProviders.of(this, factory).get(PageViewModel::class.java)
    }

    fun setupRecyclerView() {
        root.rc.layoutManager = LinearLayoutManager(context)
        root.rc.adapter = adapter
    }
}