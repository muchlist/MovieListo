package com.meretas.movielisto._detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.meretas.movielisto.R
import com.meretas.movielisto.data.MovieData
import com.meretas.movielisto.utils.DATA_INTENT_MAIN_DETAIL
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val intent = intent.getParcelableExtra<MovieData>(DATA_INTENT_MAIN_DETAIL)
        tv_detail_title.text = intent.title
        tv_detail_description.text = intent.describ
        tv_detail_genre.text = intent.genre
        tv_detail_score.text = intent.score.toString()


        Picasso.get()
            .load(intent.imgPoster)
            .centerCrop()
            .fit()
            .into(iv_detail_backdrop)

        Picasso.get()
            .load(intent.imgPoster)
            .centerCrop()
            .fit()
            .into(iv_detail_poster)

    }
}
