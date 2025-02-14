package com.example.proyecto.controller

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.proyecto.dialogues.DialogEditPartido
import com.example.proyecto.models.Partido
import com.example.proyecto.repositories.Repository
import com.example.proyecto_quizz_ericmacia.dialogues.DialogNewPartido
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class Controller : ViewModel() {

    // LiveData para observar la lista de partidos
    private val _partidos = MutableLiveData<List<Partido>>()
    val partidos: LiveData<List<Partido>> get() = _partidos

    init {
        obtenerPartidos()
    }

    // Obtener la lista de partidos desde Firestore usando una corrutina
    private fun obtenerPartidos() {
        Repository.getPartidos{ lista ->
            _partidos.postValue(lista)
        }
    }

    // Agregar un nuevo partido
    fun agregarPartido(partido: Partido) {
        viewModelScope.launch {
            try {
                Repository.addPartido(partido)
                obtenerPartidos()
            } catch (e: Exception) {
                // Manejar el error (puedes registrar el error o mostrar un mensaje)
                println("Error al agregar partido: ${e.message}")
            }
        }
    }

    // Actualizar un partido existente
    fun actualizarPartido(partido: Partido) {
        viewModelScope.launch {
            try {
                Repository.updatePartido(partido)
                // Actualizar la lista de partidos después de la actualización
                obtenerPartidos()
            } catch (e: Exception) {
                // Manejar el error
                println("Error al actualizar partido: ${e.message}")
            }
        }
    }

    // Eliminar un partido
    fun eliminarPartido(partido: Partido) {
        viewModelScope.launch {
            try {
                Repository.deletePartido(partido.id)
                // Actualizar la lista de partidos después de la eliminación
                obtenerPartidos()
            } catch (e: Exception) {
                // Manejar el error
                println("Error al eliminar partido: ${e.message}")
            }
        }

    }
}
