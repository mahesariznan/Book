package com.iznan.bookapps.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.iznan.bookapps.repo.RemoteRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class PageViewModel(private val remoteRepository: RemoteRepository) : ViewModel() {

    val bookList = MutableLiveData<List<Pair<Int, String>>>()
    val genreList = MutableLiveData<List<Pair<Int, String>>>()
    val writerList = MutableLiveData<List<Pair<Int, String>>>()
    val compositeDisposable = CompositeDisposable()

    fun setBookList() {
        remoteRepository.api().getBookList()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ response ->
                if (response != null && response.success != null && response.success) {
                    val listOfBook = mutableListOf<Pair<Int, String>>()
                    response.result?.let {
                        for (book in it) {
                            listOfBook.add(Pair(book!!.bookId!!, book.title!!))
                        }
                    }
                    bookList.value = listOfBook
                }
            }, { throwable ->
                throwable.printStackTrace()
            }).also {
                compositeDisposable.add(it)
            }
    }

    fun setGenreList() {
        remoteRepository.api().getGenreList()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ response ->
                if (response != null) {
                    val listOfGenre = mutableListOf<Pair<Int, String>>()
                    response.resource.let {
                        for (resource in it!!) {
                            listOfGenre.add(Pair(resource!!.id!!, resource.title!!))
                        }
                    }
                    genreList.value = listOfGenre
                }
            }, { throwable ->
                throwable.printStackTrace()
            }).also {
                compositeDisposable.add(it)
            }
    }

    fun setWriterList() {
        remoteRepository.api().getWriterList()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ response ->
                if (response != null && response.success != null && response.success) {
                    val listOfWriter = mutableListOf<Pair<Int, String>>()
                    response.result.let {
                        for (item in it!!) {
                            listOfWriter.add(Pair(item!!.userId!!, item.name!!))
                        }
                    }
                    writerList.value = listOfWriter
                }
            }, { throwable ->
                throwable.printStackTrace()
            }).also {
                compositeDisposable.add(it)
            }
    }

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }
}