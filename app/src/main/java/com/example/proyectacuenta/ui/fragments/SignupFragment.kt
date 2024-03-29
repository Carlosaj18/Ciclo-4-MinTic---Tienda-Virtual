package com.example.proyectacuenta.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.proyectacuenta.R
import com.example.proyectacuenta.databinding.FragmentSignupBinding
import com.example.proyectacuenta.ui.viewmodels.LoginTenderoViewModel
import com.example.proyectacuenta.ui.viewmodels.LoginViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class SignupFragment : Fragment() {

    private var _binding: FragmentSignupBinding? = null

    // El bindin sirve para ...
    private val binding get() = _binding!!

    // Inyectamos nuestro viewModel
    private val loginViewModel: LoginViewModel by sharedViewModel()
    private val loginTenderoViewModel: LoginTenderoViewModel by sharedViewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {
        // Inflate the layout for this fragment to return a view
        _binding = FragmentSignupBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onStart() {
        super.onStart()

        // Checkbuttom para perfil de tendero o usuario
        binding.registerScrolviewTendero.isVisible = false
        binding.radioGroup.setOnCheckedChangeListener{ group, checkedId ->
            binding.registerScrolviewCliente.isVisible = binding.registerRolUser.isChecked
            binding.registerScrolviewTendero.isVisible = binding.registerRolStore.isChecked

            binding.registro.setOnClickListener{

                if(binding.registerScrolviewTendero.isVisible) {
                    loginTenderoViewModel.signUpTendero(
                        binding.idInputTendero.text.toString(),
                        binding.nombresInputTendero.text.toString(),
                        binding.apellidoInputTendero.text.toString(),
                        binding.documentoIdentidadTendero.text.toString(),
                        binding.celularStore.text.toString(),
                        binding.emailUserStore.text.toString(),
                        binding.passwordTendero.text.toString(),
                        binding.nombreStore.text.toString(),
                    )
                } else {
                    loginViewModel.signUp(
                        binding.idInput.text.toString(),
                        binding.nombresInput.text.toString(),
                        binding.apellidosInput.text.toString(),
                        binding.documentoIdentidad.text.toString(),
                        binding.celular.text.toString(),
                        binding.emailUser.text.toString(),
                        binding.passwordUser.text.toString()
                    )
                }
                findNavController().navigate(R.id.action_signupFragment_to_loginAppFragment)
            }

            observeViewModels()
        }

        binding.IniciarSesion.setOnClickListener {
            findNavController().navigate(R.id.action_signupFragment_to_loginAppFragment)
        }

        /*
        Validacion de contrasena
        binding.registro.setOnClickListener {

            var isValid = true

            if(binding.passwordUser.text.toString() != binding.passwordConfirmationUser.text.toString()){
                binding.passwordConfirmationLayout.error = "Las constraseñas no coinciden"
                var isValid = false
            }
            if(isValid != false){
                findNavController().navigate(R.id.action_signupFragment_to_loginAppFragment)
            }
        }*/
    }

    private fun observeViewModels(){
        // Observamos a nuestro viewModel
        loginViewModel.user.observe(viewLifecycleOwner, Observer { user ->
            if(user != null) {
                findNavController().navigate(R.id.action_signupFragment_to_loginAppFragment)
            }
        })

        loginTenderoViewModel.tendero.observe(viewLifecycleOwner, Observer { tendero ->
            if(tendero != null) {
                findNavController().navigate(R.id.action_signupFragment_to_loginAppFragment)
            }
        })

        loginViewModel.error.observe(viewLifecycleOwner, Observer { error ->
            Toast.makeText(requireContext(), error, Toast.LENGTH_LONG).show()
        })
    }
}