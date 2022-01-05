package com.example.moviedatabase.model

import com.google.gson.annotations.SerializedName

data class ResponseAutoComplete(
    @SerializedName("d") var movies: ArrayList<Movie>
) {
}