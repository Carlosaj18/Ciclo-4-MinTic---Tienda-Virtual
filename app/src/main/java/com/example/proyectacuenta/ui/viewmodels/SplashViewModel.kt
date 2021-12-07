package com.example.proyectacuenta.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.proyectacuenta.data.models.Comment
import com.example.proyectacuenta.data.models.Product
import com.example.proyectacuenta.data.models.StoreInfo
import com.example.proyectacuenta.data.repositories.CommentRepository
import com.example.proyectacuenta.data.repositories.ProductRepository
import com.example.proyectacuenta.data.repositories.StoreRepository
import kotlinx.coroutines.launch


// Recive tres repositorios
class SplashViewModel(private val repoStore: StoreRepository, private val repoComment: CommentRepository,
                      private val repoProduct: ProductRepository): ViewModel() {

    // Metodo
    fun insert(stores: List<StoreInfo>, comments: List<Comment>, products: List<Product>){
        // Correo en el viewModel
        viewModelScope.launch {
            repoStore.insertStore(stores)
            repoComment.insertComments(comments)
            repoProduct.insertProducts(products)

        }
    }
}