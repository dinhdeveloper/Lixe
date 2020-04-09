package com.main.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.main.activity.R
import com.main.model.Song
import java.util.ArrayList

class CoverFlowAdapter(
    private val context: Context?, private val data: ArrayList<Song>?
) : BaseAdapter() {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View? {
        var convertView = convertView
        val viewHolder: ViewHolder
        if (convertView == null) {
            val inflater =
                context?.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            convertView = inflater.inflate(R.layout.custom_trending, null, false)
            viewHolder = ViewHolder(convertView)
        } else {
            viewHolder = convertView.tag as ViewHolder
        }
        if (context != null) {
            Glide.with(context!!).load(data?.get(position)?.images)
                .into(viewHolder.image!!)
        }
        viewHolder.name?.text = data?.get(position)?.name
        return convertView
    }

    override fun getItem(position: Int): Any {
        return data!![position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return data!!.size
    }

    inner class ViewHolder(item: View) {
        var image: ImageView? = item.findViewById(R.id.image)
        var name: TextView? = item.findViewById(R.id.name)
    }

}