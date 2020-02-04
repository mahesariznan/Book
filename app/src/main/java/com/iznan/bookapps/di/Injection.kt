package com.iznan.bookapps.di

import com.iznan.bookapps.repo.RemoteRepository

class Injection {
    fun provideRepository(): RemoteRepository {
        return RemoteRepository.getInstance()
    }
}