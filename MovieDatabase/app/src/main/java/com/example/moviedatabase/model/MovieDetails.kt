package com.example.moviedatabase.model

import com.google.gson.annotations.SerializedName

data class MovieDetails(
    @SerializedName("runningTimeInMinutes") val runningTimeInMinutes: Int,
    @SerializedName("numberOfEpisodes") val numberOfEpisodes: Int,
    @SerializedName("seriesEndYear") val seriesEndYear: Int,
    @SerializedName("seriesStartYear") val seriesStartYear: Int,
    @SerializedName("title") val title: String,
    @SerializedName("titleType") val titleType: String
) {

    override fun toString(): String {
        return super.toString()
    }
}