package com.meretas.movielisto._detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.meretas.movielisto.R
import com.meretas.movielisto.data.MovieListData
import com.meretas.movielisto.services.BASE_IMG_URL
import com.meretas.movielisto.utils.DATA_INTENT_MAIN_DETAIL
import com.meretas.movielisto.utils.GenreUtil
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail.*

class MovieDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val intent = intent.getParcelableExtra<MovieListData.Result>(DATA_INTENT_MAIN_DETAIL)

        val genre = GenreUtil().convertGenre(intent.genreIds[0])
        val backdropUrl = BASE_IMG_URL + intent.backdropPath
        val posterUrl = BASE_IMG_URL + intent.posterPath

        tv_detail_title.text = intent.title
        tv_detail_description.text = intent.overview
        tv_detail_genre.text = genre
        tv_detail_score.text = intent.voteAverage.toString()
        tv_detail_release_date.text = intent.releaseDate

        Picasso.get()
            .load(backdropUrl)
            .centerCrop()
            .fit()
            .into(iv_detail_backdrop)

        Picasso.get()
            .load(posterUrl)
            .centerCrop()
            .fit()
            .into(iv_detail_poster)

    }
}
