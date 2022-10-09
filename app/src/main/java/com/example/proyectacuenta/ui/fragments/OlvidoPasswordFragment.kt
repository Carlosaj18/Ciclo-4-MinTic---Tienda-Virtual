package com.example.proyectacuenta.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.proyectacuenta.R
import com.example.proyectacuenta.databinding.FragmentOlvidoPasswordBinding

class OlvidoPasswordFragment : Fragment() {

    private var _binding: FragmentOlvidoPasswordBinding? = null

    // El bindin sirve para ...
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment to return a view
        _binding = FragmentOlvidoPasswordBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        binding.confirmarEnvio.setOnClickListener {
            findNavController().navigate(R.id.action_olvidoPasswordFragment_to_loginAppFragment)

        }
    }
}
