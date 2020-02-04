package com.iznan.bookapps.repo

import com.iznan.bookapps.services.RemoteService
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.util.concurrent.TimeUnit

class RemoteRepository {

    val service: RemoteService

    companion object {
        const val BASE_API = "https://cabaca.id:8443/api/v2/"
        const val API_KEY = "&api_key=32ded42cfffb77dee86a29f43d36a3641849d4b5904aade9a79e9aa6cd5b5948"

        @Volatile
        private var INSTANCE: RemoteRepository? = null

        fun getInstance(): RemoteRepository =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: RemoteRepository().also { INSTANCE = it }
            }
    }

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_API)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(buildOkHttp())
            .build()

        service = retrofit.create(RemoteService::class.java)
    }

    fun api(): RemoteService {
        return service
    }

    private fun buildOkHttp(): OkHttpClient {
        val interceptor = object : Interceptor {
            @Throws(IOException::class)
            override fun intercept(chain: Interceptor.Chain): okhttp3.Response {
                val newRequest = chain.request().newBuilder().addHeader("x-dreamfactory-api-key", "25e0bf00ab2fa7f03a9fa57035139e47ccb28c20658f6de907b8011347e369fb").build()
                return chain.proceed(newRequest)
            }
        }

        return OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .readTimeout(10, TimeUnit.SECONDS)
            .connectTimeout(30, TimeUnit.SECONDS)
            .build()
    }

}