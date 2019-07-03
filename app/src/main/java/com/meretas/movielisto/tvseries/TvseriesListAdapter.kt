package com.meretas.movielisto.tvseries

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.meretas.movielisto.R
import com.meretas.movielisto.data.TvListData
import com.meretas.movielisto.services.BASE_IMG_URL
import com.meretas.movielisto.utils.GenreUtil
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_list_view.view.*

class TvseriesListAdapter(
    private val context: Context?,
    private val itemHistory: List<TvListData.Result>,
    private val itemClick: (TvListData.Result) -> Unit
) : RecyclerView.Adapter<TvseriesListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context)
            .inflate(R.layout.item_list_view, parent, false)
        return ViewHolder(view, itemClick)
    }

    override fun getItemCount(): Int = itemHistory.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(itemHistory[position])
    }


    class ViewHolder(view: View, val itemClick: (TvListData.Result) -> Unit) : RecyclerView.ViewHolder(view) {
        fun bindItem(items: TvListData.Result) {

            val posterPath = BASE_IMG_URL + items.posterPath
            val genre = GenreUtil().convertGenre(items.genreIds[0])
            val genreScore = genre+ " ★ ${items.voteAverage}"

            itemView.tv_title.text = items.name
            itemView.tv_sub_title.text = items.overview
            itemView.tv_genre.text = genreScore
            Picasso.get()
                .load(posterPath)
                .centerCrop()
                .fit()
                .into(itemView.img_poster)

            //onClick
            itemView.setOnClickListener { itemClick(items) }
        }

    }
}