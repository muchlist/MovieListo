package com.meretas.movielisto.services

import com.meretas.movielisto.BuildConfig
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

private const val BASE_URL = "https://api.themoviedb.org/3/"
private const val API_KEY = BuildConfig.TMDB_API_KEY
const val BASE_IMG_URL = "https://image.tmdb.org/t/p/w500"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface ApiService {

    /*
    https://api.themoviedb.org/3/search/movie?api_key={API KEY}&language=en-US&query={MOVIE NAME}
    https://api.themoviedb.org/3/search/tv?api_key={API KEY}&language=en-US&query={TV SHOW NAME}
    */


    @GET("discover/movie")
    fun getMovie(
        @Query("api_key") apiKey: String = API_KEY,
        @Query("language") language: String = "en-US"
    ): Call<MovieListData>

    @GET("search/movie")
    fun searchMovie(
        @Query("query") search: String,
        @Query("api_key") apiKey: String = API_KEY,
        @Query("language") language: String = "en-US"
    ): Call<MovieListData>

    @GET("discover/tv")
    fun getTv(
        @Query("api_key") apiKey: String = API_KEY,
        @Query("language") language: String = "en-US"
    ): Call<TvListData>

    @GET("search/tv")
    fun searchTv(
        @Query("query") search: String,
        @Query("api_key") apiKey: String = API_KEY,
        @Query("language") language: String = "en-US"
    ): Call<TvListData>

}

object Api {
    val retrofitService: ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }
}