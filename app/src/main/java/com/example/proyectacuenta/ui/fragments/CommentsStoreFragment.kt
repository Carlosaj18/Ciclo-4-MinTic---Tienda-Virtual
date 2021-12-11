package com.example.proyectacuenta.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.NavHostFragment.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.proyectacuenta.R
import com.example.proyectacuenta.data.models.Comment
import com.example.proyectacuenta.data.models.StoreInfo
import com.example.proyectacuenta.databinding.FragmentCommentBinding
import com.example.proyectacuenta.databinding.FragmentCommentsStoreBinding
import com.example.proyectacuenta.ui.adapters.CommentStoreAdapter
import com.example.proyectacuenta.ui.adapters.StoreAdapter
import com.example.proyectacuenta.ui.listeners.OnCommentListener
import com.example.proyectacuenta.ui.listeners.OnStoreListener
import com.example.proyectacuenta.ui.viewmodels.CommentViewModel
import com.example.proyectacuenta.ui.viewmodels.StoreViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class CommentsStoreFragment : Fragment() {

    private var _binding: FragmentCommentsStoreBinding? = null
    private val binding get() = _binding!!
    private lateinit var commentStoreAdapter: CommentStoreAdapter
    private lateinit var storeAdapter: StoreAdapter
    private lateinit var commentManager: LinearLayoutManager

    private val commentViewModel: CommentViewModel by sharedViewModel()
    private val storeViewModel: StoreViewModel by sharedViewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCommentsStoreBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onStart() {
        super.onStart()

        commentStoreAdapter = CommentStoreAdapter(listOf())
        storeAdapter = StoreAdapter(listOf())
        commentManager = LinearLayoutManager(requireContext())
        commentViewModel.loadComments()
        storeViewModel.loadInfo()

        commentStoreAdapter.listener = object : OnCommentListener {
            override fun onClick(item: Comment) {
                Log.d("COMMENT", item.author!!)
                commentViewModel.selectComment(item)
                // Setear la tienda
                // commentViewModel.selectStore(item)
                findNavController().navigate(R.id.action_commentsStoreFragment_to_commentsDetailFragment)
            }
        }

        binding.commentRecycler.apply {
            // Indicamos quien es el adaptador y manager
            adapter = commentStoreAdapter
            layoutManager = commentManager
        }

        // Crear comentarios con el formulario
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
        /*binding.enviar.setOnClickListener {
            binding.idCommentInput.setText("")
            binding.textInput.setText("")
            binding.nombreInput.setText("")
            binding.fechaInput.setText("")
            binding.asuntoInput.setText("")
        }*/

        observeViewModels()
        observedViewModelStore()
    }

    private fun observeViewModels(){
        commentViewModel.comments.observe(viewLifecycleOwner, Observer { comments ->
            // Se le debe indicar al adaptador que se deben cambiar los datos a mostrar
            // En el Adaptador se llama al metodo que reciba una nueva lista de comentarios
            // Toca llamar al metodo para indicarle que su data cambio

            // NO SE PORQUE SE PINTAN LOS DATOS DEL RECYCLEVIEW DE LOS COMENTARIOS CON ESTE COMANDO DE ABAJO?????????????
            commentStoreAdapter.newDataSet(comments)

        })
    }

    private fun observedViewModelStore(){
        storeViewModel.info.observe(viewLifecycleOwner, Observer { stories ->
            storeAdapter.newDataSet(stories)
        })
    }
}

