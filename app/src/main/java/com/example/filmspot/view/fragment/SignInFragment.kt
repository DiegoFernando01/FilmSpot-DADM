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

    private fun controllers() {
        binding.btSignup.setOnClickListener {
            findNavController().navigate(R.id.action_SignInFragment_to_SignUpFragment)
        }
        binding.btSignin.setOnClickListener {
            login()
        }
        binding.btRecoverypassword.setOnClickListener {

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

    private fun login() {
        if (binding.textInputEmail.text!!.isNotEmpty() && binding.textInputPassword.text!!.isNotEmpty()) {
            FirebaseAuth.getInstance().signInWithEmailAndPassword(
                binding.textInputEmail.text.toString(),
                binding.textInputPassword.text.toString()
            ).addOnCompleteListener {
                if (it.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d("Salidas", "signInWithEmail:success")
                    Toast.makeText(
                        requireContext(),
                        "Successful authentication",
                        Toast.LENGTH_SHORT,
                    ).show()
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w("Salidas", "signInWithEmail:failure")
                    Toast.makeText(
                        requireContext(),
                        "Authentication failed.",
                        Toast.LENGTH_SHORT,
                    ).show()
                }
            }
        }
    }
}