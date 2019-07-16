package com.meretas.movielisto.movie

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.meretas.movielisto.R
import com.meretas.movielisto.data.MovieListData
import com.meretas.movielisto.services.BASE_IMG_URL
import com.meretas.movielisto.utils.GenreUtil
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_list_view.view.*

class MovieListAdapter(
    private val context: Context?,
    private val itemHistory: List<MovieListData.Result>,
    private val itemClick: (MovieListData.Result) -> Unit
) : RecyclerView.Adapter<MovieListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context)
            .inflate(R.layout.item_list_view, parent, false)
        return ViewHolder(view, itemClick)
    }

    override fun getItemCount(): Int = itemHistory.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(itemHistory[position])
    }


    class ViewHolder(view: View, val itemClick: (MovieListData.Result) -> Unit) : RecyclerView.ViewHolder(view) {
        fun bindItem(items: MovieListData.Result) {

            val posterPath = BASE_IMG_URL + items.posterPath
            var genre = ""
            if (items.genreIds.isNotEmpty()) {
                genre = GenreUtil().convertGenre(items.genreIds[0])
            }
            val genreScore = genre + " ★ ${items.voteAverage}"

            itemView.tv_title.text = items.title
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