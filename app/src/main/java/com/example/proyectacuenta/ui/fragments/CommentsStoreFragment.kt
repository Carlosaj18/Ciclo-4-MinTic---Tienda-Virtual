package com.example.proyectacuenta.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.proyectacuenta.databinding.FragmentCommentBinding
import com.example.proyectacuenta.databinding.FragmentCommentsStoreBinding

class CommentsStoreFragment : Fragment() {

    private var _binding: FragmentCommentsStoreBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCommentsStoreBinding.inflate(inflater, container, false)
        return binding.root
    }
}

