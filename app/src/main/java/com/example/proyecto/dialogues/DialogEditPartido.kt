package com.example.proyecto.dialogues

import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.example.proyecto.R
import com.example.proyecto.databinding.DialogNewPartidoBinding
import com.example.proyecto.models.Partido
import com.example.proyecto_quizz_ericmacia.models.ArgumentsTrivia

class DialogEditPartido(
    val PartidoToUpdate: Partido,
    val nUpdatePartidoDialog: (Partido) -> Unit
) : DialogFragment() {

    val ARGUMENT_ID : String = ArgumentsTrivia.ARGUMENT_ID
    val ARGUMENT_LOCAL : String=ArgumentsTrivia.ARGUMENT_LOCAL
    val ARGUMENT_VISITANTE : String=ArgumentsTrivia.ARGUMENT_VISITANTE
    val ARGUMENT_IMAGEN : String=ArgumentsTrivia.ARGUMENT_IMAGEN
    val ARGUMENT_RESUMEN_PARTIDO: String=ArgumentsTrivia.ARGUMENT_RESUMEN_PARTIDO
    val ARGUMENT_CANAL_EMISION: String=ArgumentsTrivia.ARGUMENT_CANAL_EMISION
    val ARGUMENT_HORARIO : String=ArgumentsTrivia.ARGUMENT_HORARIO


    // Inicializa los argumentos para pasar a la vista del diálogo
    init {
        val args = Bundle().apply {
            putInt(ARGUMENT_ID, PartidoToUpdate.id)
            putString(ARGUMENT_RESUMEN_PARTIDO, PartidoToUpdate.resumen_partido)
            putString(ARGUMENT_LOCAL, PartidoToUpdate.local)
            putString(ARGUMENT_IMAGEN, PartidoToUpdate.imagen)
            putString(ARGUMENT_VISITANTE, PartidoToUpdate.visitante)
            putString(ARGUMENT_CANAL_EMISION,PartidoToUpdate.canal_emision)
            putString(ARGUMENT_HORARIO,PartidoToUpdate.horario)
        }
        this.arguments = args
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
    }

    // Crea el diálogo de edición de trivia
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(requireContext()) // Usamos el contexto del fragmento
        val inflater = requireActivity().layoutInflater
        val viewDialogEditTrivia = inflater.inflate(R.layout.dialog_new_partido, null)
        setValuesIntoDialog(viewDialogEditTrivia, this.arguments)

        builder.setView(viewDialogEditTrivia)
            .setPositiveButton("Aceptar",
                DialogInterface.OnClickListener { dialog, id ->
                    val updatePartido = recoverDataLayout(viewDialogEditTrivia) as Partido
                    if (updatePartido.resumen_partido.isNullOrEmpty() ||
                        updatePartido.local.isNullOrEmpty() ||
                        updatePartido.visitante.isNullOrEmpty()
                    ) {
                        Toast.makeText(context, "Campos vacíos detectados", Toast.LENGTH_LONG).show()
                        getDialog()?.cancel()
                    } else {
                        nUpdatePartidoDialog(updatePartido)
                    }
                })
            .setNegativeButton("Cancelar",
                DialogInterface.OnClickListener { dialog, id -> getDialog()?.cancel() })
        return builder.create()
    }

    private fun setValuesIntoDialog(viewDialogEditTrivia: View, arguments: Bundle?) {
        val binding = DialogNewPartidoBinding.bind(viewDialogEditTrivia)
        if (arguments != null) {
            binding.etLocal.setText(arguments.getString(ARGUMENT_LOCAL))
            binding.etHorario.setText(arguments.getString(ARGUMENT_HORARIO))
            binding.etVisitante.setText(arguments.getString(ARGUMENT_VISITANTE))
            binding.etCanal.setText(arguments.getString(ARGUMENT_CANAL_EMISION))
        }
    }

    private fun recoverDataLayout(view: View): Any {
        val binding = DialogNewPartidoBinding.bind(view)
        return Partido(
            id= 0,
            visitante = binding.etVisitante.text.toString(),
            local = binding.etLocal.text.toString(),
            resumen_partido = binding.etHorario.text.toString(),
            imagen = binding.etCanal.text.toString(),
            canal_emision = binding.etCanal.text.toString(),
            horario = binding.etHorario.text.toString(),
        )
    }
}
