package com.example.filmspot.model

import java.util.Date
import com.google.gson.annotations.SerializedName

data class MovieResponse(
    val results: List<Movie>
)

data class Movie(
    val id: Int,
    val title: String,
    val description: String,
    @SerializedName("release_date") val releaseDate: String,
    val rating: Double,
    @SerializedName("poster_path") val posterPath: String
)
