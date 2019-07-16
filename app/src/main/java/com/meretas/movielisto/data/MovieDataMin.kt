package com.meretas.movielisto.data

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "movie_table")
data class MovieDataMin(

    @ColumnInfo(name = "backdrop_path")
    val backdropPath: String,

    @ColumnInfo(name = "genre_ids")
    val genreIds: String,

    @PrimaryKey(autoGenerate = false)
    val id: Int,

    @ColumnInfo(name = "poster_path")
    val posterPath: String,

    @ColumnInfo(name = "release_date")
    val releaseDate: String,

    @ColumnInfo(name = "title")
    val title: String,

    @ColumnInfo(name = "overview")
    val overview: String,

    @ColumnInfo(name = "vote_average")
    val voteAverage: Double,

    @ColumnInfo(name = "is_tv_show")
    val isTvShow: Boolean

): Parcelable