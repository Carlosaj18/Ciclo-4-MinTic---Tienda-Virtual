package com.example.proyectacuenta

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.proyectacuenta.databinding.FragmentLoginAppBinding
import com.example.proyectacuenta.databinding.FragmentSignupBinding



class SignupFragment : Fragment() {

    private var _binding: FragmentSignupBinding? = null

    // El bindin sirve para ...
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {
        // Inflate the layout for this fragment to return a view
        _binding = FragmentSignupBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onStart() {
        super.onStart()
        binding.registro.setOnClickListener {
            findNavController().navigate(R.id.action_signupFragment_to_loginAppFragment)

        }

        binding.IniciarSesion.setOnClickListener {
            findNavController().navigate(R.id.action_signupFragment_to_loginAppFragment)

        }
    }
}