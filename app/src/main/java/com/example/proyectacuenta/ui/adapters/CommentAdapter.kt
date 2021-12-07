package com.example.proyectacuenta.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.proyectacuenta.data.models.Comment
import com.example.proyectacuenta.databinding.ItemCommentBinding
import com.example.proyectacuenta.ui.listeners.OnCommentListener

class CommentAdapter(var items: List<Comment>): RecyclerView.Adapter<CommentAdapter.ViewHolder>() {

    // Interfaz
    var listener: OnCommentListener? = null

    class ViewHolder(val item: ItemCommentBinding): RecyclerView.ViewHolder(item.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemCommentBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.item.itemCommentName.text = item.author
        holder.item.itemCommentMessage.text = item.description
        holder.item.itemCommentDate.text = item.date
        Glide.with(holder.itemView).load(item.image).into(holder.item.itemCommentPhoto)
        holder.item.root.setOnClickListener{
            listener?.onClick(item)
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    // Recibe una nueva lista de comentarios
    fun newDataSet(newComments: List<Comment>) {
        // Se le indica al adaptador que los items cambiaron
        items = newComments
        // Se llama otro metodo para pintar los datos y le indica al recyleview que los vuelva a pintar
        notifyDataSetChanged()
    }
}