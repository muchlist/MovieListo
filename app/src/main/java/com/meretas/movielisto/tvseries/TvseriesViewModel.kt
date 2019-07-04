package com.meretas.movielisto.tvseries

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.meretas.movielisto.data.TvListData
import com.meretas.movielisto.services.Api
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TvseriesViewModel : ViewModel() {

    private val _tvSeriesData: MutableLiveData<List<TvListData.Result>> = MutableLiveData()
    val tvSeriesData : LiveData<List<TvListData.Result>>
        get() = _tvSeriesData

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

    fun getTVData() {
        _isError.value = ""
        _isLoading.value = true
        Api.retrofitService.getTv().enqueue(object : Callback<TvListData> {
            override fun onResponse(call: Call<TvListData>, response: Response<TvListData>) {
                if (response.isSuccessful) {
                    //menggunakan !! karena sudah pasti ada datanya
                    val listTv = response.body()?.results
                    _tvSeriesData.postValue(listTv)
                    _isLoading.value = false
                } else {
                    _isError.value = "Terjadi Kesalahan"
                    _isLoading.value = false
                }
            }
            override fun onFailure(call: Call<TvListData>, t: Throwable) {
                //_isError.value = "Tidak dapat terhubung"
                _isLoading.value = false
            }
        })
    }
}