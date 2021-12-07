package com.example.proyectacuenta.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.proyectacuenta.data.models.Product
import com.example.proyectacuenta.data.models.StoreInfo
import com.example.proyectacuenta.data.repositories.StoreRepository
import kotlinx.coroutines.launch

// Para que algo sea un viewModel debe hereder de viewModel
// El viewModel recibe al repositorio
// Para inyectar se debe crear un ViewModelModule en la carpeta de di
// Aqui se desarrolla la LOGICA

class StoreViewModel(private val repo: StoreRepository): ViewModel() {

    // Se crean dos variables
    // Como es una varible privada la sintaxis es asi -> _info
    // Que implica que la variable sea de tipo MutableLiveData? -> para crear los observables
    // Se inicializa el MutableLiveData para que pueda cambiar la informacion

    private var _info: MutableLiveData<List<StoreInfo>> = MutableLiveData()

    // Que es una variable de tipo LiveData?
    // Se va a poner la variable anterior en forma de getter pero va a ser de tipo LiveData
    // Se devuelve la variable _info
    // ¿Que es un observable?
    // A la variable info es la que se van a suscribir -> y por ello es el observable
    // val info: LiveData<StoreInfo> get() = _info
    // Esta variable es solo de tipo lectura y a esta variable es la que se van a suscribir (Es el observable)
    val info: LiveData<List<StoreInfo>> get() = _info

    // Se crea otro observable
    private var _selected: MutableLiveData<StoreInfo> = MutableLiveData()
    val selected : LiveData<StoreInfo> get() = _selected

    // Nuestra clase o fragmento llamaran a este metodo
    fun loadInfo() {
        // Como la funcion es asincronica se corre esa funcion en el scope del viewModel
        viewModelScope.launch {
            // Se llama al repo con las fuentes de datos
            val result = repo.loadInfo()
            // Se debe pushear la info al observable
            _info.postValue(result)
        }
    }

    // Funcion que me permite seleccionar un producto
    // Recibe como parametro un producto
    fun selectStore(storeInfo: StoreInfo){
        // Toma nuestro observable y lo pushea
        _selected.postValue(storeInfo)
        //  Esto lo va a escuchar en el productFragment
    }

}