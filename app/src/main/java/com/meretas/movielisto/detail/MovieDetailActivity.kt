package com.meretas.movielisto.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.meretas.movielisto.R
import com.meretas.movielisto.data.MovieDataMin
import com.meretas.movielisto.data.MovieListData
import com.meretas.movielisto.data.TvListData
import com.meretas.movielisto.database.MovieDatabase
import com.meretas.movielisto.services.BASE_IMG_URL
import com.meretas.movielisto.utils.*
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail.*
import org.jetbrains.anko.toast

class MovieDetailActivity : AppCompatActivity() {

    private lateinit var viewModel: MovieDetailViewModel
    private lateinit var viewModelFactory: MovieDetailViewModelFactory
    private var isFavorited = false
    private var isTvShow = false

    private var id = 0
    private var title = ""
    private var description = ""
    private var genre = ""
    private var date = ""
    private var score = 0.0
    private var poster = ""
    private var backdrop = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        //CEK FROM FAVORITE OR NOT
        when (intent.getStringExtra(SOURCE_INTENT)) {
            FROM_MOVIE -> initiateFromMovie()
            FROM_TV -> initiateFromTv()
            FROM_FAVORITE -> initiateFromFavorite()
        }

        tv_detail_title.text = title
        tv_detail_description.text = description
        tv_detail_genre.text = genre
        tv_detail_score.text = score.toString()
        tv_detail_release_date.text = date

        Picasso.get()
            .load(backdrop)
            .centerCrop()
            .fit()
            .into(iv_detail_backdrop)

        Picasso.get()
            .load(poster)
            .centerCrop()
            .fit()
            .into(iv_detail_poster)


        //Pass Database dan context Ke viewModel
        val application = requireNotNull(this).application
        val dataSource = MovieDatabase.getInstane(application).movieDatabaseDao
        viewModelFactory = MovieDetailViewModelFactory(dataSource, application)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(MovieDetailViewModel::class.java)

        //Cek Status Favorite
        viewModel.getFavorite(id).observe(this, Observer {
            isFavorited = if (it == null) {
                iv_detail_favorite.setImageResource(R.drawable.ic_baseline_favorite_negative_24px)
                false
            } else {
                iv_detail_favorite.setImageResource(R.drawable.ic_baseline_favorite_24px)
                true
            }
        })

        //click listener untuk tombol favorite
        iv_detail_favorite.setOnClickListener {
            if (isFavorited) {
                viewModel.delete(id)
                toast(getString(R.string.unfavorite))
            } else {
                val movieDataMin = MovieDataMin(
                    backdrop,
                    genre,
                    id,
                    poster,
                    date,
                    title,
                    description,
                    score,
                    isTvShow
                )
                viewModel.insert(movieDataMin)
                toast(getString(R.string.favorited))
            }
        }
    }

    private fun initiateFromMovie() {
        val intent = intent.getParcelableExtra<MovieListData.Result>(DATA_INTENT_MAIN_DETAIL)
        id = intent.id
        title = intent.title
        description = intent.overview
        if (intent.genreIds.isNotEmpty()) {
            genre = GenreUtil().convertGenre(intent.genreIds[0])
        }
        date = intent.releaseDate
        score = intent.voteAverage
        poster = BASE_IMG_URL + intent.posterPath
        backdrop = BASE_IMG_URL + intent.backdropPath

        isTvShow = false
    }

    private fun initiateFromTv() {
        val intent = intent.getParcelableExtra<TvListData.Result>(DATA_INTENT_MAIN_DETAIL)
        id = intent.id
        title = intent.name
        description = intent.overview
        if (intent.genreIds.isNotEmpty()) {
            genre = GenreUtil().convertGenre(intent.genreIds[0])
        }
        date = intent.firstAirDate
        score = intent.voteAverage
        poster = BASE_IMG_URL + intent.posterPath
        backdrop = BASE_IMG_URL + intent.backdropPath

        isTvShow = true
    }

    private fun initiateFromFavorite() {
        val intent = intent.getParcelableExtra<MovieDataMin>(DATA_INTENT_MAIN_DETAIL_FROM_FAVORITE)
        id = intent.id
        title = intent.title
        description = intent.overview
        genre = intent.genreIds //sudah di convert
        date = intent.releaseDate
        score = intent.voteAverage
        poster = intent.posterPath //termasuk base url
        backdrop = intent.backdropPath //termasuk base url

        isTvShow = intent.isTvShow
    }


}
