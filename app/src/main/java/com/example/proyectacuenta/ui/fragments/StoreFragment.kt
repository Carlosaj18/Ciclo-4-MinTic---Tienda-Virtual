package com.example.proyectacuenta.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.proyectacuenta.R
import com.example.proyectacuenta.data.models.StoreInfo
import com.example.proyectacuenta.databinding.FragmentStoreBinding
import com.example.proyectacuenta.ui.adapters.StoreAdapter
import com.example.proyectacuenta.ui.listeners.OnStoreListener
import com.example.proyectacuenta.ui.viewmodels.LoginViewModel
import com.example.proyectacuenta.ui.viewmodels.StoreViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class StoreFragment : Fragment() {

    private var _binding: FragmentStoreBinding? = null
    private val binding get() = _binding!!

    private lateinit var storeAdapter: StoreAdapter
    private lateinit var storeManager: LinearLayoutManager

    private val storeViewModel: StoreViewModel by sharedViewModel()
    private val loginViewModel: LoginViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentStoreBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onStart() {
        super.onStart()

        //Cargamos el viewModel
        storeViewModel.loadInfo()
        loginViewModel.loggedIn()
        // Le tengo que pasar el parametro del elemento del spinner
        // storeViewModel.loadStoreFilter()
        // Se llama a los comentarios
        storeAdapter = StoreAdapter(
            listOf()
        )

        // Recive dos parametros pero por defecto es vertical
        // storeManager = LinearLayoutManager(requireContext(),  LinearLayoutManager.HORIZONTAL ,false)
        storeManager = LinearLayoutManager(requireContext())

        storeAdapter.listener = object : OnStoreListener {
            override fun onClick(item: StoreInfo) {
                // Esta detectando el click
                Log.d("STORE", item.name!!)
                // Le indico que acabe de seleccionar un producto
                storeViewModel.selectStore(item)
                // Navegar a la vista detallada de las tiendas
                findNavController().navigate(R.id.action_storeFragment_to_homeFragment)
            }
        }

        binding.storeRecycler.apply {
            // Indicamos quien es el adaptador y manager
            adapter = storeAdapter
            layoutManager = storeManager
        }

        observedViewModels()
        observeViewModelUser()
    }

        private fun observedViewModels(){
            storeViewModel.info.observe(viewLifecycleOwner, Observer { stories ->
                storeAdapter.newDataSet(stories)
        })
    }

    private fun observeViewModelUser() {
        loginViewModel.user.observe(viewLifecycleOwner, Observer { user ->
            if(user != null) {
                if(user!!.photoUrl != null) {
                    Glide.with(binding.root).load(user.photoUrl).into(binding.profileImage)
                }
            }

        })
    }
}