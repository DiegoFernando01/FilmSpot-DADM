package com.example.filmspot.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.filmspot.R
import com.example.filmspot.databinding.FragmentInitBinding

class InitFragment : Fragment() {

    private var _binding: FragmentInitBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView( // Operaciones sobre la vista durante su creación
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_init, container, false)
    }

    override fun onViewCreated( // Operaciones sobre la vista después su creación
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)
        controllers()
    }

    private fun controllers() {
        binding.btSignin.setOnClickListener() {
            findNavController().navigate(R.id.action_InitFragment_to_SignInFragment)
        }
        binding.btSignup.setOnClickListener() {
            findNavController().navigate(R.id.action_InitFragment_to_SignUpFragment)
        }
    }

}