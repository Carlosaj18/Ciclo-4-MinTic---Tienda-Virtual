package com.example.proyectacuenta.di

import com.example.proyectacuenta.ui.viewmodels.*
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModeleModule = module {
    // Cual es la diferencia entre single y viewModel para inyectar dependencias???
    // Al viewModel se le inyecta el storeViewModel y este necesita de un parametro para eso se hace uso del get()
    viewModel { StoreViewModel(get()) }
    viewModel { ProductViewModel(get()) }
    viewModel { CommentViewModel(get(), get()) }
    viewModel { SplashViewModel(get(), get(), get()) }
    viewModel { LoginViewModel(get()) }
}