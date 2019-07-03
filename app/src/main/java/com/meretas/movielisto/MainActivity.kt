package com.meretas.movielisto

import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.transaction
import com.meretas.movielisto.movie.MovieFragment
import com.meretas.movielisto.tvseries.TvseriesFragment
import com.meretas.movielisto.utils.STATE_MAIN_ACTIVITY
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        nav_view.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_home -> {
                    loadLastFragment()
                }
                R.id.navigation_dashboard -> {
                    loadNextFragment()
                }
            }
            true
        }

        if (savedInstanceState != null) {
            nav_view.selectedItemId = savedInstanceState.getInt(STATE_MAIN_ACTIVITY)
        } else {
            nav_view.selectedItemId = R.id.navigation_home
        }

        //TOMBOL MERUBAH LOCALE
        iv_dashboard_translate.setOnClickListener {
            val mIntent = Intent(Settings.ACTION_LOCALE_SETTINGS)
            startActivity(mIntent)
        }
        //MENGHILANGKAN KEYBOARD DI SEARCHVIEW
        sv_dashboard.isFocusable = false
        sv_dashboard.clearFocus()
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        val saveState = nav_view.selectedItemId
        outState?.putInt(STATE_MAIN_ACTIVITY, saveState)
    }


    private fun loadLastFragment() {
        supportFragmentManager.transaction {
            replace(R.id.fragment_container, MovieFragment(), MovieFragment::class.java.simpleName)
        }
    }

    private fun loadNextFragment() {
        supportFragmentManager.transaction {
            replace(R.id.fragment_container, TvseriesFragment(), TvseriesFragment::class.java.simpleName)
        }
    }

}