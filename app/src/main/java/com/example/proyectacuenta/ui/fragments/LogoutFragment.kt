package com.example.proyectacuenta.ui.fragments

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.example.proyectacuenta.R
import com.example.proyectacuenta.databinding.FragmentAddProductBinding
import com.example.proyectacuenta.databinding.FragmentLogoutBinding
import com.example.proyectacuenta.ui.activities.MainActivity
import com.example.proyectacuenta.ui.viewmodels.LoginViewModel
import com.example.proyectacuenta.ui.viewmodels.ProductViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import androidx.lifecycle.Observer



class LogoutFragment : Fragment() {

    private var _binding: FragmentLogoutBinding? = null
    private val binding get() = _binding!!
    private val loginViewModel: LoginViewModel by viewModel()
    private val REQUEST_CAMARA_PERMISON = 1
    private val REQUEST_IMAGE = 2


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentLogoutBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        loginViewModel.loggedIn()
        checkPermission()
        events()
        observeViewModels()

    }

    private fun events(){
        binding.profileImage.setOnClickListener{
            // Si alguien le da click a la fotografia voy a preguntar si tiene los permisos
            if(ContextCompat.checkSelfPermission(requireContext(), android.Manifest.permission.CAMERA)
                // Si el permiso es diferente de garantizado
                == PackageManager.PERMISSION_GRANTED){
                // Para lanzar la camara tengo que lanzar un intent explicito
                // El image capture va a decir lanzame el intent y lo que se hara es lanzar la actividad
                Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { intent ->
                    try {
                        startActivityForResult(intent, REQUEST_IMAGE)
                    } catch (e: Exception){

                    }
                }
            }

        }

        binding.logout.setOnClickListener {
            loginViewModel.logOut()

        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        // super.onActivityResult(requestCode, resultCode, data)
        if(resultCode == Activity.RESULT_OK){
            // Si el acceso fue exito
            // Si el request es igual a la constante REQUEST_IMAGE
            if(requestCode == REQUEST_IMAGE) {
                // Hago mi imagen bitmap
                // Me saca la data y la casteo a un Bitmap (viene por el obejto ACTION_IMAGE_CAPTURE)
                val bitmap = data?.extras?.get("data") as Bitmap
                // binding.profileImage.setImageBitmap(bitmap)
                // Le pasamos el viewModel con su metodo para cargar la imagen
                loginViewModel.uploadImage(bitmap)
            }
        }
    }

    private fun checkPermission() {
        // Si se tiene el permiso de la camara
        if(ContextCompat.checkSelfPermission(requireContext(), android.Manifest.permission.CAMERA)
            // Si el permiso es diferente de garantizado
            != PackageManager.PERMISSION_GRANTED) {
            // Solicita el permiso a la actividad y le paso la constate para saber si fue exitoso o no
            ActivityCompat.requestPermissions(requireActivity(), arrayOf(android.Manifest.permission.CAMERA),
                REQUEST_CAMARA_PERMISON)
        }
    }

    private fun observeViewModels(){
        loginViewModel.user.observe(viewLifecycleOwner, Observer { user ->
            if(user != null) {
                binding.nombre.text = user!!.displayName
                // Si el usuario tiene imagen se almacena
                if(user!!.photoUrl != null) {
                    Glide.with(binding.root).load(user.photoUrl).into(binding.profileImage)
                }
            } else {
                // Si el usuario no esta logeado creo una intencion
                val intent = Intent(requireContext(), MainActivity::class.java)
                startActivity(intent)
                requireActivity().finish()

            }
        })

        loginViewModel.error.observe(viewLifecycleOwner, Observer { error ->
            Toast.makeText(requireContext(), error, Toast.LENGTH_LONG).show()
        })
    }
}