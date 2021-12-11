package com.example.proyectacuenta.di

import com.example.proyectacuenta.data.repositories.*
import org.koin.dsl.module

// Se encarga de inyectar los repositorios

val repoModule = module {
    // El repositorio me pide un parametro que se lo paso con un get para que de forma automatica tome el parametro que necesita
    // Va primero a StoreRepository mira el parametro que necesita y va despues a DataSourceModule e inyecta la instancia de StoreInfoMock()
    // Despues le digo a mi App que voy a cargar el repoSourceModule
    // Se le indica que cuando el viewModel necesite de un repositorio el va inyectar los datos de esta forma
    single { StoreRepository(get(), get(), get()) } // Como tienden dos datasource se lo debo indicar a mi repositorio de dependencias
    single { ProductRepository(get(), get(), get()) }
    single { CommentRepository(get(), get(), get()) }
    single { UserRepository(get(), get(), get()) }
    single { TenderoRepository(get(), get(), get()) }
}