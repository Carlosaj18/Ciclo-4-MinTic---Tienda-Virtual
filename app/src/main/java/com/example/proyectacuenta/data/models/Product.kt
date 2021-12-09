package com.example.proyectacuenta.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "products")
data class Product(
    // Solo tienen getters and setters (no tienen ningun comportamiento)
    @PrimaryKey var id: String = "",
    var name: String?,
    var description: String?,
    var image: String?,
    var price: String?,
    var category: String?
    //var store: String?
) {
    //constructor(): this("", "", "", "", "", "",  "")
    constructor(): this("", "", "", "", "", "")
}