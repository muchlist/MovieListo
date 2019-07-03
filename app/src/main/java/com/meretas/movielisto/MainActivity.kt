package com.meretas.movielisto

import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.transaction
import com.meretas.movielisto.movie.MovieFragment
import com.meretas.movielisto.tvseries.TvseriesFragment
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        iv_dashboard_translate.setOnClickListener {
            val mIntent = Intent(Settings.ACTION_LOCALE_SETTINGS)
            startActivity(mIntent)
        }

        nav_view.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_home -> {
                    loadLastFragment(savedInstanceState)
                }
                R.id.navigation_dashboard -> {
                    loadNextFragment(savedInstanceState)
                }
            }
            true
        }
        nav_view.selectedItemId = R.id.navigation_home

        sv_dashboard.isFocusable = false
        sv_dashboard.clearFocus()
    }


    private fun loadLastFragment(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            supportFragmentManager.transaction {
                replace(R.id.fragment_container, MovieFragment(), MovieFragment::class.java.simpleName)
            }
        }
    }

    private fun loadNextFragment(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            supportFragmentManager.transaction {
                replace(R.id.fragment_container, TvseriesFragment(), TvseriesFragment::class.java.simpleName)
            }
        }
    }
}