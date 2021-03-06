package com.main.model

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName
import java.io.Serializable


class Song :Serializable{
    @SerializedName("id")
    @Expose
    var id: Int? = null

    @SerializedName("images")
    @Expose
    var images: String? = null

    @SerializedName("singer")
    @Expose
    var singer: String? = null

    @SerializedName("name")
    @Expose
    var name: String? = null

}