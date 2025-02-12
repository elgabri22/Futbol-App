package com.example.proyecto.ui.main_menu

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.proyecto.R
import com.example.proyecto.databinding.FragmentMainMenuBinding
import com.example.proyecto.ui.login.LoginFragment.Global

class MainMenuFragment : Fragment() {

    companion object {
        fun newInstance() = MainMenuFragment()
    }

    // Usamos el ViewModel que ya está inicializado con `by viewModels()`
    private val viewModel: MainMenuViewModel by viewModels()

    // Referencia a _binding para acceder a las vistas
    private var _binding: FragmentMainMenuBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // TODO: Usar el ViewModel si es necesario
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainMenuBinding.inflate(inflater, container, false)

        binding.btnPartidos.setOnClickListener {
            findNavController().navigate(R.id.action_mainmenu_to_partidos)
        }



        // Retornar la vista inflada
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        // Limpiar el binding para evitar fugas de memoria
        _binding = null
    }

}
