package com.meretas.movielisto.movie

import com.meretas.movielisto.data.MovieListData
import com.meretas.movielisto.services.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MoviePresenter(private var view: MovieView?, private val api: ApiService) {

    fun getMovieData() {
        view?.showLoading()
        api.getMovie().enqueue(object : Callback<MovieListData> {
            override fun onResponse(call: Call<MovieListData>, response: Response<MovieListData>) {
                if (response.isSuccessful) {
                    //menggunakan !! karena sudah pasti ada datanya
                    val listMovie = response.body()!!.results
                    view?.hideLoading()
                    view?.showMovieList(listMovie)
                } else {
                    view?.showError("Data Tidak Dapat Dimuat " + response.code().toString())
                    view?.hideLoading()
                }
            }
            override fun onFailure(call: Call<MovieListData>, t: Throwable) {
                view?.showError(t.toString())
                view?.hideLoading()
            }
        })
    }

    fun onDestroy() {
        view = null
    }
}