package com.example.proyecto.ui.partidos_disponibles

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.proyecto.R
import com.example.proyecto.controller.Controller
import com.example.proyecto.databinding.FragmentPartidosDisponiblesBinding

class PartidosDisponiblesFragment : Fragment() {

    lateinit var bindingFragment: FragmentPartidosDisponiblesBinding
    lateinit var controller: Controller


    /*private val viewModel: PartidosDisponiblesViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // TODO: Use the ViewModel
    }*/

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        bindingFragment = FragmentPartidosDisponiblesBinding.inflate(inflater, container, false)

        // Inicializa el RecyclerView y el Controller
        initRecyclerView()
        // Crea el controlador y pasa el contexto y el fragmento
        controller = Controller(requireContext(), this)
        controller.setAdapter()

        return bindingFragment.root
    }

    private fun initRecyclerView() {
        // Configura el RecyclerView en el fragmento
        bindingFragment.myRecyclerView.layoutManager =
            LinearLayoutManager(context)
    }
}