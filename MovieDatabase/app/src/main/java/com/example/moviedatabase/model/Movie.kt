package com.example.moviedatabase.model

import com.google.gson.annotations.SerializedName


data class Movie(
    @SerializedName("id") var id:String,
    @SerializedName("l") var name:String,
    @SerializedName("q") var type:String
) {

    override fun toString(): String {
        return name
    }
}