package com.example.proyecto.controller

import android.content.Context
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.proyecto.adapter.AdapterPartido
import com.example.proyecto.dao.PartidosDAOimp
import com.example.proyecto.dialogues.DialogDeletePartido
import com.example.proyecto.dialogues.DialogEditPartido
import com.example.proyecto.models.Partido
import com.example.proyecto.ui.partidos_disponibles.PartidosDisponiblesFragment
import com.example.proyecto_quizz_ericmacia.dialogues.DialogNewPartido

class Controller(
    val context: Context,
    val contextFragment: PartidosDisponiblesFragment
) {
    lateinit var adapterPartido: AdapterPartido
    lateinit var layoutManager: LinearLayoutManager
    lateinit var listPartidos: MutableList<Partido>

    init {
        initData()
    }

    // Inicializa el layoutManager y la lista de trivias
    fun initData() {
        setScrollWithOffsetLinearLayout()
        listPartidos = PartidosDAOimp.myDao.getDataPartidos().toMutableList()
        initOnClickListener()
    }

    // Establece el comportamiento del layoutManager con desplazamiento
    private fun setScrollWithOffsetLinearLayout() {
        layoutManager = contextFragment.bindingFragment.myRecyclerView.layoutManager as LinearLayoutManager
    }

    // Inicializa el listener para el botón de agregar trivia
    private fun initOnClickListener() {
        contextFragment.bindingFragment.btnAddPartido.setOnClickListener {
            addTrivia()
        }
    }

    // Configura el adaptador del RecyclerView
    fun setAdapter() {
        adapterPartido = AdapterPartido(
            listPartidos,
            { pos -> delTrivia(pos) },
            { pos -> updateTrivia(pos) }
        )
        contextFragment.bindingFragment.myRecyclerView.adapter = adapterPartido
    }

    fun updateTrivia(pos: Int) {
        val editDialog = DialogEditPartido(listPartidos[pos]) {
                editTrivia -> okOnEditContext(editTrivia, pos)
        }
        contextFragment.parentFragmentManager.let { editDialog.show(it, "Editamos una Trivia") }
    }

    fun addTrivia() {
        Toast.makeText(context, "Añadiremos un nuevo partido", Toast.LENGTH_LONG).show()
        val dialog = DialogNewPartido { partido -> okOnNewPartido(partido) }
        contextFragment.parentFragmentManager.let { dialog.show(it, "Añadimos una nueva Trivia") }
    }

    fun delTrivia(pos: Int) {
        val dialog = DialogDeletePartido(
            pos,
            listPartidos[pos].resumen_partido
        ) { position -> okOnDeleteTrivia(position) }

        contextFragment.parentFragmentManager.let { dialog.show(it, "Borraremos la trivia de posición $pos") }
    }

    private fun okOnDeleteTrivia(pos: Int) {
        listPartidos.removeAt(pos)
        adapterPartido.notifyItemRemoved(pos)
        Toast.makeText(context, "Trivia borrada", Toast.LENGTH_LONG).show()
    }

    private fun okOnNewPartido(partido: Partido) {
        listPartidos.add(listPartidos.size, partido)
        adapterPartido.notifyItemInserted(listPartidos.lastIndex)
        layoutManager.scrollToPositionWithOffset(listPartidos.lastIndex, 20)
    }

    private fun okOnEditContext(editPartido: Partido, pos: Int) {
        listPartidos.removeAt(pos)
        adapterPartido.notifyItemRemoved(pos)
        listPartidos.add(pos, editPartido)
        adapterPartido.notifyItemInserted(pos)
        layoutManager.scrollToPositionWithOffset(pos, 20)
    }
}
