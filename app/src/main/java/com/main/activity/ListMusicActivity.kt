package com.main.activity

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.dinh.service.APIService
import com.dinh.service.APIUntil
import com.main.adapter.ListMusicAdapter
import com.main.model.Song
import kotlinx.android.synthetic.main.activity_list_music.*
import retrofit2.Call
import retrofit2.Response

class ListMusicActivity : AppCompatActivity() {

    private lateinit var mApiService: APIService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_music)

        mApiService = APIUntil.server

        customToolbar()
        addCollapsing()
        addDataRecycleView()
    }

    private fun addDataRecycleView() {
        var productList: List<Song> = ArrayList()
        var adapterMusic: ListMusicAdapter
        rc_listMusic.layoutManager = LinearLayoutManager(this)
        mApiService!!.getAllSong().enqueue(object : retrofit2.Callback<List<Song>> {
            override fun onFailure(call: Call<List<Song>>, t: Throwable) {
                Log.e("onFailure", t.message)
            }

            override fun onResponse(call: Call<List<Song>>, response: Response<List<Song>>) {
                productList = response.body() as ArrayList<Song>
                adapterMusic =
                    ListMusicAdapter(this@ListMusicActivity, productList) { productItem ->
                        collapsing.title = productItem.name
                    }
                rc_listMusic.adapter = adapterMusic
                rc_listMusic.setHasFixedSize(true)
                adapterMusic.notifyDataSetChanged()

                shimmer_view.stopShimmer()
                shimmer_view.visibility = View.GONE
            }

        })
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
        collapsing.title = "Hết thương cạn nhớ"
        collapsing.setExpandedTitleTextAppearance(R.style.ExpandedAppBar) //set text size khi chua scoll
        // collapsing.setCollapsedTitleTextAppearance(R.style.CollapsedAppBar)
        collapsing.setCollapsedTitleTextColor(Color.WHITE) // scoll len mau trang.
        collapsing.setExpandedTitleColor(Color.WHITE)  // khi chua scoll

        //background Collapsing
        // background.setImageResource(R.drawable.bontram)

        floating.setOnClickListener {
            Toast.makeText(this, "Phát tất cả", Toast.LENGTH_LONG).show()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_toolbar, menu)
        return true
    }

    private fun setIconFavorite() {

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.icon_favorite -> {
                item.icon = resources.getDrawable(R.drawable.ic_favorite_white_24dp)
            }
            R.id.icon_more -> {
                Toast.makeText(this, "More", Toast.LENGTH_LONG).show()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onResume() {
        super.onResume()
        shimmer_view.startShimmer()
    }

    override fun onPause() {
        shimmer_view.stopShimmer()
        super.onPause()
    }
}
