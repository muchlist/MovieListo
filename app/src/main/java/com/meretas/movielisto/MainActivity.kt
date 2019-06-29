package com.meretas.movielisto

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.meretas.movielisto.movie.MovieFragment
import com.meretas.movielisto.tvseries.TvseriesFragment
import kotlinx.android.synthetic.main.activity_main.*
import android.provider.Settings
import android.content.Intent



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
                    nav_view.setBackgroundColor(getResources().getColor(R.color.blue_grey_700))
                }
                R.id.navigation_dashboard -> {
                    loadNextFragment(savedInstanceState)
                    nav_view.setBackgroundColor(getResources().getColor(R.color.teal_800))
                }
            }
            true
        }
        nav_view.selectedItemId = R.id.navigation_home

        sv_dashboard.setFocusable(false)
        sv_dashboard.clearFocus()
    }


    private fun loadLastFragment(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .replace(
                    R.id.fragment_container,
                    MovieFragment(), MovieFragment::class.java.simpleName
                )
                .commit()
        }
    }

    private fun loadNextFragment(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .replace(
                    R.id.fragment_container,
                    TvseriesFragment(), TvseriesFragment::class.java.simpleName
                )
                .commit()
        }
    }
}