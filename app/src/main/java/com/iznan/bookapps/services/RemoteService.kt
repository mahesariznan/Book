package com.iznan.bookapps.services

import com.iznan.bookapps.model.bookDetailResponse.BookDetailResponse
import com.iznan.bookapps.model.bookFromGenreResponse.BookFromGenreResponse
import com.iznan.bookapps.model.bookResponse.BookListResponse
import com.iznan.bookapps.model.genreResponse.GenreResponse
import com.iznan.bookapps.model.writerDetailResponse.WriterDetailResponse
import com.iznan.bookapps.model.writerResponse.WriterListResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RemoteService {

    @GET("cabaca/_table/genre")
    fun getGenreList(): Single<GenreResponse>

    @GET("book/uptodate?limit=7")
    fun getBookList(): Single<BookListResponse>

    @GET("writer/popular?limit=10")
    fun getWriterList(): Single<WriterListResponse>

    @GET("book/detail/{book_id}")
    fun getBookDetail(@Path("book_id") book_id: String): Single<BookDetailResponse>

    @GET("writer/detail/{user_id}")
    fun getWriterDetail(@Path("user_id") user_id: String): Single<WriterDetailResponse>

    @GET("book/category")
    fun getBookFromGenre(@Query("id") genre_id: Int): Single<BookFromGenreResponse>
}