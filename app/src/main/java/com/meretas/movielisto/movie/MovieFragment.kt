package com.meretas.movielisto.movie


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.meretas.movielisto.R
import com.meretas.movielisto._detail.DetailActivity
import com.meretas.movielisto.data.MovieData
import com.meretas.movielisto.utils.DATA_INTENT_MAIN_DETAIL
import kotlinx.android.synthetic.main.fragment_movie.view.*
import org.jetbrains.anko.startActivity


class MovieFragment : Fragment(), MovieView {

    private lateinit var presenter: MoviePresenter

    //RECYCLER VIEW
    private lateinit var movieAdapter: MovieListAdapter
    private var movieData: MutableList<MovieData> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_movie, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        presenter = MoviePresenter(this)

        //START RECYCLER VIEW
        view.rv_movie.layoutManager = LinearLayoutManager(activity)
        movieAdapter = MovieListAdapter(activity, movieData) {
            context?.startActivity<DetailActivity>(DATA_INTENT_MAIN_DETAIL to it)
        }
        view.rv_movie.adapter = movieAdapter
        //END RECYCLER VIEW

        //mengisi History Recyclerview
        presenter.getMovieData()
    }

    override fun showMovieList() {
        this.movieData.clear()

        //Aggap Movie Data dapat dari presenter
        this.movieData.apply {
            clear()
            add(
                MovieData(
                    R.drawable.poster_a_star,
                    7.5,
                    getString(R.string.genre_drama),
                    getString(R.string.a_star_is_born),
                    getString(R.string.star_detail)
                )
            )
            add(
                MovieData(
                    R.drawable.poster_aquaman,
                    6.8,
                    getString(R.string.genre_action),
                    getString(R.string.aquaman),
                    getString(R.string.aquaman_detail)
                )
            )
            add(
                MovieData(
                    R.drawable.poster_avengerinfinity,
                    8.3,
                    getString(R.string.genre_action),
                    getString(R.string.avenger),
                    getString(R.string.avenger_detail)
                )
            )
            add(
                MovieData(
                    R.drawable.poster_birdbox,
                    7.0,
                    getString(R.string.genre_thriller),
                    getString(R.string.birdbox),
                    getString(R.string.birdbox_detail)
                )
            )
            add(
                MovieData(
                    R.drawable.poster_bohemian,
                    8.1,
                    getString(R.string.genre_musical),
                    getString(R.string.bohemian),
                    getString(R.string.bohemian_detail)
                )
            )
            add(
                MovieData(
                    R.drawable.poster_bumblebee,
                    6.5,
                    getString(R.string.genre_action),
                    getString(R.string.bumblebee),
                    getString(R.string.bumblebee_detail)
                )
            )
            add(
                MovieData(
                    R.drawable.poster_creed,
                    7.3,
                    getString(R.string.genre_action),
                    getString(R.string.creed),
                    getString(R.string.creed_detail)
                )
            )
            add(
                MovieData(
                    R.drawable.poster_deadpool,
                    7.6,
                    getString(R.string.genre_action),
                    getString(R.string.deadpool),
                    getString(R.string.deadpool_detail)
                )
            )
            add(
                MovieData(
                    R.drawable.poster_dragon,
                    7.7,
                    getString(R.string.genre_family),
                    getString(R.string.dragon),
                    getString(R.string.dragon_detail)
                )
            )
            add(
                MovieData(
                    R.drawable.poster_mortal_engines,
                    6.0,
                    getString(R.string.genre_action),
                    getString(R.string.mortal_engine),
                    getString(R.string.mortal_engine_detail)
                )
            )
        }
        //this.movieData.addAll(movieData)
        movieAdapter.notifyDataSetChanged()
    }

    override fun onDestroy() {
        presenter.onDestroy()
        super.onDestroy()
    }

}
