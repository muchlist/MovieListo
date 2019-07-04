package com.meretas.movielisto

import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        view_pager_main.adapter = MainPagerAdapter(supportFragmentManager)
        main_tab.setupWithViewPager(view_pager_main)
        main_tab.getTabAt(0)?.setIcon(R.drawable.ic_movie)
        main_tab.getTabAt(1)?.setIcon(R.drawable.ic_baseline_tv_24px)

        //TOMBOL MERUBAH LOCALE
        iv_dashboard_translate.setOnClickListener {
            val mIntent = Intent(Settings.ACTION_LOCALE_SETTINGS)
            startActivity(mIntent)
        }
        //MENGHILANGKAN KEYBOARD DI SEARCHVIEW
        sv_dashboard.isFocusable = false
        sv_dashboard.clearFocus()
    }

//    override fun onSaveInstanceState(outState: Bundle?) {
//        super.onSaveInstanceState(outState)
//        val saveState = nav_view.selectedItemId
//        outState?.putInt(STATE_MAIN_ACTIVITY, saveState)
//    }
}