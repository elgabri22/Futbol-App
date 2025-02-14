package com.example.proyecto.interfaces
import com.example.proyecto.models.Partido

interface Callback {
    fun onDataLoaded(partidos: List<Partido>)
}