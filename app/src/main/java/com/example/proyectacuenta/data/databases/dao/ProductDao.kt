package com.example.proyectacuenta.data.databases.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.proyectacuenta.data.models.Product

@Dao
interface ProductDao {

    @Insert
    suspend fun insertProducts(Products: List<Product>)

    // Se pueden hacer filtros
    // Se trae el nombre de la tabla de datos que se menciono en los models
    @Query("SELECT * FROM products")
    suspend fun getAllProducts(): List<Product>
}