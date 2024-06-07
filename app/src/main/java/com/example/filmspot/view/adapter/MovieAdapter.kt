package com.example.filmspot.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.filmspot.R
import com.example.filmspot.model.Movie

class MoviesAdapter(private var movies: List<Movie>) : RecyclerView.Adapter<MoviesAdapter.MovieViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_movie_review, parent, false)
        return MovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = movies[position]
        holder.bind(movie)
    }

    override fun getItemCount(): Int = movies.size

    class MovieViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val moviePoster: ImageView = view.findViewById(R.id.moviePoster)
        private val ratingBar: RatingBar = view.findViewById(R.id.movieRatingBar)

        fun bind(movie: Movie) {
            Glide.with(itemView.context)
                .load("https://image.tmdb.org/t/p/w500${movie.posterPath}")
                .into(moviePoster)

            ratingBar.rating = movie.userRating.toFloat() / 2  // Assuming 'userRating' is out of 10
        }
    }
}

class MovieAdapter {

}
