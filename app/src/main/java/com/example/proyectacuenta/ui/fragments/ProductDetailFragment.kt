package com.example.proyectacuenta.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.example.proyectacuenta.R
import com.example.proyectacuenta.databinding.FragmentProductDetailBinding
import com.example.proyectacuenta.ui.viewmodels.ProductViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel


class ProductDetailFragment : Fragment() {

    // Tema del binding
    private var _binding: FragmentProductDetailBinding? = null
    private val binding get() = _binding!!

    // Se crea el viewModel y se le entrega el viewModel que este atado a la actividad (comparten la misma instancia)
    private val productViewModel: ProductViewModel by sharedViewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        // return inflater.inflate(R.layout.fragment_product_detail, container, false)

        // Que hace el inflate??
        _binding = FragmentProductDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        observeViewModel()
    }

    private fun observeViewModel(){
        // Se toma el view model y lo voy a observar el elemento seleccionado
        productViewModel.selected.observe(viewLifecycleOwner, Observer { product ->
            // Cuando se hizo click se cambio el observable al producto seleccionado y el metodo _selected se encargo de pushear los nuevos valores con el postValue
            // Y la variable selected esta exponiendo el nuevo observable
            // El detalle del producto es el encargado de observarlo
            // Se comparte informacion al viewModel atandolo al ciclo de vida de la actividad
            // Se setean los datos
            binding.productoName.text = product.name
            binding.productoPrice.text = product.price
            binding.productoCategory.text = product.category
            Glide.with(binding.root).load(product.image).into(binding.imagenProducto)
        })
    }
}