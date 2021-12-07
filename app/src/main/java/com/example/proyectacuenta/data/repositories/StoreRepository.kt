package com.example.proyectacuenta.data.repositories

import com.example.proyectacuenta.data.databases.dao.StoreDao
import com.example.proyectacuenta.data.mocks.CommentMock
import com.example.proyectacuenta.data.mocks.StoreInfoMock
import com.example.proyectacuenta.data.models.Comment
import com.example.proyectacuenta.data.models.Product
import com.example.proyectacuenta.data.models.StoreInfo
import com.example.proyectacuenta.utils.Constans
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

// El repositorio coordina las diferentes fuentes de datos para darselas al viewModel
// Recibe el parametro de la fuente de datos a la que va a acceder
class StoreRepository(private val dataSource: StoreInfoMock, private val dataSourceDb: StoreDao,
                      private val dataSourceFirebase: FirebaseFirestore) {


    // Se crea la variable para la base dedatos
    private val db: CollectionReference = dataSourceFirebase.collection(Constans.STORE_COLLECTION)

    //Va a tener una funcion asincronica que nos permite hacer corrutinas
    suspend fun loadInfo(): List<StoreInfo> {
        /* Para traer la data quemada */
        // Aqui se puede hacer una llamada a un servicio HTTP o a firebase y lo unico que se hace es cambiar el metodo
        // Se esta llamando al dataSource para devolver la funcion que tiene en StoreInfoMock -> loadInfo()
        // Para traer la data quemada
        // return dataSource.loadStore()

        /* Para traer la data desde la base de datos */
        // return dataSourceDb.getAllStores()

        /* Para traer la data de firebase */
        val snapshot = db.get().await()
        return snapshot.toObjects(StoreInfo::class.java)
    }

    suspend fun insertStore(stores: List<StoreInfo>) {
        val temp = dataSourceDb.getAllStores()
        if(temp.isEmpty()){
            dataSourceDb.insertStores(stores)
        }
    }
}
