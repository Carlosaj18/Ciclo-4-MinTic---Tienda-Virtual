package com.example.proyectacuenta

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.proyectacuenta.databinding.FragmentIngresoBinding

/**
 * A simple [Fragment] subclass.
 * Use the [IngresoFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class IngresoFragment : Fragment() {

    private var _binding: FragmentIngresoBinding? = null

    // El bindin sirve para ...
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment to return a view
        _binding = FragmentIngresoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        binding.login.setOnClickListener{
            findNavController().navigate(R.id.action_ingresoFragment_to_loginAppFragment)

        }

        binding.registro.setOnClickListener{
            findNavController().navigate(R.id.action_ingresoFragment_to_signupFragment)
        }
    }
}