package com.example.proyectacuenta.data.databases

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.proyectacuenta.data.databases.dao.CommentDao
import com.example.proyectacuenta.data.databases.dao.ProductDao
import com.example.proyectacuenta.data.databases.dao.StoreDao
import com.example.proyectacuenta.data.models.Comment
import com.example.proyectacuenta.data.models.Product
import com.example.proyectacuenta.data.models.StoreInfo

// Se le debe indicar todas las entidades en un arreglo y se esta accediendo a la referencia de la clase
@Database(entities = [Comment::class, Product::class, StoreInfo::class], version = 1)
abstract class AppDatabase:RoomDatabase() {

    // Cada funcion nos va a dar acceso a los DAO
    abstract fun commentDao(): CommentDao
    abstract fun productDao(): ProductDao
    abstract fun storeDao(): StoreDao

    // Companion object nos permite tener una conexion a la base de datos y me permite crear una sola instancia de la clase
    // Se crea de tipo volatile
    companion object {
        // Arranca como nula la variable
        @Volatile
        private var instance: AppDatabase? = null

        // Como puede haber varios hilos debemos crear un test para la conexion y que no se vaya a estallar

        // Recive el contexto de android y me devuelve una database

        fun getInstance(context: Context): AppDatabase {
            // Semaforo para controlar los mutiples hilos al crear la base de datos
            // Si la instancia es igual a nula es porque nadie la ha creado
            // Patron de diseño singleton y builder
            if(instance == null) {
                // El primer hilo que toma el bloque de codigo va parar al sigueinte
                    synchronized(this){
                        instance = Room.databaseBuilder(context.applicationContext, AppDatabase::class.java, "acuenta.db")
                            .build()

                    }
            }
            // La instancia aqui ya no es nula porque ya fue creada
            // Llamando al metodo getInstance se crea la instancia
            return instance!!
        }

    }


}