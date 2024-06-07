package com.example.filmspot.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.filmspot.R
import com.example.filmspot.databinding.FragmentSignUpBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class SignUpFragment : Fragment() {

    private var _binding: FragmentSignUpBinding? = null
    private val binding get() = _binding!!
    private val db = FirebaseFirestore.getInstance()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSignUpBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated( // Operaciones sobre la vista después su creación
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)
        controllers()
    }

    private fun controllers() { // Controlador de eventos y escuchas
        binding.btSignup.setOnClickListener {
            register()
        }
        binding.btSignin.setOnClickListener {
            findNavController().navigate(R.id.action_SignUpFragment_to_SignInFragment)
        }
        requireActivity().onBackPressedDispatcher.addCallback(
            viewLifecycleOwner,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    findNavController().navigate(R.id.action_SignUpFragment_to_InitFragment)
                }
            }
        )
    }

    private fun register() {
        // Método de registro con correo y contraseña
        if (binding.textInputEmail.text!!.isNotEmpty() && binding.textInputPassword.text!!.isNotEmpty()) {
            FirebaseAuth.getInstance().createUserWithEmailAndPassword(
                binding.textInputEmail.text.toString(),
                binding.textInputPassword.text.toString()
            ).addOnCompleteListener {
                if (it.isSuccessful) {
                    val user = FirebaseAuth.getInstance().currentUser
                    user?.let { firebaseUser ->
                        // Crear un mapa de datos del usuario
                        val userData = hashMapOf(
                            "email" to firebaseUser.email,
                            "uid" to firebaseUser.uid
                        )
                        // Guardar los datos del usuario en Firestore
                        db.collection("users").document(firebaseUser.uid).set(userData)
                            .addOnSuccessListener {
                                Toast.makeText(
                                    requireContext(),
                                    "User created and saved successfully.",
                                    Toast.LENGTH_LONG,
                                ).show()
                                // Agregar siguiente vista
                            }
                            .addOnFailureListener {
                                Toast.makeText(
                                    requireContext(),
                                    "User save failed.",
                                    Toast.LENGTH_LONG,
                                ).show()
                            }
                    }
                } else {
                    Toast.makeText(
                        requireContext(),
                        "User registration failed.",
                        Toast.LENGTH_LONG,
                    ).show()
                }
            }
        } else {
            Toast.makeText(
                requireContext(),
                "Email and password must not be empty.",
                Toast.LENGTH_LONG,
            ).show()
        }
    }
}