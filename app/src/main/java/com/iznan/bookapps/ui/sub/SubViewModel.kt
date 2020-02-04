package com.iznan.bookapps.ui.sub

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.iznan.bookapps.repo.RemoteRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class SubViewModel(private val remoteRepository: RemoteRepository) : ViewModel() {

    val compositeDisposable = CompositeDisposable()
    val bookList = MutableLiveData<List<Pair<Int, String>>>()

    fun setBookList(genre: Int){
        remoteRepository.api().getBookFromGenre(genre)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ response ->
                if (response != null && response.success != null && response.success) {
                    val listOfBook = mutableListOf<Pair<Int, String>>()
                    response.result?.let {
                        for (book in it) {
                            listOfBook.add(Pair(book!!.id!!, book.title!!))
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

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }

}