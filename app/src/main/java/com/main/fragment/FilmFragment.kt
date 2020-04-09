package com.main.fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.dinh.service.APIService
import com.dinh.service.APIUntil
import com.main.activity.FilmDetailActivity
import com.main.activity.LoginActivity
import com.main.activity.R
import com.main.adapter.FilmNewAdapter
import com.main.adapter.ListActorAdapter
import com.main.adapter.ListFilmAdapter
import com.main.adapter.RecentlyViewedAdapter
import com.main.model.Film
import com.main.model.Song
import kotlinx.android.synthetic.main.fragment_film.*
import retrofit2.Call
import retrofit2.Response
import java.util.*


class FilmFragment : Fragment() {

    private lateinit var apiService: APIService

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_film, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        apiService = APIUntil.server


        addUser()
        addViewed()
        addHotWeek()
        addRecommend()
        addActors()
        //addTrailers()
    }

    private fun addUser() {
        imgUser.setOnClickListener {
            var intent = Intent(context, LoginActivity::class.java)
            startActivity(intent)
        }
    }

    private fun addRecommend() {
        var productList: List<Film>
        var adapterFilm: FilmNewAdapter
        rc_list.layoutManager = GridLayoutManager(activity, 2, GridLayoutManager.VERTICAL, false)
        apiService.getAllFilm().enqueue(object : retrofit2.Callback<List<Film>> {
            override fun onFailure(call: Call<List<Film>>, t: Throwable) {
                Log.e("onFailure", t.message)
            }

            override fun onResponse(call: Call<List<Film>>, response: Response<List<Film>>) {
                productList = response.body() as List<Film>
                adapterFilm = FilmNewAdapter(context, productList)
                rc_list?.adapter = adapterFilm
                rc_list?.setHasFixedSize(true)
                adapterFilm.notifyDataSetChanged()
            }

        })
    }

    private fun addActors() {
        var productList: List<Song>
        var adapterFilm: ListActorAdapter
        rc_actor.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        apiService.getAllSong().enqueue(object : retrofit2.Callback<List<Song>> {
            override fun onFailure(call: Call<List<Song>>, t: Throwable) {
                Log.e("onFailure", t.message)
            }

            override fun onResponse(call: Call<List<Song>>, response: Response<List<Song>>) {
                productList = response.body() as List<Song>
                adapterFilm = ListActorAdapter(context, productList)
                rc_actor?.adapter = adapterFilm
                rc_actor?.setHasFixedSize(true)
                adapterFilm.notifyDataSetChanged()
            }

        })
    }

    private fun addViewed() {
        var productList: List<Song>
        var adapterFilm: RecentlyViewedAdapter
        rc_viewed.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        apiService.getAllSong().enqueue(object : retrofit2.Callback<List<Song>> {
            override fun onFailure(call: Call<List<Song>>, t: Throwable) {
                Log.e("onFailure", t.message)
            }

            override fun onResponse(call: Call<List<Song>>, response: Response<List<Song>>) {
                productList = response.body() as List<Song>
                adapterFilm = RecentlyViewedAdapter(context, productList)
                rc_viewed?.adapter = adapterFilm
                rc_viewed?.setHasFixedSize(true)
                adapterFilm.notifyDataSetChanged()
            }

        })
    }

    private fun addHotWeek() {
        var productList: List<Film>
        var adapterFilm: ListFilmAdapter
        rc_listFilm.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        apiService.getAllFilm().enqueue(object : retrofit2.Callback<List<Film>> {
            override fun onFailure(call: Call<List<Film>>, t: Throwable) {
                Log.e("onFailure", t.message)
            }

            override fun onResponse(call: Call<List<Film>>, response: Response<List<Film>>) {
                productList = response.body() as List<Film>
                adapterFilm = ListFilmAdapter(context, productList) { product ->
                    val intent = Intent(context, FilmDetailActivity::class.java)
                    intent.putExtra("FILM", product)
                    startActivity(intent)
                }
                rc_listFilm?.adapter = adapterFilm
                rc_listFilm?.setHasFixedSize(true)
                adapterFilm.notifyDataSetChanged()

                shimmer_view_product?.stopShimmer()
                shimmer_view_product?.visibility = View.GONE
            }

        })
    }

//    private fun addTrailers() {
//        var productList: List<Product>
//        var adapterFilm: TrailerAdapter
//        rc_listTrailer.layoutManager =
//            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
//        apiService.getProductById(1).enqueue(object : retrofit2.Callback<ArrayList<Product>> {
//            override fun onFailure(call: Call<ArrayList<Product>>, t: Throwable) {
//                Log.e("onFailure", t.message)
//            }
//
//            override fun onResponse(
//                call: Call<ArrayList<Product>>,
//                response: Response<ArrayList<Product>>
//            ) {
//                productList = response.body() as List<Product>
//                adapterFilm = TrailerAdapter(context, productList)
//                rc_listTrailer?.adapter = adapterFilm
//                rc_listTrailer.setHasFixedSize(true)
//                adapterFilm.notifyDataSetChanged()
//            }
//
//        })
//    }

    override fun onResume() {
        super.onResume()
        shimmer_view_product.startShimmer()
    }

    override fun onPause() {
        shimmer_view_product.stopShimmer()
        super.onPause()
    }
}
