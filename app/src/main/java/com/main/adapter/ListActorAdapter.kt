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
import com.main.model.Song

class ListActorAdapter(
    private val context: Context?,
    private val productList: List<Song>
) : RecyclerView.Adapter<ListActorAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var view =
            LayoutInflater.from(context).inflate(R.layout.custom_actor, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return productList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (context != null) {
            Glide.with(context).load(productList[position].images)
                .into(holder.imgItem!!)
            holder.txtAd?.text = productList[position].name
            holder.txt?.text = productList[position].singer
        }
    }
    inner class ViewHolder(item: View) : RecyclerView.ViewHolder(item) {
        var imgItem: ImageView? = itemView?.findViewById(R.id.imgItem)
        var txtAd: TextView? = itemView?.findViewById(R.id.txtAd)
        var txt: TextView? = itemView?.findViewById(R.id.txt)
    }
}