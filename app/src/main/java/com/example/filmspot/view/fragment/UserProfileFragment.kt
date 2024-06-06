package com.example.filmspot.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.firebase.firestore.FirebaseFirestore
import android.widget.TextView
import android.widget.ImageView
import com.bumptech.glide.Glide
import android.util.Log
import com.example.filmspot.R

class UserProfileFragment : Fragment() {
    private lateinit var db: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        db = FirebaseFirestore.getInstance() // Inicializa Firestore
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflar el layout para este fragmento
        return inflater.inflate(R.layout.fragment_user_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        db = FirebaseFirestore.getInstance()

        val userId = "id_del_usuario"
        db.collection("users").document(userId).get().addOnSuccessListener { documentSnapshot ->
            val usuario = documentSnapshot.toObject(Usuario::class.java)
            usuario?.let { user ->
                val userNameTextView: TextView = view.findViewById(R.id.userName)
                val movieNameTextView: TextView = view.findViewById(R.id.favoriteMovieNameYear)
                val movieImageView: ImageView = view.findViewById(R.id.favoriteMovieImage)
                val profileImageView: ImageView = view.findViewById(R.id.userProfileImage)

                userNameTextView.text = user.nombre
                movieNameTextView.text = "${user.peliculaFavorita.titulo} (${user.peliculaFavorita.año})"
                Glide.with(this).load(user.peliculaFavorita.urlImagen).into(movieImageView)
                Glide.with(this).load(user.urlImagenPerfil).into(profileImageView)
            }
        }.addOnFailureListener { exception ->
            Log.e("Firestore", "Error al obtener datos", exception)
        }
    }
}
