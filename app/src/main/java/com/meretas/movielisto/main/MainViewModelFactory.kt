package com.meretas.movielisto.main

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.meretas.movielisto.database.MovieDatabaseDao
import com.meretas.movielisto.services.ApiService

class MainViewModelFactory(
    private val apiService: ApiService,
    private val dataSource: MovieDatabaseDao,
    private val application: Application
) : ViewModelProvider.Factory {
    @Suppress("uncheked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(apiService, dataSource, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel Class")
    }

}