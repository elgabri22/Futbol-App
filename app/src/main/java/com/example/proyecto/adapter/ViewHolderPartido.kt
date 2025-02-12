import android.app.AlertDialog
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.proyecto.R
import com.example.proyecto.databinding.ItemPartidoBinding
import com.example.proyecto.models.Partido

class ViewHolderPartido(
    view: View,
    var deleteOnClick: (Int) -> Unit,
    var updateOnClick: (Int) -> Unit
) : RecyclerView.ViewHolder(view) {

    var binding: ItemPartidoBinding = ItemPartidoBinding.bind(view)

    fun renderize(partido: Partido) {

        binding.resumenPartido.setText(partido.resumen_partido)
        binding.canalEmision.setText(partido.canal_emision)

        setOnClickListener(adapterPosition)

        // Eliminar partido
        binding.btnDelete.setOnClickListener {
            deleteOnClick(adapterPosition)
        }
    }
    private fun setOnClickListener(position : Int){
        binding.btnEdit.setOnClickListener {
            updateOnClick(position)
        }
        binding.btnDelete.setOnClickListener {
            deleteOnClick(position)
        }
    }
}

