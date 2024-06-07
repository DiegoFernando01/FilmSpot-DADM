package com.example.filmspot.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.filmspot.databinding.FragmentReviewBinding

class ReviewFragment : Fragment() {
    private var _binding: FragmentReviewBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentReviewBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        fetchRatedMovies()
    }

    private fun setupRecyclerView() {
//        binding.watchListRecyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
//        binding.watchListRecyclerView.adapter = MoviesAdapter(emptyList())  // Use the correct class name
    }

    private fun fetchRatedMovies() {
//        RetrofitClient.tmdbApi.searchMovies("your_api_key_here", "rated")
//            .enqueue(object : Callback<List<Movie>> {  // Correct the generic type if needed
//                override fun onResponse(call: Call<List<Movie>>, response: Response<List<Movie>>) {
//                    if (response.isSuccessful) {
//                        binding.watchListRecyclerView.adapter = MoviesAdapter(response.body() ?: emptyList())  // Use the correct class name
//                    }
//                }
//
//                override fun onFailure(call: Call<List<Movie>>, t: Throwable) {
//                    Log.e("ReviewFragment", "Error fetching rated movies", t)
//                }
//            })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
