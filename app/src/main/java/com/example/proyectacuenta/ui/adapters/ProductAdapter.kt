package com.example.proyectacuenta.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.proyectacuenta.data.models.Comment
import com.example.proyectacuenta.data.models.Product
import com.example.proyectacuenta.databinding.ItemProductBinding
import com.example.proyectacuenta.ui.listeners.onProductListener

// Un adaptador (Adapter) es un mecanismo de Android que hace de puente entre nuestros datos y las vistas contenidas en un ListView (o un GridView o Spinner).
// Heredan de adapter y recicleview
// Se recibe una lista de items (Productos)
// La clase adapter recibe un viewHolder que se creara como variable (atributo)
class ProductAdapter(var items: List<Product>): RecyclerView.Adapter<ProductAdapter.ViewHolder>() {

    // Se crea una variable de la interfaz onProductListener
    var listener: onProductListener? = null

    // Es una clase privada que recibe un item de producto que se creo ItemProduct xml
    // Se devuelve el RecyclerView el item.root
    class ViewHolder(val item: ItemProductBinding): RecyclerView.ViewHolder(item.root)

    // Como es una clase abtracta se implementan 3 metodos

    // Para crear un viewHolder se retorna un viewHolder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // Le estoy pasando un ItemProductBinding para crearlo
        return ViewHolder(
            ItemProductBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    // Es el metodo donde se recive la vista y la posicion del elemento que queremos pintar para ello se le asigna valores
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // Accedemos a nuestro elemento
        val item = items[position]
        // Se accede a cada atributo del elemento
        holder.item.itemProductName.text = item.name
        holder.item.itemProductPrice.text = item.price
        // Se debe instalar la libreria glide desde el gradle y nos permite descargar imagenes de internet
        // Se ajusta en el manifest los permisos
        Glide.with(holder.itemView).load(item.image).into(holder.item.itemProductPhoto)

        // Para detectar el click de un elemento de la grilla
        holder.item.root.setOnClickListener{
            // Se hace navegacion segura y le paso el item que se hizo click
            // En nuestro fragmento de productos a nuestro adaptador le pasamos el listener
            // Delega esta accion
            listener?.onClick(item)
        }
    }

    // Me indica cuantos elementos hay
    override fun getItemCount(): Int {
       return items.size
    }

    // Recibe una nueva lista de productos
    // Se le pasa una lista y me devuelve el numero de elementos
    fun newDataSet(newProducts: List<Product>) {
        // Se le indica al adaptador que los items cambiaron
        items = newProducts
        // Se llama otro metodo para pintar los datos y le indica al recyleview que los vuelva a pintar
        notifyDataSetChanged()
    }
}