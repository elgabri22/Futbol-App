import android.os.Bundle
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.navigation.fragment.findNavController
import com.example.proyecto.R
import com.example.proyecto.databinding.DialogRecuperaContrasenaBinding
import com.example.proyecto.databinding.DialogRegistroBinding
import com.google.firebase.auth.FirebaseAuth

class DialogoCambiaContrasena : DialogFragment() {
    private var _binding: DialogRecuperaContrasenaBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Initialize the binding after inflating the layout
        _binding = DialogRecuperaContrasenaBinding.inflate(inflater, container, false)

        // Now you can safely access views through binding
        val inputUsuario = binding.usuario
        val btncambia = binding.btncambia

        btncambia.setOnClickListener{
            val usuario = inputUsuario.editText?.text.toString()

            if (usuario.isNotEmpty()){
                if (usuario.isNotEmpty() && Patterns.EMAIL_ADDRESS.matcher(usuario).matches()){
                    enviarCorreoRecuperacion(usuario)
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

    private fun enviarCorreoRecuperacion(email: String) {
        // Verificar si el correo está registrado
        FirebaseAuth.getInstance().fetchSignInMethodsForEmail(email)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val result = task.result
                    // Si el correo no está registrado, result.signInMethods estará vacío
                    if (result?.signInMethods?.isEmpty() == true) {
                        // El correo no está registrado
                        Toast.makeText(requireContext(), "Este correo no está registrado", Toast.LENGTH_SHORT).show()
                    } else {
                        // El correo está registrado, procedemos a enviar el correo de recuperación
                        FirebaseAuth.getInstance().sendPasswordResetEmail(email)
                            .addOnCompleteListener { resetTask ->
                                if (resetTask.isSuccessful) {
                                    // Si el correo fue enviado con éxito
                                    Toast.makeText(requireContext(), "Correo de recuperación enviado", Toast.LENGTH_SHORT).show()
                                } else {
                                    // Si ocurrió un error al enviar el correo
                                    Toast.makeText(requireContext(), "Error al enviar el correo", Toast.LENGTH_SHORT).show()
                                }
                            }
                    }
                } else {
                    // Error al comprobar si el correo está registrado
                    Toast.makeText(requireContext(), "Error al verificar el correo", Toast.LENGTH_SHORT).show()
                }
            }
    }


}