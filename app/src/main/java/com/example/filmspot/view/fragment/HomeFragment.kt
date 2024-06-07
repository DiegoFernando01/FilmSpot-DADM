package com.example.filmspot.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.filmspot.databinding.FragmentHomeBinding
import com.example.filmspot.view.adapter.ReviewsAdapter
import com.example.filmspot.view.adapter.WatchListAdapter
import androidx.navigation.fragment.findNavController
import com.example.filmspot.R
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private lateinit var reviewsAdapter: ReviewsAdapter
    private lateinit var watchListAdapter: WatchListAdapter

    // Asegúrate de implementar ReviewsAdapter y WatchListAdapter correctamente

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Inicializar los adapters
        reviewsAdapter = ReviewsAdapter(listOf(/* sample data */))
        watchListAdapter = WatchListAdapter(listOf(/* sample data */))

        controllers()
        setupRecyclerViews()
        setupListeners()
    }

    private fun controllers() {
        binding.moreActivityText.setOnClickListener() {
            findNavController().navigate(R.id.action_HomeFragment_to_ReviewFragment)
        }
        binding.DetailsText.setOnClickListener() {
            findNavController().navigate(R.id.action_HomeFragment_to_ExploreFragment)
        }
        binding.exploreButton.setOnClickListener {
            findNavController().navigate(R.id.action_HomeFragment_to_ExploreFragment)
        }
    }

    private fun setupRecyclerViews() {
        binding.reviewsRecyclerView.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = reviewsAdapter
        }

        binding.watchListRecyclerView.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = watchListAdapter
        }
    }

    private fun setupListeners() {
        binding.moreActivityText.setOnClickListener {
            // Handle the click event, possibly navigating to another fragment
        }
        binding.exploreButton.setOnClickListener {
            // Handle the click event
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    class UserProfileFragment : Fragment() {

        private lateinit var db: FirebaseFirestore

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            db = Firebase.firestore
        }

        override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
            super.onViewCreated(view, savedInstanceState)

            // Suponiendo que tienes un 'userId' disponible aquí
            //val userId = "someUserId"

//            db.collection("users").document(userId).get().addOnSuccessListener { document ->
//                if (document != null) {
//                    val usuario = document.toObject(Usuario::class.java)  // Asegúrate de tener una clase 'Usuario' que coincida con la estructura en Firestore
//                    updateUI(usuario)
//                } else {
//                    Log.d("Firestore", "No such document")
//                }
//            }.addOnFailureListener { exception ->
//                Log.d("Firestore", "get failed with ", exception)
//            }
        }

//        private fun updateUI(usuario: Usuario?) {
//            // Verificar si el usuario no es null
//            usuario?.let {
//                binding.userName.text = it.nombre
//                binding.favoriteMovieNameYear.text =
//                    "${it.peliculaFavorita.titulo} (${it.peliculaFavorita.año})"
//                Glide.with(this).load(it.peliculaFavorita.urlImagen)
//                    .into(binding.favoriteMovieImage)
//                Glide.with(this).load(it.urlImagenPerfil).into(binding.userProfileImage)
//            }
//        }
    }

}
