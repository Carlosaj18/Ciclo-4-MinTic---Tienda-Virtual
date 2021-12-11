package com.example.proyectacuenta.ui.viewmodels

import android.graphics.Bitmap
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.proyectacuenta.data.repositories.TenderoRepository
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.launch

class LoginTenderoViewModel(private val repo: TenderoRepository): ViewModel() {

    private var _tendero: MutableLiveData<FirebaseUser?> = MutableLiveData()
    val tendero: LiveData<FirebaseUser?> get() = _tendero

    private var _error: MutableLiveData<String> = MutableLiveData()
    val error: LiveData<String> get() = _error

    fun signUpTendero(id: String, nombres: String, apellidos: String, cc: String, celular: String,
               email: String, password: String, store: String) {
        viewModelScope.launch {
            try {
                _tendero.postValue(repo.signUpTendero(id, nombres, apellidos, cc, celular, email, password, store))
            } catch (e: Error) {
                _error.postValue(e.message)
            }
        }
    }

    fun loginTendero(email: String, pass: String) {
        viewModelScope.launch {
            try {
                _tendero.postValue(repo.logInTendero(email, pass))
            } catch (e: Error) {
                _error.postValue(e.message!!)
            }
        }
    }

    fun logOutTendero() {
        viewModelScope.launch {
            _tendero.postValue(repo.logOutTendero())
        }
    }

    fun loggedInTendero() {
        viewModelScope.launch {
            _tendero.postValue(repo.loggedInTendero())
        }
    }

    fun uploadImage(bitmap: Bitmap) {
        viewModelScope.launch {
            _tendero.postValue(repo.uploadImage(bitmap))
        }
    }
}