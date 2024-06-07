package com.example.filmspot.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.filmspot.R
import com.example.filmspot.model.WatchItem

class WatchListAdapter(private val watchList: List<WatchItem>) : RecyclerView.Adapter<WatchListAdapter.WatchViewHolder>() {
    class WatchViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        // Similar al ReviewsAdapter, inicializa tus vistas aquí
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WatchViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_watch, parent, false)
        return WatchViewHolder(view)
    }

    override fun onBindViewHolder(holder: WatchViewHolder, position: Int) {
        // Bindea los datos aquí
    }

    override fun getItemCount() = watchList.size
}
