package com.meretas.movielisto.favorite

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.meretas.movielisto.R
import com.meretas.movielisto.data.MovieDataMin
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_list_view.view.*

class FavoriteListAdapter(
    private val context: Context?,
    private val itemHistory: List<MovieDataMin>,
    private val itemClick: (MovieDataMin) -> Unit
) : RecyclerView.Adapter<FavoriteListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context)
            .inflate(R.layout.item_list_view, parent, false)
        return ViewHolder(view, itemClick)
    }

    override fun getItemCount(): Int = itemHistory.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(itemHistory[position])
    }


    class ViewHolder(view: View, val itemClick: (MovieDataMin) -> Unit) : RecyclerView.ViewHolder(view) {
        fun bindItem(items: MovieDataMin) {

            val genreScore = items.genreIds + " ★ ${items.voteAverage}"
            val typeFilm = if (items.isTvShow) " Tv Series " else " Movie "

            itemView.tv_title.text = items.title
            itemView.tv_sub_title.text = items.overview
            itemView.tv_genre.text = genreScore
            itemView.tv_type_film.visibility = View.VISIBLE
            itemView.tv_type_film.text = typeFilm

            Picasso.get()
                .load(items.posterPath)
                .centerCrop()
                .fit()
                .into(itemView.img_poster)

            itemView.setOnClickListener { itemClick(items) }
        }


    }
}