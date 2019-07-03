package com.meretas.movielisto.tvseries

import com.meretas.movielisto.data.TvListData
import com.meretas.movielisto.services.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TvseriesPresenter(private var view: TvseriesView?, private val api: ApiService) {

    fun getTVData() {
        view?.showLoading()
        api.getTv().enqueue(object : Callback<TvListData> {
            override fun onResponse(call: Call<TvListData>, response: Response<TvListData>) {
                if (response.isSuccessful) {
                    //menggunakan !! karena sudah pasti ada datanya
                    val listTv = response.body()!!.results
                    view?.hideLoading()
                    view?.showTVList(listTv)
                } else {
                    view?.showError("Data Tidak Dapat Dimuat " + response.code().toString())
                    view?.hideLoading()
                }
            }
            override fun onFailure(call: Call<TvListData>, t: Throwable) {
                view?.showError(t.toString())
                view?.hideLoading()
            }
        })
    }

    fun onDestroy() {
        view = null
    }
}