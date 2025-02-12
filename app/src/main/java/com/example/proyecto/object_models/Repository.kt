package com.example.proyecto.object_models

import com.example.proyecto.models.Partido


object Repository {
    val listPartido: MutableList<Partido> = mutableListOf(
        Partido(
            id = 1,
            visitante = "Real Madrid",
            local = "Barcelona",
            resumen_partido = "Un clásico emocionante con muchas oportunidades.",
            imagen = "real_madrid_vs_barcelona.jpg",
            canal_emision = "Canal+",
            horario= "20:00"
        ),
        Partido(
            id = 2,
            visitante = "Atletico Madrid",
            local = "Sevilla",
            resumen_partido = "Partido reñido con goles en el último minuto.",
            imagen = "atletico_vs_sevilla.jpg",
            canal_emision = "Movistar",
            horario= "20:00"
        ),
        Partido(
            id = 3,
            visitante = "Valencia",
            local = "Villarreal",
            resumen_partido = "Empate sin goles, pero mucha acción en el campo.",
            imagen = "valencia_vs_villarreal.jpg",
            canal_emision = "DAZN",
            horario= "20:00"
        ),
        Partido(
            id = 4,
            visitante = "Betis",
            local = "Real Sociedad",
            resumen_partido = "Victoria aplastante del equipo local.",
            imagen = "betis_vs_real_sociedad.jpg",
            canal_emision = "GolTV",
            horario= "20:00"
        ),
        Partido(
            id = 5,
            visitante = "Espanyol",
            local = "Mallorca",
            resumen_partido = "Ambos equipos mostraron una defensa sólida.",
            imagen = "espanyol_vs_mallorca.jpg",
            canal_emision = "ESPN",
            horario= "20:00"
        ),
        Partido(
            id = 6,
            visitante = "Granada",
            local = "Celta de Vigo",
            resumen_partido = "Un partido lleno de emoción y goles inesperados.",
            imagen = "granada_vs_celta.jpg",
            canal_emision = "TNT Sports",
            horario= "20:00"
        ),
        Partido(
            id = 7,
            visitante = "Getafe",
            local = "Osasuna",
            resumen_partido = "Gran actuación del portero visitante.",
            imagen = "getafe_vs_osasuna.jpg",
            canal_emision = "LaLigaTV",
            horario= "20:00"
        ),
        Partido(
            id = 8,
            visitante = "Almería",
            local = "Rayo Vallecano",
            resumen_partido = "Un partido con muchas faltas pero bien controlado.",
            imagen = "almeria_vs_rayo.jpg",
            canal_emision = "Sky Sports",
            horario= "20:00"
        ),
        Partido(
            id = 9,
            visitante = "Athletic Bilbao",
            local = "Cadiz",
            resumen_partido = "El equipo visitante sorprendió con una victoria inesperada.",
            imagen = "bilbao_vs_cadiz.jpg",
            canal_emision = "Amazon Prime",
            horario= "20:00"
        ),
        Partido(
            id = 10,
            visitante = "Elche",
            local = "Levante",
            resumen_partido = "Partido muy igualado con empate final.",
            imagen = "elche_vs_levante.jpg",
            canal_emision = "FOX Sports",
            horario= "20:00"
        )
    )
}
