package com.example.proyectacuenta.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
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
    private val storeViewModel: StoreViewModel by sharedViewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentHomeStoreBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Spinner Departamentos
        val arrayList_departments = arrayListOf<String>("Santander","Cundinamarca","Antioquia")
        val arrayAdapter_departments= ArrayAdapter(requireContext(), android.R.layout.simple_spinner_dropdown_item, arrayList_departments)
        binding.spinnerDepartments.adapter=arrayAdapter_departments

        // Spinner Municipios
        val arrayList_Santander = arrayListOf<String>("Bucaramanga","Floridablanca","Giron")
        val arrayList_Cundinamarca = arrayListOf<String>("Bogota","Soacha","Villeta")
        val arrayList_Antioquia = arrayListOf<String>("Medellin","Bello","Rionegro")
        val arrayAdapter_cities=ArrayAdapter(requireContext(),android.R.layout.simple_spinner_dropdown_item, arrayList_departments)
        binding.spinnerCities.adapter=arrayAdapter_cities

        // Seleccionar departamentos
        binding.spinnerDepartments.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            )   {

                if( position == 0 )
                {
                    val arrayAdapter_cities = ArrayAdapter(requireContext(),android.R.layout.simple_spinner_dropdown_item, arrayList_Santander)
                    binding.spinnerCities.adapter=arrayAdapter_cities
                    var elemento = parent!!.getItemAtPosition(position) as String
                    storeViewModel.loadStoreFilter(elemento)
                    Log.d("CIUDAD", "La ciudad seleccionada es: ${elemento}")
                }

                if( position == 1 )
                {
                    val arrayAdapter_cities = ArrayAdapter(requireContext(),android.R.layout.simple_spinner_dropdown_item, arrayList_Cundinamarca)
                    binding.spinnerCities.adapter=arrayAdapter_cities
                    var elemento = parent!!.getItemAtPosition(position) as String
                    storeViewModel.loadStoreFilter(elemento)
                    Log.d("CIUDAD", "La ciudad seleccionada es: ${elemento}")
                }

                if( position == 2 )
                {
                    val arrayAdapter_cities = ArrayAdapter(requireContext(),android.R.layout.simple_spinner_dropdown_item, arrayList_Antioquia)
                    binding.spinnerCities.adapter=arrayAdapter_cities
                    var elemento = parent!!.getItemAtPosition(position) as String
                    storeViewModel.loadStoreFilter(elemento)
                    Log.d("CIUDAD", "La ciudad seleccionada es: ${elemento}")
                } else {
                    storeViewModel.loadInfo()

                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                storeViewModel.loadInfo()
            }
        }
    }

    override fun onStart() {
        super.onStart()

        binding.consultarTienda.setOnClickListener {
            findNavController().navigate(R.id.action_homeStoreFragment_to_storeFragment)
        }

        // Retorna el current User
        loginViewModel.loggedIn()
        observeViewModels()
    }

    private fun observeViewModels() {
        loginViewModel.user.observe(viewLifecycleOwner, Observer { user ->
            if(user != null) {
                binding.userName.text = user!!.displayName
                if(user!!.photoUrl != null) {
                    Glide.with(binding.root).load(user.photoUrl).into(binding.profileImage)
                }
            }

        })
    }
}



