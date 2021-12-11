/*package com.example.proyectacuenta.ui.fragments

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
}*/

package com.example.proyectacuenta.ui.fragments

import android.app.Activity
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.proyectacuenta.R
import com.example.proyectacuenta.databinding.FragmentHomeStoreBinding
import com.example.proyectacuenta.ui.activities.HomeActivity
import com.example.proyectacuenta.ui.viewmodels.LoginViewModel
import com.example.proyectacuenta.ui.viewmodels.StoreViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class HomeStoreFragment : Fragment() {

    private var _binding: FragmentHomeStoreBinding? = null
    private val binding get() = _binding!!
    private val loginViewModel: LoginViewModel by viewModel()
    protected lateinit var homeActivity: HomeActivity
    protected lateinit var contextFragment: Context

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

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is HomeActivity) {
            this.homeActivity = context
        }
        this.contextFragment = context
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val arrayList_departments = arrayListOf<String>("Santander","Cundinamarca","Antioquia")
        val arrayAdapter_departments= ArrayAdapter(requireContext(), android.R.layout.simple_spinner_dropdown_item, arrayList_departments)
        binding.spinnerDepartments.adapter=arrayAdapter_departments
        //spinnerDepartments.adapter=arrayAdapter_departments


        val arrayList_Santander = arrayListOf<String>("Bucaramanga","Floridablanca","Giron")
        val arrayList_Cundinamarca = arrayListOf<String>("Bogota","Soacha","Villeta")
        val arrayList_Antioquia = arrayListOf<String>("Medellin","Bello","Rionegro")
        val arrayAdapter_cities=ArrayAdapter(requireContext(),android.R.layout.simple_spinner_dropdown_item , arrayList_departments)
        binding.spinnerCities.adapter=arrayAdapter_cities
        //spinnerCities.adapter=arrayAdapter_cities

        binding.spinnerDepartments.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            //spinnerDepartments.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                if(position==0)
                {
                    val arrayAdapter_cities = ArrayAdapter(requireContext(),android.R.layout.simple_spinner_dropdown_item,arrayList_Santander)
                    binding.spinnerCities.adapter=arrayAdapter_cities
                    //spinnerCities.adapter=arrayAdapter_cities
                }

                if(position==1)
                {
                    val arrayAdapter_cities = ArrayAdapter(requireContext(),android.R.layout.simple_spinner_dropdown_item,arrayList_Cundinamarca)
                    binding.spinnerCities.adapter=arrayAdapter_cities
                    //spinnerCities.adapter=arrayAdapter_cities
                }

                if(position==2)
                {
                    val arrayAdapter_cities = ArrayAdapter(requireContext(),android.R.layout.simple_spinner_dropdown_item,arrayList_Antioquia)
                    binding.spinnerCities.adapter=arrayAdapter_cities
                    //spinnerCities.adapter=arrayAdapter_cities
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }

    }
}

