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
import com.example.filmspot.databinding.FragmentSignInBinding
import com.google.firebase.auth.FirebaseAuth

class SignInFragment : Fragment() {

    private var _binding: FragmentSignInBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSignInBinding.inflate(inflater, container, false)
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
            findNavController().navigate(R.id.action_SignInFragment_to_SignUpFragment)
        }
        binding.btSignin.setOnClickListener {
            login()
        }
        binding.btRecoverypassword.setOnClickListener {
            resetpassword()
        }
        requireActivity().onBackPressedDispatcher.addCallback(
            viewLifecycleOwner,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    findNavController().navigate(R.id.action_SignInFragment_to_InitFragment)
                }
            }
        )
    }

    private fun login() { // Método de inicio de sesión
        if (binding.textInputEmail.text!!.isNotEmpty() && binding.textInputPassword.text!!.isNotEmpty()) {
            FirebaseAuth.getInstance().signInWithEmailAndPassword(
                binding.textInputEmail.text.toString(),
                binding.textInputPassword.text.toString()
            ).addOnCompleteListener {
                if (it.isSuccessful) {
                    Toast.makeText(
                        requireContext(),
                        "Successful authentication",
                        Toast.LENGTH_LONG,
                    ).show()
                    // Agregar siguiente vista
                } else {
                    Toast.makeText(
                        requireContext(),
                        "Authentication failed.",
                        Toast.LENGTH_LONG,
                    ).show()
                }
            }
        }
    }

    private fun resetpassword() { // Método para actualizar la contraseña
        if (binding.textInputEmail.text!!.isNotEmpty()) {
            FirebaseAuth.getInstance()
                .sendPasswordResetEmail(binding.textInputEmail.text.toString())
                .addOnCompleteListener {
                    if (it.isSuccessful) {
                        Toast.makeText(
                            requireContext(),
                            "Reset password email send.",
                            Toast.LENGTH_LONG,
                        ).show()
                    } else {
                        Toast.makeText(
                            requireContext(),
                            "Incorrect email.",
                            Toast.LENGTH_LONG,
                        ).show()
                    }
                }
        }
    }
}