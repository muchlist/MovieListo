package com.meretas.movielisto.tvseries


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
import com.meretas.movielisto.data.TvListData
import com.meretas.movielisto.main.MainViewModel
import com.meretas.movielisto.utils.DATA_INTENT_MAIN_DETAIL
import com.meretas.movielisto.utils.FROM_TV
import com.meretas.movielisto.utils.SOURCE_INTENT
import kotlinx.android.synthetic.main.fragment_tvseries.*
import kotlinx.android.synthetic.main.fragment_tvseries.view.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast


class TvseriesFragment : Fragment() {

    private lateinit var viewModel: MainViewModel
    private lateinit var tvAdapter: TvseriesListAdapter
    private var tvSeriesData: MutableList<TvListData.Result> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_tvseries, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProviders.of(activity!!).get(MainViewModel::class.java)

        viewModel.isTvLoading.observe(viewLifecycleOwner, Observer {
            if (it) {
                pb_tv_series.visibility = View.VISIBLE
            } else {
                pb_tv_series.visibility = View.GONE
            }
        })

        viewModel.isTvError.observe(viewLifecycleOwner, Observer {
            if (!it.isNullOrEmpty()) {
                activity?.toast(it)
            }
        })

        viewModel.tvSeriesData.observe(viewLifecycleOwner, Observer {
            this.tvSeriesData.clear()
            this.tvSeriesData.addAll(it)
            tvAdapter.notifyDataSetChanged()
        })

        setRecyclerView(view)
        viewModel.getTVData()

    }

    private fun setRecyclerView(view: View) {
        view.rv_tvseries.layoutManager = LinearLayoutManager(activity)
        tvAdapter = TvseriesListAdapter(activity, tvSeriesData) {
            context?.startActivity<MovieDetailActivity>(
                SOURCE_INTENT to FROM_TV,
                DATA_INTENT_MAIN_DETAIL to it
            )
        }
        view.rv_tvseries.adapter = tvAdapter
        view.rv_tvseries.setHasFixedSize(true)
    }

}
