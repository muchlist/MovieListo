package com.meretas.movielisto.data


import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
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
    @Entity(tableName = "movie_table")
    data class Result(
        @ColumnInfo(name="")
        @Json(name = "adult")
        val adult: Boolean,

        @ColumnInfo(name = "backdrop_path")
        @Json(name = "backdrop_path")
        val backdropPath: String,

        @ColumnInfo(name = "genre_ids")
        @Json(name = "genre_ids")
        val genreIds: List<Int>,

        @Json(name = "id")
        @PrimaryKey //(autoGenerate = true)
        val id: Int,

        @ColumnInfo(name = "original_language")
        @Json(name = "original_language")
        val originalLanguage: String,

        @ColumnInfo(name = "original_title")
        @Json(name = "original_title")
        val originalTitle: String,

        @ColumnInfo(name = "overview")
        @Json(name = "overview")
        val overview: String,

        @ColumnInfo(name = "popularity")
        @Json(name = "popularity")
        val popularity: Double,

        @ColumnInfo(name = "poster_path")
        @Json(name = "poster_path")
        val posterPath: String,

        @ColumnInfo(name = "release_date")
        @Json(name = "release_date")
        val releaseDate: String,

        @ColumnInfo(name = "title")
        @Json(name = "title")
        val title: String,

        @ColumnInfo(name = "video")
        @Json(name = "video")
        val video: Boolean,

        @ColumnInfo(name = "vote_average")
        @Json(name = "vote_average")
        val voteAverage: Double,

        @ColumnInfo(name = "vote_count")
        @Json(name = "vote_count")
        val voteCount: Int
    ): Parcelable
}