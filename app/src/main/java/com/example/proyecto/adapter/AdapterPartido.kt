package com.example.proyecto.adapter

import ViewHolderPartido
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.proyecto.R
import com.example.proyecto.models.Partido

class AdapterPartido(
    var listTrivia : MutableList<Partido>,
    var deleteOnClick : (Int) -> Unit,
    var updateOnClick : (Int) -> Unit
) : RecyclerView.Adapter<ViewHolderPartido>(){

    // Inflamos la vista de cada item y la pasamos al ViewHolder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderPartido {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ViewHolderPartido(
            layoutInflater.inflate(R.layout.item_partido, parent, false),
            deleteOnClick,
            updateOnClick
        )
    }

    // Vinculamos los datos de la trivia con el ViewHolder
    override fun onBindViewHolder(holder: ViewHolderPartido, position: Int) {
        holder.renderize(listTrivia[position])
    }

    // Devuelve la cantidad de elementos en la lista
    override fun getItemCount(): Int = listTrivia.size
}
