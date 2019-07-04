package com.meretas.movielisto

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.meretas.movielisto.movie.MovieFragment
import com.meretas.movielisto.tvseries.TvseriesFragment

class MainPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

    private val pages = listOf(
        MovieFragment(),
        TvseriesFragment()
    )

    override fun getItem(position: Int): Fragment {
        return pages[position]
    }

    override fun getCount(): Int {
        return pages.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when(position){
            0 -> "Movie"
            1 -> "TV"
            else -> "Favorite"
        }
    }


}