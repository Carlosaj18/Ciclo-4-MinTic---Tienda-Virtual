package com.example.proyectacuenta.di

import com.example.proyectacuenta.data.databases.AppDatabase
import org.koin.dsl.module

val databaseModule = module {
    // Cuando pidan la instancia de la base de datos devuelvenos esto
    // Va ha inyectar lo que esta en la clase App
    single { AppDatabase.getInstance(get()) }

    // Cuando nos pidan un DAO
    // Me va a devolver esta instancia de la base de datos que estoy creando y le llamo el metodo abstracto ej: commentDao
    // Aqui se usa el get para indicarle lo que debe hacer
    single { get<AppDatabase>().commentDao() }
    single { get<AppDatabase>().productDao() }
    single { get<AppDatabase>().storeDao() }
}