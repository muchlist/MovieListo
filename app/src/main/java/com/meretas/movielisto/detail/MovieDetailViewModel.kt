package com.meretas.movielisto.detail

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.meretas.movielisto.data.MovieDataMin
import com.meretas.movielisto.database.MovieDatabaseDao
import kotlinx.coroutines.*

class MovieDetailViewModel(
    val database: MovieDatabaseDao,
    application: Application
) : AndroidViewModel(application) {

    private var viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    fun getFavorite(key: Int): LiveData<MovieDataMin> {
        return database.get(key)
    }

    fun insert(movie: MovieDataMin) {
        uiScope.launch {
            insertToDatabase(movie)
        }
    }

    private suspend fun insertToDatabase(movie: MovieDataMin) {
        withContext(Dispatchers.IO) {
            database.insert(movie)
        }
    }

    fun delete(key: Int) {
        uiScope.launch {
            deleteMovieInDatabase(key)
        }
    }

    private suspend fun deleteMovieInDatabase(key: Int) {
        withContext(Dispatchers.IO) {
            database.delete(key)
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}