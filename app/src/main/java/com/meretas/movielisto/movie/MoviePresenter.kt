package com.meretas.movielisto.movie


class MoviePresenter(private var view: MovieView?) {

    fun getMovieData(){
        view?.showMovieList()
    }

    fun onDestroy() {
        view = null
    }
}