package com.example.proyecto.ui.login

import DialogoCambiaContrasena
import DialogoCrearCuenta
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.provider.Settings.Global
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.proyecto.R
import com.example.proyecto.databinding.FragmentLoginBinding
import com.example.proyecto.ui.home.HomeFragment
import com.google.firebase.auth.FirebaseAuth

class  LoginFragment : Fragment() {

    companion object {
        fun newInstance() = LoginFragment()
    }

    object Global{
        var preferencias_compartidas="sharedpreferences"
    }

    // Usamos el ViewModel que ya está inicializado con `by viewModels()`
    private val viewModel: LoginViewModel by viewModels()

    // Referencia a _binding para acceder a las vistas
    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        verificaSesionAbierta()
        // Inflar el layout y obtener las vistas del binding
        _binding = FragmentLoginBinding.inflate(inflater, container, false)

        // Definir variables para las vistas del formulario
        val inputUsuario = binding.usuario
        val inputContrasena = binding.password
        val btnLogin = binding.btnLogin
        val btnRegistro= binding.btnRegistro
        val btnCambiarPassword=binding.btncambiapassword

        // ---------- LISTENERS ----------
        btnLogin.setOnClickListener {
            val usuario = inputUsuario.editText?.text.toString()
            val contrasena = inputContrasena.editText?.text.toString()

            if (contrasena.isNotEmpty()){
                if (usuario.isNotEmpty() && Patterns.EMAIL_ADDRESS.matcher(usuario).matches()){
                    login_firebase(usuario,contrasena)
                }else{
                    Toast.makeText(requireContext(), "Formato de correo incorrecto", Toast.LENGTH_LONG).show();
                }
            }else{
                Toast.makeText(requireContext(), "Escriba la contraseña", Toast.LENGTH_LONG).show();
            }
        }

        btnRegistro.setOnClickListener{
            DialogoCrearCuenta().show(childFragmentManager,null)
        }

        btnCambiarPassword.setOnClickListener{
            DialogoCambiaContrasena().show(childFragmentManager,null)
        }

        // Retornar la vista inflada
        return binding.root
    }

    private fun login_firebase(usuario: String, contrasena: String) {
        FirebaseAuth.getInstance().signInWithEmailAndPassword(usuario,contrasena)
            .addOnCompleteListener(requireActivity()) {task ->
                if (task.isSuccessful){
                    guardar_sesion(usuario,"firebase")
                    findNavController().navigate(R.id.action_loginFragment_to_MainMenu)
                }else{
                    Toast.makeText(requireContext(),"Error",Toast.LENGTH_LONG).show()
                }
            }
    }

    private fun verificaSesionAbierta() {
        // Obtén la instancia de SharedPreferences
        val sesion_abierta:SharedPreferences = requireContext().getSharedPreferences(Global.preferencias_compartidas, Context.MODE_PRIVATE)

        var correo=sesion_abierta.getString("Correo",null);
        var proveedor=sesion_abierta.getString("Proveedor",null);

        if (correo!=null && proveedor!=null){
            findNavController().navigate(R.id.action_loginFragment_to_MainMenu)
        }
    }



    fun guardar_sesion(correo:String,proveedor:String){
        val guardar_sesion:SharedPreferences.Editor = requireContext().getSharedPreferences(Global.preferencias_compartidas, Context.MODE_PRIVATE).edit()
        guardar_sesion.putString("Correo",correo)
        guardar_sesion.putString("Proveedor",proveedor)
        guardar_sesion.apply()
        guardar_sesion.commit()
    }




    override fun onDestroyView() {
        super.onDestroyView()
        // Limpiar el binding para evitar fugas de memoria
        _binding = null
    }
}
