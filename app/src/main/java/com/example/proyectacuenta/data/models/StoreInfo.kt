package com.example.proyectacuenta.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "stores")
data class StoreInfo(
    // Solo tienen getters and setters (no tienen ningun comportamiento)
    // Esta info deberia venir desde nuestro servicio de backend
    @PrimaryKey var id: String = "",
    var name: String?,
    var contactName: String?,
    var phone: String?,
    var email: String?,
    var image: String?,
    var description: String?,
    var department: String?,
    var city: String?,
    var address: String?,
    var lat: Double? = null,
    var lng: Double? = null
) {
    constructor(): this("", "", "", "", "", "", "", "", "", "")
}