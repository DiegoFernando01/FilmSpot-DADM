package com.example.filmspot.view.fragment

import android.os.Bundle
import android.util.Log
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

class SignUpFragment : Fragment() {

    private var _binding: FragmentSignUpBinding? = null
    private val binding get() = _binding!!

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

    private fun controllers() {
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
        if (binding.textInputEmail.text!!.isNotEmpty() && binding.textInputPassword.text!!.isNotEmpty()) {
            FirebaseAuth.getInstance().createUserWithEmailAndPassword(
                binding.textInputEmail.text.toString(),
                binding.textInputPassword.text.toString()
            ).addOnCompleteListener {
                if (it.isSuccessful) {
                    Log.d("Salidas", "createUserWithEmail:success")
                    Toast.makeText(
                        requireContext(),
                        "Usuario creado con éxito.",
                        Toast.LENGTH_SHORT,
                    ).show()
                } else {
                    Log.w("Error", "createUserWithEmail:failure")
                    Toast.makeText(
                        requireContext(),
                        "Registro de usuario fallido.",
                        Toast.LENGTH_SHORT,
                    ).show()
                }
            }
        }
    }
}