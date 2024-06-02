package com.example.filmspot.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.filmspot.R
import com.example.filmspot.databinding.FragmentStartBinding

class StartFragment : Fragment() { // Fragmento inicial

    private var _binding: FragmentStartBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView( // Operaciones sobre la vista durante su creación
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentStartBinding.inflate(inflater, container, false)
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
        binding.imgbtContinue.setOnClickListener() {
            startLogin()
        }
    }

    private fun startLogin() { // Función para ir del start login al init login
        findNavController().navigate(R.id.action_StartFragment_to_InitFragment)
    }

}