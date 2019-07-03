package com.meretas.movielisto.movie

import com.meretas.movielisto.data.MovieListData

interface MovieView {
    fun showMovieList (movieList : List<MovieListData.Result>)
    fun showLoading()
    fun hideLoading()
    fun showError(error: String)
}