package com.example.proyectacuenta.data.repositories

import android.graphics.Bitmap
import android.util.Log
import com.example.proyectacuenta.data.models.Product
import com.example.proyectacuenta.utils.Constans
import com.google.firebase.auth.*
import com.google.firebase.auth.ktx.userProfileChangeRequest
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.StorageReference
import kotlinx.coroutines.tasks.await
import org.koin.core.component.getScopeId
import java.io.ByteArrayOutputStream
import com.google.firebase.auth.FirebaseUser


class UserRepository(private val dataSource: FirebaseAuth,
                     private val dataSourceStorage: StorageReference,
                     private val dataFireStore: FirebaseFirestore
) {

    private val db: CollectionReference = dataFireStore.collection(Constans.USER_COLLECTION)

    // Metodos para el login
    // Metodo para saber si esta logeado
    suspend fun loggedIn(): FirebaseUser?{
        // Si esta logeado es diferente a null
        return dataSource.currentUser
    }

    suspend fun loadUser(): List<UserInfo> {
        val snapshot = dataFireStore.collection(Constans.USER_COLLECTION).get().await()
        return snapshot.toObjects((UserInfo::class.java))
    }

    suspend fun existeUser(currentUser: String): Boolean{
        val users = db.get().await()
        var esUser = false
        for (User in users){
            // Log.d("USER", "${User}, ${currentUser}")
            if(User.id == currentUser){
                esUser = true
                Log.d("USER", "Entro en el bloque de user in collecion")
            }
        }
        Log.e("USER", "El usuario es: ${esUser}")
        return esUser
    }

    // Metodo para hacer el signup
    // suspend fun signUp(email: String, name: String, password: String): FirebaseUser? {
    suspend fun signUp(id: String, nombres: String, apellidos: String, cc: String, celular: String,
                       email: String, password: String): FirebaseUser? {
        try {
            // Tomo el dataSource y llamo al metodo createUserWithEmailAndPassword
            dataSource.createUserWithEmailAndPassword(email, password).await()
            val user = dataSource.currentUser
            val profileUpdate = userProfileChangeRequest {
                displayName = nombres
            }
            user!!.updateProfile(profileUpdate).await()
            //dataFireStore.collection(Constans.USER_COLLECTION).document(user!!.uid).set(
            dataFireStore.collection(Constans.USER_COLLECTION).document(user!!.uid).set(
                hashMapOf(
                    "id" to user.uid,
                    "nombres" to nombres,
                    "apellidos" to apellidos,
                    "cc" to cc,
                    "celular" to celular,
                    "email" to email,
                    "password" to password,
                )
            ).await()
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