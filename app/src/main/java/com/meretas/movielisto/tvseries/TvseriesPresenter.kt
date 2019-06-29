package com.meretas.movielisto.tvseries

class TvseriesPresenter(private var view: TvseriesView?) {

    fun getTVData() {
        view?.showTVList()
    }

    fun onDestroy() {
        view = null
    }
}