package com.iznan.bookapps.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.iznan.bookapps.di.Injection
import com.iznan.bookapps.repo.RemoteRepository
import com.iznan.bookapps.ui.detail.DetailViewModel
import com.iznan.bookapps.ui.main.PageViewModel
import com.iznan.bookapps.ui.sub.SubViewModel

class ViewModelFactory(val remoteRepository: RemoteRepository) :
    ViewModelProvider.NewInstanceFactory() {

    companion object {
        @Volatile
        private var INSTANCE: ViewModelFactory? = null

        fun getInstance(): ViewModelFactory =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: ViewModelFactory(Injection().provideRepository()).also { INSTANCE = it }
            }
    }

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PageViewModel::class.java)) {
            return PageViewModel(remoteRepository) as T
        } else if (modelClass.isAssignableFrom(DetailViewModel::class.java)) {
            return DetailViewModel(remoteRepository) as T
        } else if(modelClass.isAssignableFrom(SubViewModel::class.java)){
            return SubViewModel(remoteRepository) as T
        }

        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }
}