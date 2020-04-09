package com.main.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.main.activity.R
import com.smarteist.autoimageslider.SliderViewAdapter

class SlideHomeAdapter : SliderViewAdapter<SlideHomeAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup?): ViewHolder {
        val view: View = LayoutInflater.from(parent?.context)
            .inflate(R.layout.custom_slide_home, null)
        return ViewHolder(view)
    }

    override fun getCount(): Int {
        return 4
    }

    override fun onBindViewHolder(viewHolder: ViewHolder?, position: Int) {
        when (position) {
            0 -> Glide.with(viewHolder!!.itemView)
                .load("http://mobishops.herokuapp.com/images/imageBanner/tet5.jpg")
                .into(viewHolder.imageCate!!)
            1 -> Glide.with(viewHolder!!.itemView)
                .load("http://mobishops.herokuapp.com/images/imageBanner/tet1.jpg")
                .into(viewHolder.imageCate!!)
            2 -> Glide.with(viewHolder!!.itemView)
                .load("http://mobishops.herokuapp.com/images/imageBanner/tet2.jpg")
                .into(viewHolder.imageCate!!)
            3 -> Glide.with(viewHolder!!.itemView)
                .load("http://mobishops.herokuapp.com/images/imageBanner/tet3.jpg")
                .into(viewHolder.imageCate!!)
            4 -> Glide.with(viewHolder!!.itemView)
                .load("http://mobishops.herokuapp.com/images/imageBanner/tet4.jpg")
                .into(viewHolder.imageCate!!)
            else -> Glide.with(viewHolder!!.itemView)
                .load("http://mobishops.herokuapp.com/images/imageBanner/tet6.jpg")
                .into(viewHolder.imageCate!!)
        }
    }

    inner class ViewHolder(itemView: View) :
        SliderViewAdapter.ViewHolder(itemView) {
        var itemView: View = itemView
        var imageCate: ImageView? = itemView.findViewById(R.id.custom_img_slide)
    }
}