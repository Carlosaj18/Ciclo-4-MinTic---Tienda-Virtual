package com.example.proyectacuenta.data.repositories

import android.graphics.Bitmap
import com.example.proyectacuenta.utils.Constans
import com.google.firebase.auth.*
import com.google.firebase.auth.ktx.userProfileChangeRequest
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.StorageReference
import kotlinx.coroutines.tasks.await
import java.io.ByteArrayOutputStream

class TenderoRepository(
    private val dataSource: FirebaseAuth,
    private val dataSourceStorage: StorageReference,
    private val dataFireStore: FirebaseFirestore
    ) {

        suspend fun loggedInTendero(): FirebaseUser?{
            return dataSource.currentUser
        }

        suspend fun signUpTendero(id: String, nombres: String, apellidos: String, cc: String, celular: String,
                           email: String, password: String, store: String): FirebaseUser? {
            try {
                dataSource.createUserWithEmailAndPassword(email, password).await()
                val user = dataSource.currentUser
                val profileUpdate = userProfileChangeRequest {
                    displayName = nombres
                }
                user!!.updateProfile(profileUpdate).await()
                dataFireStore.collection(Constans.TENDERO_COLLECTION).document(user!!.uid).set(
                    hashMapOf(  "id" to user.uid,
                        "nombres" to nombres,
                        "apellidos" to apellidos,
                        "cc" to cc,
                        "celular" to celular,
                        "email" to email,
                        "password" to password,
                        "store" to store,
                    )
                ).await()
                return user
            } catch (e: FirebaseAuthUserCollisionException) {
                throw Error("No se pudo crear el objeto tendero")
            }
        }

        suspend fun logInTendero(email: String, password: String): FirebaseUser? {
            try {
                dataSource.signInWithEmailAndPassword(email, password).await()
                return dataSource.currentUser
            } catch (e: FirebaseAuthInvalidCredentialsException){
                throw Error("Credenciales invalidas")
            } catch (e: FirebaseAuthInvalidUserException) {
                throw Error("Usuario deshabilitado")
            }
        }

        suspend fun logOutTendero(): FirebaseUser? {
            dataSource.signOut()
            return null
        }

        suspend fun uploadImage(bitmap: Bitmap): FirebaseUser? {
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
