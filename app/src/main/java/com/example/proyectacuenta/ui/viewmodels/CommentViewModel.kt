package com.example.proyectacuenta.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.proyectacuenta.data.models.Comment
import com.example.proyectacuenta.data.models.Product
import com.example.proyectacuenta.data.models.StoreInfo
import com.example.proyectacuenta.data.repositories.CommentRepository
import com.example.proyectacuenta.data.repositories.StoreRepository
import kotlinx.coroutines.launch

// Para usar en la vista de comentarios detallados tambien necesito del storeRepository
// Revisar como se implementa el tema de los datos que se traen del repoStore
class CommentViewModel(private var repo: CommentRepository, private var repoStore: StoreRepository): ViewModel() {

    // Comment
    // Es un observable y como es para pushear la info tiene que ser de tipo MutableLiveData
    private var _comments: MutableLiveData<List<Comment>> = MutableLiveData()
    // Es un observable donde se observa la info y tiene que ser de tipo LiveData
    val comments: LiveData<List<Comment>> get() = _comments

    private var _selected: MutableLiveData<Comment> = MutableLiveData()
    val selected : LiveData<Comment> get() = _selected

    // Store
    private var _store: MutableLiveData<List<StoreInfo>> = MutableLiveData()
    val store: LiveData<List<StoreInfo>> get() = _store

    private var _storeSelected: MutableLiveData<StoreInfo> = MutableLiveData()
    val store_selected: LiveData<StoreInfo> get() = _storeSelected

    // Funcion para cargar los commentarios
    fun loadComments(){
        viewModelScope.launch {
            // Se llama al repo
            val result = repo.loadComments()
            // Se debe pushear la info al observable
            _comments.postValue(result)
        }
    }

    // Funcion que me permite seleccionar un producto
    // Recibe como parametro un producto
    fun selectComment(comment: Comment){
        // Toma nuestro observable y lo pushea
        _selected.postValue(comment)
        //  Esto lo va a escuchar en el productFragment
    }

    fun loadStore(){
        viewModelScope.launch {
            val result = repoStore.loadInfo()
            _store.postValue(result)
        }
    }

    fun selectStore(store: StoreInfo){
        // Toma nuestro observable y lo pushea
        _storeSelected.postValue(store)

    }
}

