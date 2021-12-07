package com.example.proyectacuenta.di

import com.example.proyectacuenta.data.mocks.CommentMock
import com.example.proyectacuenta.data.mocks.ProductMock
import com.example.proyectacuenta.data.mocks.StoreInfoMock
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import org.koin.dsl.module

// Es un inyector de dependencias para no crear las instancias new para inyectarlos de manera automatica
// Se le indico como debe crear cada una de las isntancias
// Se encarga de inyectar todos los datasource (fuentes de datos -> Comments, Products, StoreInfo)

val dataSourceModule = module {
    single { CommentMock() }
    single { ProductMock() }
    single { StoreInfoMock() }
    single { FirebaseAuth.getInstance() }
    // Obtenemos la referencia para inyectar el fireStorage
    single { FirebaseStorage.getInstance().getReference() }
    // Inyectar el firebasefirestore
    single { FirebaseFirestore.getInstance() }

}