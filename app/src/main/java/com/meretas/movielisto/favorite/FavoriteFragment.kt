package com.meretas.movielisto.favorite


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.meretas.movielisto.R
import com.meretas.movielisto.detail.MovieDetailActivity
import com.meretas.movielisto.data.MovieDataMin
import com.meretas.movielisto.database.MovieDatabase
import com.meretas.movielisto.utils.DATA_INTENT_MAIN_DETAIL_FROM_FAVORITE
import com.meretas.movielisto.utils.FROM_FAVORITE
import com.meretas.movielisto.utils.SOURCE_INTENT
import kotlinx.android.synthetic.main.fragment_favorite.view.*
import org.jetbrains.anko.startActivity

class FavoriteFragment : Fragment() {

    private lateinit var viewModel: FavoriteViewModel
    private lateinit var viewModelFactory: FavoriteViewModelFactory
    private lateinit var movieListAdapter: FavoriteListAdapter
    private var movieData: MutableList<MovieDataMin> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_favorite, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val application = requireNotNull(activity).application
        val dataSource = MovieDatabase.getInstane(application).movieDatabaseDao
        viewModelFactory = FavoriteViewModelFactory(dataSource, application)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(FavoriteViewModel::class.java)

        setRecyclerView(view)

        viewModel.movieData.observe(this, Observer {
            this.movieData.clear()
            this.movieData.addAll(it)
            movieListAdapter.notifyDataSetChanged()
        })

        viewModel.getMovieData()
    }

    private fun setRecyclerView(view: View) {
        view.rv_favorite.layoutManager = LinearLayoutManager(activity)
        movieListAdapter = FavoriteListAdapter(activity, movieData) {
            context?.startActivity<MovieDetailActivity>(
                SOURCE_INTENT to FROM_FAVORITE,
                DATA_INTENT_MAIN_DETAIL_FROM_FAVORITE to it
            )
        }
        view.rv_favorite.adapter = movieListAdapter


    }

    override fun onResume() {
        super.onResume()
        viewModel.getMovieData()
    }


}
