package com.example.proyectacuenta.data.repositories

import com.example.proyectacuenta.data.databases.dao.ProductDao
import com.example.proyectacuenta.data.mocks.ProductMock
import com.example.proyectacuenta.data.models.Comment
import com.example.proyectacuenta.data.models.Product
import com.example.proyectacuenta.utils.Constans
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class ProductRepository(private val dataSource: ProductMock, private val dataSourceDb: ProductDao,
                        private val dataSourceFirebase: FirebaseFirestore
) {

    // Se crea la variable para la base dedatos
    private val db: CollectionReference = dataSourceFirebase.collection(Constans.PRODUCT_COLLECTION)

    // El parametro se lo paso desde el viewmodel
    suspend fun loadProducts(): List<Product>{
        /* Para traer la data quemada
        No siempre se debe devolver lo que esta en el dataSource
        El repositorio se encargaria de formar el objeto de tal forma que le llegue al viewModel lo que el solicita a partir de funciones de transformacion (usando for, map functions, reuser).
        Para traer la data quemada*/
        // return dataSource.loadProducts()

        /* Para filtrar por categoria de productos */
        // val filtroCategoria = dataSource.loadProducts()
        // El valor debe llegar como parametro de la funcion
        // val filtro = filtroCategoria.filter { it.category == "Frutas" }
        // return filtro

        /* Para traer la data desde la base de datos*/
        // return dataSourceDb.getAllProducts()

        /* Para traer la data de firebase */
        val snapshot = db.get().await()
        return snapshot.toObjects(Product::class.java)
    }

    suspend fun insertProducts(products: List<Product>) {
        val temp = dataSourceDb.getAllProducts()
        if(temp.isEmpty()){
            dataSourceDb.insertProducts(products)
        }
    }
}