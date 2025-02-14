package com.example.proyecto.interfaces

import com.example.proyecto.models.Partido


interface PartidosDAO {
    fun getDataPartidos(callback: Callback)
    fun deletePartido(partido: Partido)
}