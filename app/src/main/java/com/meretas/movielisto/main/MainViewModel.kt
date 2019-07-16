package com.meretas.movielisto.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.meretas.movielisto.data.MovieListData
import com.meretas.movielisto.data.TvListData
import com.meretas.movielisto.database.MovieDatabaseDao
import com.meretas.movielisto.services.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel(
    private val apiService: ApiService,
    val database: MovieDatabaseDao,
    application: Application
) : AndroidViewModel(application) {

    //MOVIE DATA
    private val _movieData: MutableLiveData<List<MovieListData.Result>> = MutableLiveData()
    val movieData: LiveData<List<MovieListData.Result>>
        get() = _movieData

    private val _isMovieLoading = MutableLiveData<Boolean>()
    val isMovieLoading: LiveData<Boolean>
        get() = _isMovieLoading

    private val _isMovieError = MutableLiveData<String>()
    val isMovieError: LiveData<String>
        get() = _isMovieError

    //TV DATA
    private val _tvSeriesData: MutableLiveData<List<TvListData.Result>> = MutableLiveData()
    val tvSeriesData: LiveData<List<TvListData.Result>>
        get() = _tvSeriesData

    private val _isTvLoading = MutableLiveData<Boolean>()
    val isTvLoading: LiveData<Boolean>
        get() = _isTvLoading

    private val _isTvError = MutableLiveData<String>()
    val isTvError: LiveData<String>
        get() = _isTvError

    //INITIATE
    init {
        _isMovieLoading.value = false
        _isMovieError.value = ""

        _isTvLoading.value = false
        _isTvError.value = ""
    }

    fun getMovieDataMovie() {
        _isMovieError.value = ""
        _isMovieLoading.value = true
        apiService.getMovie().enqueue(object : Callback<MovieListData> {
            override fun onResponse(call: Call<MovieListData>, response: Response<MovieListData>) {
                if (response.isSuccessful) {
                    val listMovie = response.body()?.results
                    _movieData.postValue(listMovie)
                    _isMovieLoading.value = false
                } else {
                    _isMovieError.value = "Terjadi Kesalahan"
                    _isMovieLoading.value = false
                }
            }

            override fun onFailure(call: Call<MovieListData>, t: Throwable) {
                _isMovieError.value = "Tidak Terkoneksi Internet"
                _isMovieLoading.value = false
            }
        })
    }

    fun searchMovie(searchInput: String){
        _isMovieError.value = ""
        _isMovieLoading.value = true
        apiService.searchMovie(searchInput).enqueue(object : Callback<MovieListData> {
            override fun onResponse(call: Call<MovieListData>, response: Response<MovieListData>) {
                if (response.isSuccessful) {
                    val listMovie = response.body()?.results
                    _movieData.postValue(listMovie)
                    _isMovieLoading.value = false
                } else {
                    _isMovieError.value = "Terjadi Kesalahan"
                    _isMovieLoading.value = false
                }
            }

            override fun onFailure(call: Call<MovieListData>, t: Throwable) {
                _isMovieError.value = "Terjadi Kesalahan"
                _isMovieLoading.value = false
            }
        })
    }

    fun getTVData() {
        _isTvError.value = ""
        _isTvLoading.value = true
        apiService.getTv().enqueue(object : Callback<TvListData> {
            override fun onResponse(call: Call<TvListData>, response: Response<TvListData>) {
                if (response.isSuccessful) {
                    val listTv = response.body()?.results
                    _tvSeriesData.postValue(listTv)
                    _isTvLoading.value = false
                } else {
                    _isTvError.value = "Terjadi Kesalahan"
                    _isTvLoading.value = false
                }
            }

            override fun onFailure(call: Call<TvListData>, t: Throwable) {
                _isTvLoading.value = false
            }
        })
    }

    fun searchTvData(searchInput: String) {
        _isTvError.value = ""
        _isTvLoading.value = true
        apiService.searchTv(searchInput).enqueue(object : Callback<TvListData> {
            override fun onResponse(call: Call<TvListData>, response: Response<TvListData>) {
                if (response.isSuccessful) {
                    val listTv = response.body()?.results
                    _tvSeriesData.postValue(listTv)
                    _isTvLoading.value = false
                } else {
                    _isTvError.value = "Terjadi Kesalahan"
                    _isTvLoading.value = false
                }
            }

            override fun onFailure(call: Call<TvListData>, t: Throwable) {
                _isTvLoading.value = false
            }
        })
    }


    override fun onCleared() {
        super.onCleared()
    }

}