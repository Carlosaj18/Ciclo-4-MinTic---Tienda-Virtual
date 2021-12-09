package com.example.proyectacuenta.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.proyectacuenta.R
import com.example.proyectacuenta.databinding.FragmentAddProductBinding
import com.example.proyectacuenta.ui.viewmodels.ProductViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class AddProductFragment : Fragment() {

    private var _binding: FragmentAddProductBinding? = null
    private val binding get() = _binding!!
    private val productViewModel: ProductViewModel by sharedViewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentAddProductBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        binding.guardar.setOnClickListener {
            productViewModel.insertProducts(
                binding.idInput.text.toString(),
                binding.nameInput.text.toString(),
                binding.descriptionInput.text.toString(),
                "",
                // binding.imageInput.text.toString(),
                binding.priceInput.text.toString(),
                binding.categoryInput.text.toString()
            )
            findNavController().navigate(R.id.action_addProductFragment_to_productFragment)
        }
    }

}