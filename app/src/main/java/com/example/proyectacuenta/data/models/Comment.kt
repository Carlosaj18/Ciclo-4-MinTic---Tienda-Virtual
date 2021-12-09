package com.example.proyectacuenta.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

// Son los Plan Java Objects que representan los atributos de un objeto en especifico
// Se ponen unas etiquetas

@Entity(tableName = "comments")
data class Comment(
    // Solo tienen getters and setters (no tienen ningun comportamiento)
    @PrimaryKey var id: String = "",
    var description: String?,
    var author: String?,
    var image: String = "",
    var date: String?,
    var asunto: String?
) {
    constructor(): this("", "", "", "", "", "")
}