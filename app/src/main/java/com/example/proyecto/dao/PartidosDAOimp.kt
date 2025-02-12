package com.example.proyecto.dao

import com.example.proyecto.interfaces.PartidosDAO
import com.example.proyecto.models.Partido
import com.example.proyecto.object_models.Repository

class PartidosDAOimp private constructor(): PartidosDAO {
    companion object {
        val myDao: PartidosDAOimp by lazy{
            PartidosDAOimp()
        }
    }

    override fun getDataPartidos(): List<Partido> = Repository.listPartido

    override fun deletePartido(partido: Partido) {
        Repository.listPartido.remove(partido)
    }
}