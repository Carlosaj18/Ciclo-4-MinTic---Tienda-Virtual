package com.example.proyectacuenta

import android.app.Application
import com.example.proyectacuenta.di.dataSourceModule
import com.example.proyectacuenta.di.databaseModule
import com.example.proyectacuenta.di.repoModule
import com.example.proyectacuenta.di.viewModeleModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

// Se creo un archivo de App para indicarle a Koin como seria el inyector de dependencias
// El cual se le debe indicar en el archivo de manifiest

class App: Application() {
    // Para habilitar un inyector de dependencias custom

    // Se sobreescribe el metodo onCreate()
    override fun onCreate() {
        // Habilitar nuestro inyector de dependencias con Koin
        super.onCreate()
        // Se inicia el Koin con la finalidad de poder hacer la inteccion de dependencias
        // Se le indica el contexto de esta clase -> El cual es el contexto general
        startKoin{
            androidContext(this@App)
            // Aqui se van a listar los inyectores de dependencias que se indicaron en la carpeta DI (Inyectores de Dependencias)
            // Koin sabra como inyectar todos los dataSourceModule y los repositorios
            modules(listOf(dataSourceModule, repoModule, viewModeleModule, databaseModule))
        }
    }

}