// En el paquete com.example.filmspot.network
package com.example.filmspot.network

import com.example.filmspot.model.MovieResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface TmdbService {
    @GET("search/movie")
    fun searchMovies(
        @Query("api_key") apiKey: String,
        @Query("query") movieTitle: String
    ): Call<MovieResponse>
}
