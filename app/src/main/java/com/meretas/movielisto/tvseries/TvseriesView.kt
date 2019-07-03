package com.meretas.movielisto.tvseries

import com.meretas.movielisto.data.TvListData

interface TvseriesView {
    fun showTVList (tvList : List<TvListData.Result>)
    fun showLoading()
    fun hideLoading()
    fun showError(error: String)
}