package com.meretas.movielisto.favorite

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.meretas.movielisto.data.MovieDataMin
import com.meretas.movielisto.database.MovieDatabaseDao
import kotlinx.coroutines.*

class FavoriteViewModel(
    val database: MovieDatabaseDao,
    application: Application
) : AndroidViewModel(application) {

    private var viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    private val _movieData: MutableLiveData<List<MovieDataMin>> = MutableLiveData()
    val movieData: LiveData<List<MovieDataMin>>
        get() = _movieData

    fun getMovieData() {
        uiScope.launch {
            getMovieDataFromDatabase()
        }
    }

    private suspend fun getMovieDataFromDatabase() {
        withContext(Dispatchers.IO) {
            _movieData.postValue(database.getMovies())
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

}