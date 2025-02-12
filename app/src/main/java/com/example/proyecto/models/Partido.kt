package com.example.proyecto.models

class Partido(
    var id: Int,
    var visitante: String,
    var local: String,
    var resumen_partido: String,
    var imagen: String,
    var canal_emision: String,
    var horario: String
) {
    override fun toString(): String {
        return "Partido(id=$id, visitante='$visitante', local='$local', resumen_partido='$resumen_partido', imagen='$imagen', canal_emision='$canal_emision', horario='$horario')"
    }
}
