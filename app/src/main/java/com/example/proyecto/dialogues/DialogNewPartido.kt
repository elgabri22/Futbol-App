package com.example.proyecto_quizz_ericmacia.dialogues

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

class DialogNewPartido(
    val onNewPartidoDialog: (Partido) -> Unit
) : DialogFragment() {

    override fun onAttach(context: Context) {
        super.onAttach(context)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(requireContext()) // Usamos el contexto del fragmento
        val inflater = requireActivity().layoutInflater
        val viewDialogNewPartido = inflater.inflate(R.layout.dialog_new_partido, null)

        builder.setView(viewDialogNewPartido)
            .setPositiveButton("Añadir",
                DialogInterface.OnClickListener { dialog, id ->
                    val newPartido = recoverDataLayout(viewDialogNewPartido) as Partido
                    if (newPartido.local.isNullOrEmpty() ||
                        newPartido.resumen_partido.isNullOrEmpty() ||
                        newPartido.imagen.isNullOrEmpty() ||
                        newPartido.canal_emision.isNullOrEmpty()
                    ) {
                        Toast.makeText(context, "Campos vacíos detectados", Toast.LENGTH_LONG).show()
                        getDialog()?.cancel()
                    } else {
                        onNewPartidoDialog(newPartido)
                    }
                })
            .setNegativeButton("Cancelar",
                DialogInterface.OnClickListener { dialog, id -> getDialog()?.cancel() })
        return builder.create()
    }

    private fun recoverDataLayout(view: View): Any {
        val binding = DialogNewPartidoBinding.bind(view)
        return Partido(
            id= "",
            visitante = binding.etVisitante.text.toString(),
            local = binding.etLocal.text.toString(),
            resumen_partido = binding.etHorario.text.toString(),
            imagen = binding.etCanal.text.toString(),
            canal_emision = "gfgjjf",
            horario = binding.etHorario.text.toString(),
        )
    }
}