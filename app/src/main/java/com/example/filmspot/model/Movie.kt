package com.example.filmspot.model

import java.util.Date

data class Movie(
    val id: String,
    val title: String,
    val description: String,
    val releaseDate: Date,
    val rating: Double,
    val imageUrl: String
)
