package com.meretas.movielisto.services

import com.meretas.movielisto.data.MovieListData
import com.meretas.movielisto.data.TvListData
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.*

//https://api.themoviedb.org/3/discover/movie?api_key={API KEY}&language=en-US
//https://api.themoviedb.org/3/discover/tv?api_key={API KEY}&language=en-US
//https://image.tmdb.org/t/p/w185/kSBXou5Ac7vEqKd97wotJumyJvU.jpg

private const val BASE_URL = "https://api.themoviedb.org/3/discover/"
private const val API_KEY = "c51926b7a80f450b3f8f0fe0175b9634"
const val BASE_IMG_URL = "https://image.tmdb.org/t/p/w500"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface ApiService {

    @GET("movie")
    fun getMovie(
        @Query("api_key") apiKey: String = API_KEY,
        @Query("language") language: String = "en-US"
    ): Call<MovieListData>

    @GET("tv")
    fun getTv(
        @Query("api_key") apiKey: String = API_KEY,
        @Query("language") language: String = "en-US"
    ): Call<TvListData>

}

object Api {
    val retrofitService: ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }
}