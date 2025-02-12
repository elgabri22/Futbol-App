package com.example.proyecto.interfaces

import com.example.proyecto.models.Partido


interface PartidosDAO {
    fun getDataPartidos(): List<Partido>
    fun deletePartido(partido: Partido)
}