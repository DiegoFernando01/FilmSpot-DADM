package com.example.filmspot.viewmodel

import java.util.Date
import androidx.lifecycle.ViewModel
import com.example.filmspot.repository.MovieRepository
import androidx.lifecycle.LiveData
import com.example.filmspot.model.Movie


class MovieViewModel(private val repository: MovieRepository) : ViewModel() {
    val movies: LiveData<List<Movie>> = repository.getMovies()

    fun addMovie(movie: Movie) {
        repository.addMovie(movie)
    }

    // Métodos para actualizar y eliminar
}
