package com.iznan.bookapps.ui.sub

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.iznan.bookapps.R
import com.iznan.bookapps.ui.detail.DetailActivity
import com.iznan.bookapps.ui.main.PlaceholderFragment
import com.iznan.bookapps.viewModel.ViewModelFactory
import kotlinx.android.synthetic.main.activity_sub.*

class SubActivity : AppCompatActivity(), ISubItemClick {

    override fun onGenreClick(id: Int) {
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra(PlaceholderFragment.ID_EXTRA, id)
        intent.putExtra(PlaceholderFragment.PAGE_EXTRA, PlaceholderFragment.GENRE_PAGE)
        startActivity(intent)
    }

    private lateinit var subViewModel: SubViewModel
    private val adapter = SubDetailRecyclerViewAdapter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sub)
        toolbar.setNavigationOnClickListener {
            onBackPressed()
        }
        setupRecyclerView()

        subViewModel = obtainViewModel().apply {
            intent.getIntExtra(PlaceholderFragment.GENRE_EXTRA, 0).let {
                this.setBookList(it)
            }
        }

        observeViewModel()
    }

    fun observeViewModel() {
        subViewModel.bookList.observe(this, Observer<List<Pair<Int, String>>> {
            adapter.swapData(it)
        })
    }

    private fun obtainViewModel(): SubViewModel {
        val factory = ViewModelFactory.getInstance()
        return ViewModelProviders.of(this, factory).get(SubViewModel::class.java)
    }

    fun setupRecyclerView() {
        recycler_view_sub.layoutManager = LinearLayoutManager(this)
        recycler_view_sub.adapter = adapter
    }
}
