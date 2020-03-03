package com.iznan.bookapps.ui.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.iznan.bookapps.MyNDK
import com.iznan.bookapps.model.BookModel
import com.iznan.bookapps.repo.RemoteRepository
import com.iznan.bookapps.repo.RemoteRepository.Companion.BASE_API
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class DetailViewModel(private val remoteRepository: RemoteRepository) : ViewModel() {

    val compositeDisposable = CompositeDisposable()
    val title = MutableLiveData<String>()
    val desc = MutableLiveData<String>()
    val writer = MutableLiveData<String>()
    val poster = MutableLiveData<String>()
    val genres = MutableLiveData<List<String>>()
    val status = MutableLiveData<String>()
    val email = MutableLiveData<String>()
    val follower = MutableLiveData<String>()
    val karya = MutableLiveData<List<String>>()
    val relatedBooks = MutableLiveData<List<BookModel>>()

    fun setBookDetail(id: Int) {
        remoteRepository.api().getBookDetail(id.toString())
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ response ->
                if (response != null && response.success != null && response.success) {
                    response.result?.let {
                        title.value = it.title
                        desc.value = it.synopsis
                        poster.value = BASE_API + "files/" + it.coverUrl + MyNDK.getApiKey()
                        writer.value = it.writerByWriterId?.userByUserId?.name
                        status.value = it.status
                        it.genres?.let {
                            val listOfGenre = mutableListOf<String>()
                            for (genre in it) {
                                listOfGenre.add(genre!!.title!!)
                            }
                            genres.value = listOfGenre
                        }

                        it.relatedByBook?.let {
                            val listOfRelatedBook = mutableListOf<BookModel>()
                            for (relatedBook in it) {
                                listOfRelatedBook.add(
                                    BookModel(
                                        relatedBook!!.id!!,
                                        BASE_API + "files/" + relatedBook.coverUrl!! + MyNDK.getApiKey(),
                                        relatedBook.title!!
                                    )
                                )
                            }
                            relatedBooks.value = listOfRelatedBook
                        }
                    }
                }
            }, { throwable ->
                throwable.printStackTrace()
            }).also {
                compositeDisposable.add(it)
            }
    }

    fun setWriterDetail(id: Int) {
        remoteRepository.api().getWriterDetail(id.toString())
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ response ->
                if (response != null && response.success != null && response.success) {
                    response.result?.let {
                        title.value = it.name
                        desc.value = it.deskripsi
                        poster.value = BASE_API + "files/" + it.photoUrl + MyNDK.getApiKey()
                        email.value = it.email
                        follower.value = it.countFollower?.toString()
                        it.karya?.let {
                            val listOfTitle = mutableListOf<String>()
                            for (karya in it) {
                                listOfTitle.add(karya!!.title!!)
                            }
                            karya.value = listOfTitle

                            val listOfRelatedBook = mutableListOf<BookModel>()
                            for (relatedBook in it) {
                                listOfRelatedBook.add(
                                    BookModel(
                                        relatedBook!!.id!!,
                                        BASE_API + "files/" + relatedBook.coverUrl!! + MyNDK.getApiKey(),
                                        relatedBook.title!!
                                    )
                                )
                            }
                            relatedBooks.value = listOfRelatedBook
                        }
                    }
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