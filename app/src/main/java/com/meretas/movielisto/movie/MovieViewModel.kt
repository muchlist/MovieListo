package com.meretas.movielisto.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.meretas.movielisto.data.MovieListData
import com.meretas.movielisto.services.Api
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieViewModel : ViewModel() {

    private val _movieData: MutableLiveData<List<MovieListData.Result>> = MutableLiveData()
    val movieData : LiveData<List<MovieListData.Result>>
        get() = _movieData

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading : LiveData<Boolean>
        get() = _isLoading

    private val _isError = MutableLiveData<String>()
    val isError : LiveData<String>
        get() = _isError

    init {
        _isLoading.value = false
        _isError.value = ""
    }

    fun getMovieData() {
        _isError.value = ""
        _isLoading.value = true
        Api.retrofitService.getMovie().enqueue(object : Callback<MovieListData> {
            override fun onResponse(call: Call<MovieListData>, response: Response<MovieListData>) {
                if (response.isSuccessful) {
                    val listMovie = response.body()?.results
                    _movieData.postValue(listMovie)
                    _isLoading.value = false
                } else {
                    _isError.value = "Terjadi Kesalahan"
                    _isLoading.value = false
                }
            }
            override fun onFailure(call: Call<MovieListData>, t: Throwable) {
                _isError.value = "Tidak Terkoneksi Internet"
                _isLoading.value = false
            }
        })
    }
}