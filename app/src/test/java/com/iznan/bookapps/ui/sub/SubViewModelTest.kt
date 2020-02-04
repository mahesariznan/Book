package com.iznan.bookapps.ui.sub

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.iznan.bookapps.repo.RemoteRepository
import org.junit.Assert
import org.junit.Before
import org.junit.Test

import org.junit.Rule

class SubViewModelTest {

    private lateinit var viewModel:SubViewModel
    private val listOfBook = mutableListOf(
        Pair(26, "The Crown of The Heart"),
        Pair(398, "Setia Padamu, Selingkuh Denganmu"),
        Pair(392, "Womanizer")
    )

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        viewModel = SubViewModel(RemoteRepository())
        createDummyData()
    }

    private fun createDummyData() {
        viewModel.bookList.value = listOfBook
    }

    @Test
    fun getBookList() {
        val int = (0..2).random()
        Assert.assertNotNull(viewModel.bookList)
        Assert.assertEquals(listOfBook[int].first, viewModel.bookList.value?.get(int)?.first)
        Assert.assertEquals(listOfBook[int].second, viewModel.bookList.value?.get(int)?.second)
    }
}