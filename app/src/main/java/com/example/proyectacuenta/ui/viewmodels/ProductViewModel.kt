package com.example.proyectacuenta.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.proyectacuenta.data.models.Product
import com.example.proyectacuenta.data.repositories.ProductRepository
import kotlinx.coroutines.launch

// Si necesito mas de un repositorio para traer la informacion lo pondria dentro del parametro de ProductViewModel (Se conoce como fachada).
// class ProductViewModel(private val repo:ProductRepository, private val repoStore: StoreRepository): ViewModel() {
class ProductViewModel(private val repo:ProductRepository): ViewModel() {

    // Observable
    // Creamos estas variables para que nuestras actividades o fragmentos vayan a pushear info sobre el observable
    // El unico que puede pushear info es su viewModel
    private var _products: MutableLiveData<List<Product>> = MutableLiveData()
    val products: LiveData<List<Product>> get() = _products

    // Se crea otro observable
    private var _selected: MutableLiveData<Product> = MutableLiveData()
    val selected : LiveData<Product> get() = _selected

    // Si quiero puedo pasarle solo un parametro para que me cargue una categoria se lo puedo pasar aqui!!!!
    fun loadProducts(){
        // El viewModel llama a su repositorio para que le de los resultados que necesita
        // Cuando tenga eso se lo pushea al observable
        // La gente que este suscrita escuchara
        viewModelScope.launch {
            // Se llama al repo
            val result = repo.loadProducts()
            // Se debe pushear la info al observable
            _products.postValue(result)
        }

    }

    // Funcion que me permite seleccionar un producto
    // Recibe como parametro un producto
    // Cuando se hizo click en el ProductFragment el metodo selectProducto se encargo de modificar el observable
    // El detalle del producto se encargara de observarlo
    fun selectProduct(product: Product){
        // Toma nuestro observable y lo pushea
        // Le dije a mi viewModel que acabo de seleccionar un item
        // Vot al fragmento
        _selected.postValue(product)
        //  Esto lo va a escuchar en el productFragment
    }

}