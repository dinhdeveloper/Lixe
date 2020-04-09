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

class ListMusicAdapter(
    private val context: Context,
    private val productList: List<Song>,
    private val itemClick: (Song) -> Unit
) : RecyclerView.Adapter<ListMusicAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.custom_item_song, parent, false)
        return ViewHolder(view, itemClick)
    }

    override fun getItemCount(): Int {
        return productList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.txtSong?.text = productList[position].name
        holder.txtSingerSong?.text = productList[position].singer
        Glide.with(context).load(productList[position].images).into(holder.imgItemMusic!!)
    }

    inner class ViewHolder(itemView: View, private val itemClick: (Song) -> Unit) :
        RecyclerView.ViewHolder(itemView) {
        var txtSong: TextView? = itemView?.findViewById(R.id.txtSong)
        var txtSingerSong: TextView? = itemView?.findViewById(R.id.txtSingerSong)
        var imgItemMusic: ImageView? = itemView?.findViewById(R.id.imgItemMusic)

        init {
            itemView?.setOnClickListener {
                itemClick?.invoke(productList[adapterPosition])
            }
        }
    }
}