package com.example.proyectacuenta.data.databases.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.proyectacuenta.data.models.Comment


@Dao
interface CommentDao {

    // Insertar los dao
    @Insert
    suspend fun insertComments(Comments: List<Comment>)

    // Se pueden hacer filtros
    // @Query("SELECT * FROM comments WHERE author =: param1")
    // suspend fun getAllComments(param1: String): List<Comment>
    @Query("SELECT * FROM comments")
    suspend fun getAllComments(): List<Comment>

}