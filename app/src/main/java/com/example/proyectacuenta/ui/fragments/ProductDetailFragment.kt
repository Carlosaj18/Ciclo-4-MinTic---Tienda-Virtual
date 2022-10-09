package com.example.proyectacuenta.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.proyectacuenta.R
import com.example.proyectacuenta.databinding.FragmentProductDetailBinding
import com.example.proyectacuenta.ui.viewmodels.LoginViewModel
import com.example.proyectacuenta.ui.viewmodels.ProductViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class ProductDetailFragment : Fragment() {

    // Tema del binding
    private var _binding: FragmentProductDetailBinding? = null
    private val binding get() = _binding!!

    // Se crea el viewModel y se le entrega el viewModel que este atado a la actividad (comparten la misma instancia)
    private val productViewModel: ProductViewModel by sharedViewModel()
    private val loginViewModel: LoginViewModel by viewModel()
    private var cantidad: Int = 0
    private var total: Int = 0

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
        loginViewModel.loggedIn()
        productViewModel.loadProducts()

        binding.checkout.setOnClickListener{
            findNavController().navigate(R.id.action_productDetailFragment_to_checkOutFragment)
        }

        observeViewModel()
        observeViewModelUser()
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

            // Declaro las variables de inicializacion
            binding.cantidadProductos.text = cantidad.toString()
            binding.totalPedido.text = total.toString()

            binding.cantidadNegativa.setOnClickListener{

                if(cantidad > 0 ){
                    cantidad -= 1
                    binding.cantidadProductos.text = cantidad.toString()
                    total = product.price!!.toInt() * cantidad
                    binding.totalPedido.text = total.toString()
                }
            }
            binding.cantidadPositiva.setOnClickListener{
                cantidad += 1
                binding.cantidadProductos.text = cantidad.toString()
                total = product.price!!.toInt() * cantidad
                binding.totalPedido.text = total.toString()
            }
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