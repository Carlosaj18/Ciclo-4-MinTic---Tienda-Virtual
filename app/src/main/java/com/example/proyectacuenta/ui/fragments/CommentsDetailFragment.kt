package com.example.proyectacuenta.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.example.proyectacuenta.R
import com.example.proyectacuenta.databinding.FragmentCommentsDetailBinding
import com.example.proyectacuenta.databinding.FragmentCommentsStoreBinding
import com.example.proyectacuenta.databinding.FragmentProductDetailBinding
import com.example.proyectacuenta.ui.viewmodels.CommentViewModel
import com.example.proyectacuenta.ui.viewmodels.ProductViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class CommentsDetailFragment : Fragment() {

    private var _binding: FragmentCommentsDetailBinding? = null
    private val binding get() = _binding!!

    private val commentViewModel: CommentViewModel by sharedViewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCommentsDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        observeViewModel()

    }

    private fun observeViewModel() {
        commentViewModel.selected.observe(viewLifecycleOwner, Observer { comment ->
            binding.nombreDelUsuario.text = comment.author
            binding.asunto.text = comment.asunto
            binding.ultimaConexion.text = comment.date
            binding.comentario.text = comment.description
            Glide.with(binding.root).load(comment.image).into(binding.Foto)
        })

        commentViewModel.store_selected.observe(viewLifecycleOwner, Observer { store ->
            binding.nombreTienda.text = store.name
            binding.contactName.text = store.contactName
            binding.direccion.text = store.address
            Glide.with(binding.root).load(store.image).into(binding.imageStore)

        })
    }
}