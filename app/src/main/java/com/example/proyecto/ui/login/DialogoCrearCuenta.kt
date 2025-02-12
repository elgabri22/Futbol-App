import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.navigation.fragment.findNavController
import com.example.proyecto.R
import com.example.proyecto.databinding.DialogRegistroBinding
import com.example.proyecto.ui.login.LoginFragment.Global
import com.google.firebase.auth.FirebaseAuth

class DialogoCrearCuenta : DialogFragment() {
    private var _binding: DialogRegistroBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Initialize the binding after inflating the layout
        _binding = DialogRegistroBinding.inflate(inflater, container, false)

        // Now you can safely access views through binding
        val inputUsuario = binding.usuario
        val inputContrasena = binding.password
        val btnRegistro = binding.btnRegistro

        // Definir los valores para la validación
        val user = "Usuario"
        val password = "12345"

        btnRegistro.setOnClickListener{
            val usuario = inputUsuario.editText?.text.toString()
            val contrasena = inputContrasena.editText?.text.toString()

            if (contrasena.isNotEmpty()){
                if (usuario.isNotEmpty() && Patterns.EMAIL_ADDRESS.matcher(usuario).matches()){
                    crearCuentaFirebase(usuario, contrasena)
                } else {
                    Toast.makeText(requireContext(), "Formato de correo incorrecto", Toast.LENGTH_LONG).show()
                }
            } else {
                Toast.makeText(requireContext(), "Escriba la contraseña", Toast.LENGTH_LONG).show()
            }
        }

        return binding.root // Use the root view from the binding
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null // Avoid memory leaks
    }

    private fun crearCuentaFirebase(usuario: String, contrasena: String) {
        // Obtener la instancia de FirebaseAuth
        val firebaseAuth = FirebaseAuth.getInstance()

        // Crear la cuenta con correo y contraseña
        firebaseAuth.createUserWithEmailAndPassword(usuario, contrasena)
            .addOnCompleteListener(requireActivity()) { task ->
                if (task.isSuccessful) {
                    // Obtener el usuario recién creado
                    val user = firebaseAuth.currentUser
                    showToast("Cuenta creada exitosamente.")
                } else {
                    // Mostrar mensaje de error si falla la creación de la cuenta
                    val errorMessage = task.exception?.localizedMessage ?: "Error desconocido"
                    showToast("Error al crear la cuenta: $errorMessage")
                }
            }
    }


    // Función auxiliar para mostrar Toast
    private fun showToast(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show()
    }

    fun guardar_sesion(correo:String,proveedor:String){
        val guardar_sesion: SharedPreferences.Editor = requireContext().getSharedPreferences(Global.preferencias_compartidas, Context.MODE_PRIVATE).edit()
        guardar_sesion.putString("Correo",correo)
        guardar_sesion.putString("Proveedor",proveedor)
        guardar_sesion.apply()
        guardar_sesion.commit()
    }

}
