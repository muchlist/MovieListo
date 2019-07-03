package com.meretas.movielisto.tvseries


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.meretas.movielisto.R
import com.meretas.movielisto._detail.TvDetailActivity
import com.meretas.movielisto.data.TvListData
import com.meretas.movielisto.services.Api
import com.meretas.movielisto.services.ApiService
import com.meretas.movielisto.utils.DATA_INTENT_MAIN_DETAIL
import kotlinx.android.synthetic.main.fragment_tvseries.*
import kotlinx.android.synthetic.main.fragment_tvseries.view.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast


class TvseriesFragment : Fragment(), TvseriesView {

    private lateinit var presenter: TvseriesPresenter
    private lateinit var apiService: ApiService

    //Recycler View item
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

        apiService = Api.retrofitService
        presenter = TvseriesPresenter(this, apiService)

        //START RECYCLER VIEW
        view.rv_tvseries.layoutManager = LinearLayoutManager(activity)
        tvAdapter = TvseriesListAdapter(activity, tvSeriesData) {
            context?.startActivity<TvDetailActivity>(
                DATA_INTENT_MAIN_DETAIL to it
            )
        }
        view.rv_tvseries.adapter = tvAdapter
        view.rv_tvseries.setHasFixedSize(true)
        //END RECYCLER VIEW

        //mengisi History Recyclerview
        presenter.getTVData()
    }

    override fun showTVList(tvList: List<TvListData.Result>) {
        this.tvSeriesData.clear()
        this.tvSeriesData.addAll(tvList)
        tvAdapter.notifyDataSetChanged()
    }

    override fun showLoading() {
        pb_tv_series.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        pb_tv_series.visibility = View.GONE
    }

    override fun showError(error: String) {
        activity?.toast(error)
    }

    override fun onDestroy() {
        presenter.onDestroy()
        super.onDestroy()
    }

}
