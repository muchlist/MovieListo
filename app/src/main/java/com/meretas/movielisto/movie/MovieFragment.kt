package com.meretas.movielisto.movie


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.meretas.movielisto.R
import com.meretas.movielisto._detail.MovieDetailActivity
import com.meretas.movielisto.data.MovieListData
import com.meretas.movielisto.services.Api
import com.meretas.movielisto.services.ApiService
import com.meretas.movielisto.utils.DATA_INTENT_MAIN_DETAIL
import kotlinx.android.synthetic.main.fragment_movie.*
import kotlinx.android.synthetic.main.fragment_movie.view.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast


class MovieFragment : Fragment(), MovieView {

    private lateinit var presenter: MoviePresenter
    private lateinit var apiService: ApiService

    //RECYCLER VIEW
    private lateinit var movieAdapter: MovieListAdapter
    private var movieData: MutableList<MovieListData.Result> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_movie, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        apiService = Api.retrofitService
        presenter = MoviePresenter(this, apiService)

        //START RECYCLER VIEW
        view.rv_movie.layoutManager = LinearLayoutManager(activity)
        movieAdapter = MovieListAdapter(activity, movieData) {
            context?.startActivity<MovieDetailActivity>(
                DATA_INTENT_MAIN_DETAIL to it
            )
        }
        view.rv_movie.adapter = movieAdapter
        view.rv_movie.setHasFixedSize(true)
        //END RECYCLER VIEW

        //mengisi History Recyclerview
        presenter.getMovieData()
    }

    override fun showMovieList(movieList: List<MovieListData.Result>) {
        this.movieData.clear()
        this.movieData.addAll(movieList)
        movieAdapter.notifyDataSetChanged()
    }

    override fun showLoading() {
        pb_movie_list.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        pb_movie_list.visibility = View.GONE
    }

    override fun showError(error: String) {
        activity?.toast(error)
    }

    override fun onDestroy() {
        presenter.onDestroy()
        super.onDestroy()
    }

}
