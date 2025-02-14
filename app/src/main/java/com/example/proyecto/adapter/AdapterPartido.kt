package com.example.proyecto.adapter

import ViewHolderPartido
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.proyecto.R
import com.example.proyecto.models.Partido

class AdapterPartido(
    var listPartidos: MutableList<Partido>,
    var deleteOnClick: (Partido) -> Unit,
    var updateOnClick: (Partido) -> Unit
) : RecyclerView.Adapter<ViewHolderPartido>() {

    // Inflamos la vista de cada item y la pasamos al ViewHolder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderPartido {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ViewHolderPartido(
            layoutInflater.inflate(R.layout.item_partido, parent, false),
            deleteOnClick,
            updateOnClick
        )
    }

    // Vinculamos los datos de los partidos con el ViewHolder
    override fun onBindViewHolder(holder: ViewHolderPartido, position: Int) {
        val partido = listPartidos[position]
        holder.renderize(partido)
    }

    // Devuelve la cantidad de elementos en la lista
    override fun getItemCount(): Int = listPartidos.size

    // Método para actualizar la lista
    fun actualizarLista(nuevaLista: List<Partido>) {
        listPartidos.clear()
        listPartidos.addAll(nuevaLista)
        notifyDataSetChanged() // Notificar que los datos han cambiado
    }
}
