package com.meretas.movielisto.main

import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.ViewModelProviders
import androidx.viewpager.widget.ViewPager
import com.meretas.movielisto.R
import com.meretas.movielisto.database.MovieDatabase
import com.meretas.movielisto.services.Api
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*


class MainActivity : AppCompatActivity() {

    //View Model
    private lateinit var viewModel: MainViewModel
    private lateinit var viewModelFactory: MainViewModelFactory

    //JOB search cancleable
    val searchScope = CoroutineScope(Dispatchers.Main)
    private var textChangeCountJob: Job? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initPager()
        hideKeyboard()

        //INIT VIEW MODEL
        val application = requireNotNull(this).application
        val dataSource = MovieDatabase.getInstane(application).movieDatabaseDao
        val apiService = Api.retrofitService
        viewModelFactory = MainViewModelFactory(apiService, dataSource, application)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(MainViewModel::class.java)

        //TOMBOL MERUBAH LOCALE
        iv_dashboard_translate.setOnClickListener {
            val mIntent = Intent(Settings.ACTION_LOCALE_SETTINGS)
            startActivity(mIntent)
        }

        //Searchbar
        sv_dashboard.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                textChangeCountJob?.cancel()
                when (view_pager_main.currentItem) {
                    0 -> viewModel.searchMovie(query ?: "")
                    1 -> viewModel.searchTvData(query ?: "")
                }
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                textChangeCountJob?.cancel()
                if (newText!!.length > 2) {
                    textChangeCountJob = searchScope.launch {
                        delay(800L)
                        when (view_pager_main.currentItem) {
                            0 -> viewModel.searchMovie(newText)
                            1 -> viewModel.searchTvData(newText)
                        }
                    }
                }
                return false
            }

        })
    }

    private fun initPager() {
        view_pager_main.adapter = MainPagerAdapter(supportFragmentManager)
        view_pager_main.offscreenPageLimit = 2
        view_pager_main.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {
            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
            }

            override fun onPageSelected(position: Int) {
                sv_dashboard.setQuery("", false)
            }
        })

        main_tab.setupWithViewPager(view_pager_main)
        main_tab.getTabAt(0)?.setIcon(R.drawable.ic_movie)
        main_tab.getTabAt(1)?.setIcon(R.drawable.ic_baseline_tv_24px)
        main_tab.getTabAt(2)?.setIcon(R.drawable.ic_baseline_favorite_negative_24px)
    }

    private fun hideKeyboard() {
        sv_dashboard.isFocusable = false
        sv_dashboard.clearFocus()
    }

    override fun onDestroy() {
        textChangeCountJob?.cancel()
        super.onDestroy()
    }
}