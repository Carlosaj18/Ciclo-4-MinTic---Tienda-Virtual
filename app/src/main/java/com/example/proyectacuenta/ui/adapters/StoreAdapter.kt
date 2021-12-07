package com.example.proyectacuenta.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.proyectacuenta.data.models.StoreInfo
import com.example.proyectacuenta.databinding.ItemStoreBinding
import com.example.proyectacuenta.ui.listeners.OnStoreListener

class StoreAdapter(var items: List<StoreInfo>): RecyclerView.Adapter<StoreAdapter.ViewHolder>(){

    var listener: OnStoreListener? = null

    // Se conecta al layout store xml que se creo
    class ViewHolder(val item: ItemStoreBinding): RecyclerView.ViewHolder(item.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return StoreAdapter.ViewHolder(
            ItemStoreBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    // Recivimos nuestra vista y la posicion del elemento
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.item.itemStoreName.text = item.name
        holder.item.itemDescriptionStoreMessage.text = item.description
        holder.item.itemStorePhone.text = item.phone
        holder.item.itemCommentDireccion.text = item.address
        Glide.with(holder.itemView).load(item.image).into(holder.item.itemStorePhoto)
        holder.item.root.setOnClickListener{
            listener?.onClick(item)
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun newDataSet(newStore: List<StoreInfo>) {
        // Se le indica al adaptador que los items cambiaron
        items = newStore
        // Se llama otro metodo para pintar los datos y le indica al recyleview que los vuelva a pintar
        notifyDataSetChanged()
    }

}




