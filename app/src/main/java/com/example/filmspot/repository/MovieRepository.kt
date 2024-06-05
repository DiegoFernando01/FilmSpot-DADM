package com.example.filmspot.repository

import java.util.Date
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.filmspot.model.Movie
import com.google.firebase.firestore.FirebaseFirestore

class MovieRepository(private val db: FirebaseFirestore) {
    private val moviesLiveData = MutableLiveData<List<Movie>>()

    fun getMovies(): LiveData<List<Movie>> {
        db.collection("movies").get().addOnSuccessListener { documents ->
            val moviesList = documents.map { document ->
                Movie(
                    id = document.id,
                    title = document.getString("title") ?: "",
                    description = document.getString("description") ?: "",
                    releaseDate = document.getDate("releaseDate") ?: Date(),
                    rating = document.getDouble("rating") ?: 0.0,
                    imageUrl = document.getString("imageUrl") ?: ""
                )
            }
            moviesLiveData.postValue(moviesList)
        }
        return moviesLiveData
    }

    fun addMovie(movie: Movie) {
        val movieMap = hashMapOf(
            "title" to movie.title,
            "description" to movie.description,
            "releaseDate" to movie.releaseDate,
            "rating" to movie.rating,
            "imageUrl" to movie.imageUrl
        )
        db.collection("movies").add(movieMap)
    }

    // Métodos para actualizar y eliminar
}
