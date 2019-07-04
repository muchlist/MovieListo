package com.meretas.movielisto.movie


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.meretas.movielisto.R
import com.meretas.movielisto._detail.MovieDetailActivity
import com.meretas.movielisto.data.MovieListData
import com.meretas.movielisto.utils.DATA_INTENT_MAIN_DETAIL
import kotlinx.android.synthetic.main.fragment_movie.*
import kotlinx.android.synthetic.main.fragment_movie.view.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast


class MovieFragment : Fragment() {

    private lateinit var viewModel: MovieViewModel
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

        viewModel = ViewModelProviders.of(this).get(MovieViewModel::class.java)

        viewModel.isLoading.observe(this, Observer {
            if (it) {
                pb_movie_list.visibility = View.VISIBLE
            } else {
                pb_movie_list.visibility = View.GONE
            }
        })

        viewModel.isError.observe(this, Observer {
            if (!it.isNullOrEmpty()){
                activity?.toast(it)
            }
        })

        viewModel.movieData.observe(this, Observer {
            this.movieData.clear()
            this.movieData.addAll(it)
            movieAdapter.notifyDataSetChanged()
        })

        setRecyclerView(view)
        viewModel.getMovieData()
    }

    private fun setRecyclerView(view: View){
        view.rv_movie.layoutManager = LinearLayoutManager(activity)
        movieAdapter = MovieListAdapter(activity, movieData) {
            context?.startActivity<MovieDetailActivity>(
                DATA_INTENT_MAIN_DETAIL to it
            )
        }
        view.rv_movie.adapter = movieAdapter
        view.rv_movie.setHasFixedSize(true)
    }
}
