package com.main.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.main.activity.R
import com.main.model.Song

class TrailerAdapter(
    private val context: Context?,
    private val productList: List<Song>
): RecyclerView.Adapter<TrailerAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrailerAdapter.ViewHolder {
        var view =
            LayoutInflater.from(context).inflate(R.layout.custom_trailers, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return productList.size
    }

    override fun onBindViewHolder(holder: TrailerAdapter.ViewHolder, position: Int) {
        if (context != null) {
            Glide.with(context).load(productList[position].images).into(holder.imgItemFilm!!)
        }
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var imgItemFilm: ImageView? = itemView?.findViewById(R.id.imgItemFilm)
    }
}