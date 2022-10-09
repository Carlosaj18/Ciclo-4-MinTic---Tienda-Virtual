package com.example.proyectacuenta.ui.viewmodels

import android.graphics.Bitmap
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.proyectacuenta.data.repositories.UserRepository
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.launch

class LoginViewModel(private val repo: UserRepository): ViewModel() {

    private var _user: MutableLiveData<FirebaseUser?> = MutableLiveData()
    val user: LiveData<FirebaseUser?> get() = _user

    private var _existe: MutableLiveData<Boolean> = MutableLiveData()
    val existe: LiveData<Boolean?> get() = _existe

    private var _error: MutableLiveData<String> = MutableLiveData()
    val error: LiveData<String> get() = _error

    fun signUp(id: String, nombres: String, apellidos: String, cc: String, celular: String,
               email: String, password: String) {
        viewModelScope.launch {
            try {
                _user.postValue(repo.signUp(id, nombres, apellidos, cc, celular, email, password))
            } catch (e: Error) {
                _error.postValue(e.message)
            }
        }
    }

    fun login(email: String, pass: String) {
        viewModelScope.launch {
            try {
                _user.postValue(repo.logIn(email, pass))
            } catch (e: Error) {
                _error.postValue(e.message!!)
            }
        }
    }

    fun logOut() {
        viewModelScope.launch {
            _user.postValue(repo.logOut())
        }
    }

    fun loggedIn() {
        viewModelScope.launch {
            _user.postValue(repo.loggedIn())
        }
    }

    fun uploadImage(bitmap: Bitmap) {
        viewModelScope.launch {
            _user.postValue(repo.uploadImage(bitmap))
        }
    }

    fun loadUser(){
        viewModelScope.launch {
            repo.loadUser()
        }
    }

    fun existeUser(currentUser: String) {
        viewModelScope.launch {
            // repo.existeUser(currentUser)
             _existe.postValue(repo.existeUser(currentUser))
        }
    }
}

