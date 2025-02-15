package com.example.proyecto.ui.partidos_disponibles

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.proyecto.R
import com.example.proyecto.adapter.AdapterPartido
import com.example.proyecto.controller.Controller
import com.example.proyecto.databinding.FragmentPartidosDisponiblesBinding
import com.example.proyecto.dialogues.DialogEditPartido
import com.example.proyecto.models.Partido
import com.example.proyecto_quizz_ericmacia.dialogues.DialogNewPartido

class PartidosDisponiblesFragment : Fragment() {

    private val controller: Controller by viewModels()
    private lateinit var adapterPartido: AdapterPartido

    // Usamos el binding de manera segura
    private var _binding: FragmentPartidosDisponiblesBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflamos el layout correcto y asignamos el binding
        _binding = FragmentPartidosDisponiblesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Configurar RecyclerView
        adapterPartido = AdapterPartido(
            listPartidos = mutableListOf(), // Lista vacía de partidos
            deleteOnClick = { partido -> // Función para manejar la eliminación
                controller.eliminarPartido(partido) // Pasamos el partido completo
                Toast.makeText(requireContext(),"Partido borrado correctamente", Toast.LENGTH_LONG).show()
            },
            updateOnClick = { partido -> // Función para manejar la actualización
                DialogEditPartido(partido) { partidoActualizado ->
                    controller.actualizarPartido(partidoActualizado)
                }.show(parentFragmentManager, "editDialog")
            }
        )

        binding.myRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.myRecyclerView.adapter = adapterPartido

        controller.partidos.observe(viewLifecycleOwner) { lista ->
            adapterPartido.actualizarLista(lista)
        }

        // Observar datos de Firebase en tiempo real (si es necesario)
        /*controller.partidos.observe(viewLifecycleOwner) { lista ->
            adapterPartido.listPartidos = lista.toMutableList()
            adapterPartido.notifyDataSetChanged()
        }*/

        // Botón para agregar un nuevo elemento
        binding.btnAddPartido.setOnClickListener {
            DialogNewPartido(
                onNewPartidoDialog = { partido ->
                    controller.agregarPartido(partido)
                }
            ).show(parentFragmentManager, "NewPartidoDialog")
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        // Limpiamos el binding para evitar memory leaks
        _binding = null
    }
}