package com.example.filmspot.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.appcompat.app.AppCompatActivity
import com.example.filmspot.network.TmdbService
import com.example.filmspot.model.MovieResponse
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.filmspot.R
import com.example.filmspot.databinding.FragmentExploreBinding
import com.example.filmspot.network.RetrofitClient
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory

class ExploreFragment : Fragment() {

    private var _binding: FragmentExploreBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentExploreBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.searchBox.setOnEditorActionListener { textView, i, keyEvent ->
            if (textView.text.isNotEmpty()) {
                searchMovies(textView.text.toString())
                true
            } else false
        }
    }

    private fun searchMovies(query: String) {
        RetrofitClient.tmdbApi.searchMovies("tu_api_key_aquí", query).enqueue(object : retrofit2.Callback<MovieResponse> {
            override fun onResponse(call: retrofit2.Call<MovieResponse>, response: retrofit2.Response<MovieResponse>) {
                if (response.isSuccessful) {
                    // Actualizar RecyclerView con los resultados
                }
            }

            override fun onFailure(call: retrofit2.Call<MovieResponse>, t: Throwable) {
                // Manejar error
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}