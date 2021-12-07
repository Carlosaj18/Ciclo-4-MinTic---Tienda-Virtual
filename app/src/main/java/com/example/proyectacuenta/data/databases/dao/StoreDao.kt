package com.example.proyectacuenta.data.databases.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.proyectacuenta.data.models.Product
import com.example.proyectacuenta.data.models.StoreInfo

@Dao
interface StoreDao {

    @Insert
    suspend fun insertStores(Stores: List<StoreInfo>)

    // Se pueden hacer filtros
    // Se trae el nombre de la tabla de datos que se menciono en los models
    // @Query("SELECT * FROM stores LIMIT 3" )
    @Query("SELECT * FROM stores")
    suspend fun getAllStores(): List<StoreInfo>

}