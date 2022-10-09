package com.example.proyectacuenta.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.proyectacuenta.R
import com.example.proyectacuenta.databinding.FragmentHomeBinding
import com.example.proyectacuenta.ui.viewmodels.LoginViewModel
import com.example.proyectacuenta.ui.viewmodels.StoreViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {

    private var click = 0
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    // Inyectamos nuestro viewModel

    // Y con el shredViewModel se le indica que estara atado al ciclo de la actividad y no del fragmento
    private val storeViewModel: StoreViewModel by sharedViewModel()
    private val loginViewModel: LoginViewModel by viewModel()


    // onCreateView es un metodo del ciclo de vida de las actividades
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Lo que esta es creando nuestro layout
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        // Se carga la info de la tienda
        storeViewModel.loadInfo()
        loginViewModel.loggedIn()
        binding.locationButtom.setOnClickListener{
            binding.location.visibility = View.VISIBLE
        }

        if(click == 0) {

            binding.fabButtomMenu.setOnClickListener {
                binding.fabButtomComentarios.visibility = View.VISIBLE
                binding.fabButtomProductos.visibility = View.VISIBLE
                click += 1
                Log.d("Flating buttom", "El valor del click es ${click}")
            }
        }
        if (click == 1) {
            binding.fabButtomMenu.setOnClickListener {
                binding.fabButtomComentarios.visibility = View.GONE
                binding.fabButtomProductos.visibility = View.GONE
                click -=1
                Log.d("Flating buttom", "El valor del click es ${click}")
            }
        }

        binding.fabButtomProductos.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_productFragment)
        }

        binding.fabButtomComentarios.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_commentsStoreFragment)
        }
        //Creamos un metodo
        observeViewModels()
        observeViewModelUser()

    }

    private fun observeViewModels() {
        // Se toma al getter del viewModel para observarlo
        storeViewModel.selected.observe(viewLifecycleOwner, Observer { store ->
            // Se debe actualizar nuestro UI
            // Se podra actualizar la info de la tienda
            binding.nombreTienda.text = store.name
            binding.contactName.text = store.contactName
            binding.telefono.text = store.phone
            binding.mail.text = store.email
            binding.descriptionStore.text = store.description
            binding.direccion.text = store.address
            binding.departamento.text = store.department
            binding.ciudad.text = store.city
            binding.tienda.text = store.name
            Glide.with(binding.root).load(store.image).into(binding.imageStore)
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