package com.example.filmspot.view.adapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.filmspot.R

class ReviewsAdapter(private val reviewsList: List<Review>) : RecyclerView.Adapter<ReviewsAdapter.ReviewViewHolder>() {
    class ReviewViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        // Aquí puedes inicializar tus vistas, por ejemplo, TextViews, ImageViews, etc.
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReviewViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_review, parent, false)
        return ReviewViewHolder(view)
    }

    override fun onBindViewHolder(holder: ReviewViewHolder, position: Int) {
        // Aquí bindeas los datos de `reviewsList` a las vistas del ViewHolder
    }

    override fun getItemCount() = reviewsList.size
}
