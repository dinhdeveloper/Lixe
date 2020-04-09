package com.main.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.main.activity.R
import com.main.model.Film
import com.main.model.Song

class FilmNewAdapter(
    private val context: Context?,
    private val productList: List<Film>
) : RecyclerView.Adapter<FilmNewAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var view =
            LayoutInflater.from(context).inflate(R.layout.custom_film_new, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return productList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (context != null) {
            Glide.with(context).load(productList[position].images)
                .into(holder.imgItemFilm!!)
            holder.txtNameFilm?.text = productList[position].name
        }
    }

    inner class ViewHolder(item: View) : RecyclerView.ViewHolder(item) {
        var imgItemFilm: ImageView? = itemView?.findViewById(R.id.imgItemFilm)
        var txtNameFilm: TextView? = itemView?.findViewById(R.id.txtNameFilm)
    }
}