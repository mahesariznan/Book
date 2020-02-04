package com.iznan.bookapps.ui.main

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.iznan.bookapps.repo.RemoteRepository
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class PageViewModelTest {

    private lateinit var viewModel: PageViewModel
    private val listOfBook = mutableListOf(
        Pair(26, "The Crown of The Heart"),
        Pair(398, "Setia Padamu, Selingkuh Denganmu"),
        Pair(392, "Womanizer")
    )
    private val listOfGenre = mutableListOf(
        Pair(1, "Romance"),
        Pair(2, "Adult"),
        Pair(3, "Comedy")
    )
    private val listOfWriter = mutableListOf(
        Pair(944, "J.P. SUNU"),
        Pair(461, "Ratna Aleefa"),
        Pair(32664, "LeeIndah7")
    )

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        viewModel = PageViewModel(RemoteRepository())
        createDummyData()
    }

    @Test
    fun getBookList() {
        val int = (0..2).random()
        Assert.assertNotNull(viewModel.bookList)
        Assert.assertEquals(listOfBook[int].first, viewModel.bookList.value?.get(int)?.first)
        Assert.assertEquals(listOfBook[int].second, viewModel.bookList.value?.get(int)?.second)
    }

    @Test
    fun getGenreList() {
        val int = (0..2).random()
        Assert.assertNotNull(viewModel.genreList)
        Assert.assertEquals(listOfGenre[int].first, viewModel.genreList.value?.get(int)?.first)
        Assert.assertEquals(listOfGenre[int].second, viewModel.genreList.value?.get(int)?.second)
    }

    @Test
    fun getWriterList() {
        val int = (0..2).random()
        Assert.assertNotNull(viewModel.writerList)
        Assert.assertEquals(listOfWriter[int].first, viewModel.writerList.value?.get(int)?.first)
        Assert.assertEquals(listOfWriter[int].second, viewModel.writerList.value?.get(int)?.second)
    }

    fun createDummyData() {
        viewModel.bookList.value = listOfBook
        viewModel.genreList.value = listOfGenre
        viewModel.writerList.value = listOfWriter
    }
}