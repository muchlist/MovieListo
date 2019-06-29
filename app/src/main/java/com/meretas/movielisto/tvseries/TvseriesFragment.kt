package com.meretas.movielisto.tvseries


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
import kotlinx.android.synthetic.main.fragment_tvseries.view.*
import org.jetbrains.anko.startActivity


class TvseriesFragment : Fragment(), TvseriesView {

    private lateinit var presenter: TvseriesPresenter

    //Recycler View item
    private lateinit var tvAdapter: TvseriesListAdapter
    private var tvSeriesData: MutableList<MovieData> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_tvseries, container, false)

        presenter = TvseriesPresenter(this)

        //START RECYCLER VIEW
        rootView.rv_tvseries.layoutManager = LinearLayoutManager(activity)
        tvAdapter = TvseriesListAdapter(activity, tvSeriesData) {
            context!!.startActivity<DetailActivity>(DATA_INTENT_MAIN_DETAIL to it)
        }
        rootView.rv_tvseries.adapter = tvAdapter
        //END RECYCLER VIEW

        //mengisi History Recyclerview
        presenter.getTVData()

        return rootView
    }

    override fun showTVList() {
        this.tvSeriesData.clear()

        //Aggap Movie Data dapat dari presenter
        this.tvSeriesData.apply {
            clear()
            add(
                MovieData(
                    R.drawable.tv_arrow,
                    5.8,
                    getString(R.string.genre_action),
                    getString(R.string.arrow),
                    getString(R.string.arrow_detail)
                )
            )
            add(
                MovieData(
                    R.drawable.tv_doom_patrol,
                    6.1,
                    getString(R.string.genre_sci_fi),
                    getString(R.string.doom_patrol),
                    getString(R.string.doom_patrol_detail)
                )
            )
            add(
                MovieData(
                    R.drawable.tv_dragon_ball,
                    7.1,
                    getString(R.string.genre_anime_action),
                    getString(R.string.dragon_ball),
                    getString(R.string.dragon_ball_detail)
                )
            )
            add(
                MovieData(
                    R.drawable.tv_fairytail,
                    6.4,
                    getString(R.string.genre_anime_action),
                    getString(R.string.fairy_tail),
                    getString(R.string.fairy_tail_detail)
                )
            )
            add(
                MovieData(
                    R.drawable.tv_family_guy,
                    6.5,
                    getString(R.string.genre_comedy),
                    getString(R.string.family_guy),
                    getString(R.string.family_guy_detail)
                )
            )
            add(
                MovieData(
                    R.drawable.tv_god,
                    8.1,
                    getString(R.string.genre_drama_war),
                    getString(R.string.game_of_thrones),
                    getString(R.string.game_of_thrones_detail)
                )
            )
            add(
                MovieData(
                    R.drawable.tv_gotham,
                    6.9,
                    getString(R.string.genre_crime),
                    getString(R.string.gotham),
                    getString(R.string.gotham_detail)
                )
            )
            add(
                MovieData(
                    R.drawable.tv_grey_anatomy,
                    6.9,
                    getString(R.string.genre_drama),
                    getString(R.string.grey_anatomy),
                    getString(R.string.grey_anatomy_detail)
                )
            )
            add(
                MovieData(
                    R.drawable.tv_hanna,
                    6.4,
                    getString(R.string.genre_thriller),
                    getString(R.string.hanna),
                    getString(R.string.hanna_detail)
                )
            )
            add(
                MovieData(
                    R.drawable.tv_flash,
                    6.7,
                    getString(R.string.genre_action),
                    getString(R.string.the_flash),
                    getString(R.string.the_flash_detail)
                )
            )
        }
        //this.tvSeriesData.addAll(movieData)
        tvAdapter.notifyDataSetChanged()
    }

    override fun onDestroy() {
        presenter.onDestroy()
        super.onDestroy()
    }

}
