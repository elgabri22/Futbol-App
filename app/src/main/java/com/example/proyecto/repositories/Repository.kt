package com.example.proyecto.repositories

import android.util.Log
import com.example.proyecto.models.Partido
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

object Repository {
    private val db = FirebaseFirestore.getInstance()
    private const val COLLECTION_NAME = "partidos"

    // Usamos corutinas en vez de callbacks para mejorar la consistencia y evitar los callbacks en el ViewModel
    fun getPartidos(callback: (List<Partido>) -> Unit) {
        db.collection(COLLECTION_NAME).get()
            .addOnSuccessListener { result ->
                val partidos = result.mapNotNull { it.toObject(Partido::class.java) }
                callback(partidos)
            }
            .addOnFailureListener { exception ->
                Log.e("Repository", "Error obteniendo datos", exception)
                callback(emptyList())
            }
    }

    suspend fun addPartido(partido: Partido): Boolean {
        return try {
            val documentRef = FirebaseFirestore.getInstance().collection("partidos").add(partido).await()
            documentRef.update("id", documentRef.id).await() // Guardar el ID en Firebase
            true
        } catch (e: Exception) {
            Log.e("Repository", "Error añadiendo partido", e)
            false
        }
    }

    suspend fun updatePartido(partido: Partido): Boolean {
        return try {
            if (partido.id.isNotBlank()) {
                Log.e("A","NO")
                FirebaseFirestore.getInstance().collection("/partidos").document(partido.id).set(partido).await()
                true
            } else {
                Log.e("Repository", "ID del partido no es válido para actualizar")
                false
            }
        } catch (e: Exception) {
            Log.e("Repository", "Error actualizando partido", e)
            false
        }
    }

    suspend fun deletePartido(id: String): Boolean {
        return try {
            if (id.isNotBlank()) {
                FirebaseFirestore.getInstance().collection("/partidos").document(id).delete().await()
                true
            } else {
                Log.e("Repository", "ID del partido no es válido para eliminar")
                false
            }
        } catch (e: Exception) {
            Log.e("Repository", "Error eliminando partido", e)
            false
        }
    }
}

