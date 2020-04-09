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

class RecentlyViewedAdapter(
    private val context: Context?,
    private val productList: List<Song>
) : RecyclerView.Adapter<RecentlyViewedAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var view =
            LayoutInflater.from(context).inflate(R.layout.custom_item_recently_viewed, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return productList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (context != null) {
            Glide.with(context).load(productList[position].images)
                .into(holder.imgViewed!!)
            holder?.txtNameActor?.text = productList[position].singer
        }
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var imgViewed: ImageView? = itemView?.findViewById(R.id.img_viewed)
        var txtNameActor: TextView? = itemView?.findViewById(R.id.txtNameActor)
    }
}