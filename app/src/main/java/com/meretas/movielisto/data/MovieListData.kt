package com.meretas.movielisto.data


import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

data class MovieListData(
    @Json(name = "page")
    val page: Int,
    @Json(name = "results")
    val results: List<Result>,
    @Json(name = "total_pages")
    val totalPages: Int,
    @Json(name = "total_results")
    val totalResults: Int
) { @Parcelize
    data class Result(
    @Json(name = "adult")
        val adult: Boolean,

    @Json(name = "backdrop_path")
        val backdropPath: String?,

    @Json(name = "genre_ids")
        val genreIds: List<Int?>,

    @Json(name = "id")
        val id: Int,

    @Json(name = "original_language")
        val originalLanguage: String,

    @Json(name = "original_title")
        val originalTitle: String,

    @Json(name = "overview")
        val overview: String,

    @Json(name = "popularity")
        val popularity: Double,

    @Json(name = "poster_path")
        val posterPath: String?,

    @Json(name = "release_date")
        val releaseDate: String,

    @Json(name = "title")
        val title: String,

    @Json(name = "video")
        val video: Boolean,

    @Json(name = "vote_average")
        val voteAverage: Double,

    @Json(name = "vote_count")
        val voteCount: Int
    ): Parcelable
}