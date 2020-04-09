package com.main.activity

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.size
import com.bumptech.glide.Glide
import com.google.android.material.tabs.TabLayout
import com.main.model.Film
import kotlinx.android.synthetic.main.activity_film_detail.*
import kotlinx.android.synthetic.main.activity_list_music.toolbar

class FilmDetailActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_film_detail)

        customToolbar()
        addCollapsing()

        getData()

    }

    private fun getData() {
        var intent = intent
        var product = intent.getSerializableExtra("FILM") as Film

        Glide.with(applicationContext).load(product.images).into(avatar)
        collapsing_film.title = product.name
        collapsing_film.setExpandedTitleTextAppearance(R.style.ExpandedAppBar)
        txtAd.text = product.actor
    }


    private fun customToolbar() {
        //set toolbar
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toolbar.setNavigationOnClickListener {
            finish()
        }
    }

    private fun addCollapsing() {
        collapsing_film.title = ""
        collapsing_film.setExpandedTitleTextAppearance(R.style.ExpandedAppBar) //set text size khi chua scoll
        // collapsing.setCollapsedTitleTextAppearance(R.style.CollapsedAppBar)
        collapsing_film.setCollapsedTitleTextColor(Color.WHITE) // scoll len mau trang.
        collapsing_film.setExpandedTitleColor(Color.WHITE)  // khi chua scoll
        collapsing_film.setExpandedTitleTextAppearance(R.style.TextAppearance_MyApp_Title_Expanded)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_film_detail, menu)
        return true
    }
}
