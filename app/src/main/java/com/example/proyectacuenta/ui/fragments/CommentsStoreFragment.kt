package com.example.proyectacuenta.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.NavHostFragment.findNavController
import androidx.navigation.fragment.findNavController
import com.example.proyectacuenta.R
import com.example.proyectacuenta.databinding.FragmentCommentBinding
import com.example.proyectacuenta.databinding.FragmentCommentsStoreBinding
import com.example.proyectacuenta.ui.viewmodels.CommentViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class CommentsStoreFragment : Fragment() {

    private var _binding: FragmentCommentsStoreBinding? = null
    private val binding get() = _binding!!
    private val commentViewModel: CommentViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCommentsStoreBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onStart() {
        super.onStart()

        // Crear comentarios
        binding.enviar.setOnClickListener {
            commentViewModel.insertComments(
                binding.idCommentInput.text.toString(),
                binding.textInput.text.toString(),
                binding.nombreInput.text.toString(),
                "https://w7.pngwing.com/pngs/524/696/png-transparent-computer-icons-user-symbol-symbol-miscellaneous-black-computer-icons.png",
                binding.fechaInput.text.toString(),
                binding.asuntoInput.text.toString(),
            )
            findNavController().navigate(R.id.action_commentsStoreFragment_to_homeFragment)
        }
    }
}

