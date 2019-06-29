package com.meretas.movielisto.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MovieData(
    val imgPoster: Int,
    val score: Double,
    val genre: String,
    val title: String,
    val describ: String
): Parcelable