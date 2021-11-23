package com.example.proyectacuenta

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.proyectacuenta.databinding.FragmentIngresoBinding
import com.example.proyectacuenta.databinding.FragmentLoginAppBinding

/**
 * A simple [Fragment] subclass.
 * Use the [LoginAppFragment.newInstance] factory method to
 * create an instance of this fragment.
 */

class LoginAppFragment : Fragment() {

    private var _binding: FragmentLoginAppBinding? = null

    // El bindin sirve para ...
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment to return a view
        _binding = FragmentLoginAppBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        binding.passwordCorreo.setOnClickListener {
            findNavController().navigate(R.id.action_loginAppFragment_to_olvidoPasswordFragment2)

        }
        binding.textButtonRegistro.setOnClickListener {
            findNavController().navigate(R.id.action_loginAppFragment_to_signupFragment)

        }

    }
}