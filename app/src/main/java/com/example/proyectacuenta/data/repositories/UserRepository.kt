package com.example.proyectacuenta.data.repositories

import android.graphics.Bitmap
import com.google.firebase.auth.*
import com.google.firebase.auth.ktx.userProfileChangeRequest
import com.google.firebase.storage.StorageReference
import kotlinx.coroutines.tasks.await
import java.io.ByteArrayOutputStream

class UserRepository(private val dataSource: FirebaseAuth, private val dataSourceStorage: StorageReference ) {

    // Metodos para el login

    // Metodo para saber si esta logeado
    suspend fun loggedIn(): FirebaseUser?{
        // Si esta logeado es diferente a null
        return dataSource.currentUser
    }

    // Metodo para hacer el signup
    suspend fun signUp(email: String, name: String, password: String): FirebaseUser? {
        try {
            // Tomo el dataSource y llamo al metodo createUserWithEmailAndPassword
            dataSource.createUserWithEmailAndPassword(email, password).await()
            val user = dataSource.currentUser
            val profileUpdate = userProfileChangeRequest {
                displayName = name
            }
            user!!.updateProfile(profileUpdate).await()
            return user
        // Error si el usuario ya existe
        } catch (e: FirebaseAuthUserCollisionException) {
            throw Error("Correo electronico en uso")
        }
    }

    suspend fun logIn(email: String, password: String): FirebaseUser? {
        try {
            dataSource.signInWithEmailAndPassword(email, password).await()
            return dataSource.currentUser
        } catch (e: FirebaseAuthInvalidCredentialsException){
            throw Error("Credenciales invalidas")
        } catch (e: FirebaseAuthInvalidUserException) {
            throw Error("Usuario deshabilitado")
        }
    }

    suspend fun logOut(): FirebaseUser? {
        dataSource.signOut()
        return null
    }

    suspend fun uploadImage(bitmap: Bitmap): FirebaseUser? {
        // Se obtiene el arreglo de bytes
        // Se crea el objeto en el storage de firebase
        // Se sube la imagen
        // Se descarga la URL
        // Se actualiza el perfil del usuario

        val baos = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos)
        val data = baos.toByteArray()
        val user = dataSource.currentUser
        val profileRef = dataSourceStorage.child( "${user!!.uid}.jpg")
        profileRef.putBytes(data).await()
        val uri = profileRef.downloadUrl.await()
        val profileUpdate = userProfileChangeRequest {
            photoUri = uri
        }
        user.updateProfile(profileUpdate).await()
        return user
    }
}