package com.example.proyectacuenta.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.proyectacuenta.R
import com.example.proyectacuenta.databinding.FragmentHomeStoreBinding
import com.example.proyectacuenta.ui.viewmodels.LoginViewModel
import com.example.proyectacuenta.ui.viewmodels.StoreViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class HomeStoreFragment : Fragment() {

    private var _binding: FragmentHomeStoreBinding? = null
    private val binding get() = _binding!!
    private val loginViewModel: LoginViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentHomeStoreBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        loginViewModel.loggedIn()
        observeViewModels()
        binding.consultarTienda.setOnClickListener {
            findNavController().navigate(R.id.action_homeStoreFragment_to_storeFragment)
        }
    }
    private fun observeViewModels() {
        loginViewModel.user.observe(viewLifecycleOwner, Observer { user ->
            if(user != null) {
                binding.userName.text = user!!.displayName
            }
        })

    }
}