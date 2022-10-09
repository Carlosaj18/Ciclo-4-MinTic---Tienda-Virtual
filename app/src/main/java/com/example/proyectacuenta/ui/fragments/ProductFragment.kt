package com.example.proyectacuenta.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.proyectacuenta.R
import com.example.proyectacuenta.ui.adapters.ProductAdapter
import com.example.proyectacuenta.data.models.Product
import com.example.proyectacuenta.databinding.FragmentProductBinding
import com.example.proyectacuenta.ui.listeners.onProductListener
import com.example.proyectacuenta.ui.viewmodels.ProductViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import android.widget.AdapterView
import android.widget.Spinner
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.proyectacuenta.ui.viewmodels.LoginViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.*

/*
 Los fragmentos solo tienen un viewModel al cual le indican que tiene que realizar ciertas acciones (como cargar los datos del repo) y si estan interesados en esa informacion tomaran esos observables (observe) y los observaran y cuando nueva info este se ejecuta la funcion del newDataSet
 */
class ProductFragment : Fragment() {

    private var _binding: FragmentProductBinding? = null
    private val binding get() = _binding!!

    // Se debe crear el productAdapter y el GridLayoutManager (como queremos iniciar en grilla llamamos al manager)
    private lateinit var productAdapter: ProductAdapter
    private lateinit var productManager: GridLayoutManager
    private lateinit var categories: ArrayAdapter<String>
    private var spinnerCategorias:Spinner?=null

    // Inyectamos el viewModel
    private val productViewModel: ProductViewModel by sharedViewModel()
    private val loginViewModel: LoginViewModel by viewModel()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentProductBinding.inflate(inflater, container, false)
        return binding.root
    }

    // Se inicia
    override fun onStart() {
        super.onStart()
        loginViewModel.loggedIn()

        binding.fabButtomProductos.setOnClickListener {
            findNavController().navigate(R.id.action_productFragment_to_addProductFragment)
        }

        // Cargamos el viewModel con la informacion de los productos
        //spinnerCategorias = binding.spinnerCategorias
        //val listaCategorias = arrayOf("Selecciona una categoria", "Verduras", "Carnes", "Frutas")
        //var adaptador:ArrayAdapter<String> = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_dropdown_item)
        //binding.spinnerCategorias?.adapter=adaptador

        binding.spinnerCategorias.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                if(position>0){
                    var elemento = parent!!.getItemAtPosition(position) as String
                    productViewModel.loadProductsFilter(elemento)
                } else {
                    productViewModel.loadProducts()
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                productViewModel.loadProducts() // indice 0
            }

        }

        // Inicializamos el productAdapter el cual requiere de una lista
        productAdapter = ProductAdapter(
            listOf()
            /*  1. Se puede incluir los datos quemados aqui en esta lista
                2. Lista vacia
                Product("1", "Product1", "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged.", "https://cdn.pixabay.com/photo/2016/03/05/19/02/hamburger-1238246_960_720.jpg", "$10.000"),
                Product("2", "Product2", "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged.", "https://cdn.pixabay.com/photo/2016/03/05/19/02/hamburger-1238246_960_720.jpg", "$10.000"),
                Product("3", "Product3", "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged.", "https://cdn.pixabay.com/photo/2016/03/05/19/02/hamburger-1238246_960_720.jpg", "$10.000"),
                Product("4", "Product4", "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged.", "https://cdn.pixabay.com/photo/2016/03/05/19/02/hamburger-1238246_960_720.jpg", "$10.000"),
                Product("5", "Product5", "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged.", "https://cdn.pixabay.com/photo/2016/03/05/19/02/hamburger-1238246_960_720.jpg", "$10.000"),
                Product("6", "Product6", "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged.", "https://cdn.pixabay.com/photo/2016/03/05/19/02/hamburger-1238246_960_720.jpg", "$10.000"),
                Product("7", "Product7", "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged.", "https://cdn.pixabay.com/photo/2016/03/05/19/02/hamburger-1238246_960_720.jpg", "$10.000"),
                Product("8", "Product8", "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged.", "https://cdn.pixabay.com/photo/2016/03/05/19/02/hamburger-1238246_960_720.jpg", "$10.000"),
                Product("9", "Product9", "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged.", "https://cdn.pixabay.com/photo/2016/03/05/19/02/hamburger-1238246_960_720.jpg", "$10.000"),
                Product("10", "Product10", "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged.", "https://cdn.pixabay.com/photo/2016/03/05/19/02/hamburger-1238246_960_720.jpg", "$10.000")
            */
        )
        // Se crea el manager
        productManager = GridLayoutManager(requireContext(), 2)
        // Al adaptador le creamos una variable de listener
        // Implementamos el metodo y esto es lo que se ejecutaria
        productAdapter.listener = object : onProductListener {
            override fun onClick(item: Product) {
                // Esta detectando el click
                Log.d("PRODUCT", item.name!!)
                // Le indico que acabe de seleccionar un producto
                productViewModel.selectProduct(item)
                // Navegar a la vista detallada del producto
                findNavController().navigate(R.id.action_productFragment_to_productDetailFragment)
            }
        }
        // Con el bindign se crea el recycleView con su ID del xml
        binding.productRecycler.apply {
            // // Indicamos quien es el adaptador y manager
            adapter = productAdapter
            layoutManager = productManager

        }

        // Con este metodo en el fragmento se llama al observable
        observedViewModels()
        observeViewModelUser()
    }

    private fun observedViewModels(){
        // Tomamos el observable y lo volvemos a observar
        // Los fragmentos o actividades no tienen nada que ver con la informacion
        // Solo tienen unos viewModels que le indican que tiene que realizar ciertas acciones (cargar los products)
        // Tomaran esos observables y los observaran y cuando nueva informacion este realizara la siguiente logica
        productViewModel.products.observe(viewLifecycleOwner, Observer { products ->
            productAdapter.newDataSet(products)

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