package com.example.proyectacuenta.ui.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.proyectacuenta.ui.activities.HomeActivity
import com.example.proyectacuenta.R
import com.example.proyectacuenta.databinding.FragmentLoginAppBinding
import com.example.proyectacuenta.isValidEmail
import com.example.proyectacuenta.ui.activities.HomeTenderoActivity
import com.example.proyectacuenta.ui.viewmodels.LoginTenderoViewModel
import com.example.proyectacuenta.ui.viewmodels.LoginViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginAppFragment : Fragment() {

    // Se crea el binding
    private var _binding: FragmentLoginAppBinding? = null

    // El bindin sirve para acceder a los elementos en los layouts
    private val binding get() = _binding!!

    // Inyectamos nuestro viewModel
    private val loginViewModel: LoginViewModel by sharedViewModel()
    private val loginTenderoViewModel: LoginTenderoViewModel by sharedViewModel()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment to return a view
        _binding = FragmentLoginAppBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onStart() {
        super.onStart()

        binding.loginCuentaNuevaLabel.setOnClickListener{
            binding.loginCrearCuentaBottom.visibility = View.VISIBLE
        }
        binding.loginOlvidoPassword.setOnClickListener {
            findNavController().navigate(R.id.action_loginAppFragment_to_olvidoPasswordFragment2)

        }
        binding.loginCrearCuentaBottom.setOnClickListener {
            findNavController().navigate(R.id.action_loginAppFragment_to_signupFragment)

        }

        binding.loginButtom.setOnClickListener{
            // Asumo que el formulario de login es valido
            var isValid = true

            if(!binding.loginEmail.text.isValidEmail()){
                // Si el email no es valido usando la expresion regular devuelvo que no es valido y saco un error
                isValid = false
                binding.loginEmailLayout.error = "Correo electronico no valido"
            } else {
                binding.loginEmailLayout.error = null
            }
            // Si la contraseña es menor a 4 caracteres indico que es invalida y saco un error
            if(binding.loginPassword.text.toString().length < 4){
                isValid = false
                binding.loginPasswordLayout.error = "Contraseña invalida"
            } else {
                binding.loginPasswordLayout.error = null
            }

            if(isValid){
                loginViewModel.login(binding.loginEmail.text.toString(), binding.loginPassword.text.toString())
                loginTenderoViewModel.loginTendero(binding.loginEmail.text.toString(), binding.loginPassword.text.toString())
            }
        }

        observeViewModelUser()
        observeViewModelTendero()

    }

    private fun observeViewModelUser(){
        // Observamos a nuestro viewModel
        loginViewModel.user.observe(viewLifecycleOwner, Observer { user ->
            if(user != null) {
                // Por medio de los intent se cambia de actividad -> Contexto
                // Se deveria direccion desde el main activity con un viewModel compartido de esa actividad para que el sistema no quede acoplado
                val intent = Intent(requireContext(), HomeActivity::class.java)
                startActivity(intent)
            }
        })
        loginViewModel.error.observe(viewLifecycleOwner, Observer { error ->
            Toast.makeText(requireContext(),error, Toast.LENGTH_LONG).show()
        })
    }
    private fun observeViewModelTendero(){
        // Observamos a nuestro viewModel
        loginTenderoViewModel.tendero.observe(viewLifecycleOwner, Observer { tendero ->
            if(tendero != null) {
                // Por medio de los intent se cambia de actividad -> Contexto
                // Se deveria direccion desde el main activity con un viewModel compartido de esa actividad para que el sistema no quede acoplado
                val intent = Intent(requireContext(), HomeTenderoActivity::class.java)
                startActivity(intent)
            }
        })
        loginViewModel.error.observe(viewLifecycleOwner, Observer { error ->
            Toast.makeText(requireContext(),error, Toast.LENGTH_LONG).show()
        })
    }
}