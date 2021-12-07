package com.example.proyectacuenta.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.proyectacuenta.R
import com.example.proyectacuenta.ui.adapters.CommentAdapter
import com.example.proyectacuenta.data.models.Comment
import com.example.proyectacuenta.data.models.StoreInfo
import com.example.proyectacuenta.databinding.FragmentCommentBinding
import com.example.proyectacuenta.ui.adapters.StoreAdapter
import com.example.proyectacuenta.ui.listeners.OnCommentListener
import com.example.proyectacuenta.ui.listeners.OnStoreListener
import com.example.proyectacuenta.ui.viewmodels.CommentViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class CommentFragment : Fragment() {

    private var _binding: FragmentCommentBinding? = null
    private val binding get() = _binding!!

    private lateinit var commentAdapter: CommentAdapter
    // revisar si se pasa el storeAdapter -> private lateinit var storeAdapter: StoreAdapter
    private lateinit var storeAdapter: StoreAdapter
    private lateinit var commentManager: LinearLayoutManager

    // Si no quiere que la vida del ciclo este atada a la actividad se usa viewModel(), entonces estara atado al cilo del vida del fragmento
    private val commentViewModel: CommentViewModel by sharedViewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentCommentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        // Se llama a los comentarios
        commentViewModel.loadComments()
        commentViewModel.loadStore()

        commentAdapter = CommentAdapter(
            listOf(/* Se debe pasar una Lista vacia para no quemar los datos
                Comment("1", "Neque porro quisquam est qui dolorem ipsum quia dolor sit amet, consectetur, adipisci velit...", "Carlos Jaramillo", "https://w7.pngwing.com/pngs/524/696/png-transparent-computer-icons-user-symbol-symbol-miscellaneous-black-computer-icons.png", "2021-11-26"),
                Comment("1", "Neque porro quisquam est qui dolorem ipsum quia dolor sit amet, consectetur, adipisci velit...", "Andres Portilla", "https://w7.pngwing.com/pngs/524/696/png-transparent-computer-icons-user-symbol-symbol-miscellaneous-black-computer-icons.png", "2021-11-27")
            */)
        )

        storeAdapter = StoreAdapter(listOf())

        // Recive dos parametros pero por defecto es vertical
        commentManager = LinearLayoutManager(requireContext())
        commentAdapter.listener = object : OnCommentListener {
            override fun onClick(item: Comment) {
                Log.d("COMMENT", item.author!!)
                commentViewModel.selectComment(item)
                // Setear la tienda
                // commentViewModel.selectStore(item)
                findNavController().navigate(R.id.action_commentFragment_to_commentsDetailFragment)
            }
        }

        binding.commentRecycler.apply {
            // Indicamos quien es el adaptador y manager
            adapter = commentAdapter
            layoutManager = commentManager
        }

        // Funcion para observar
        observeViewModels()
    }

    private fun observeViewModels(){
        // Se observa el observable
        // Se recibe una lista de comentarios e indicarle cuales comentarios deberia mostrar en el Adapter
        commentViewModel.comments.observe(viewLifecycleOwner, Observer { comments ->
            // Se le debe indicar al adaptador que se deben cambiar los datos a mostrar
            // En el Adaptador se llama al metodo que reciba una nueva lista de comentarios
            // Toca llamar al metodo para indicarle que su data cambio
            commentAdapter.newDataSet(comments)
        })
        commentViewModel.store.observe(viewLifecycleOwner, Observer { store ->
            storeAdapter.newDataSet(store)
        })

    }

}
