import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.proyecto.R
import com.example.proyecto.databinding.ItemPartidoBinding
import com.example.proyecto.models.Partido

class ViewHolderPartido(
    view: View,
    var deleteOnClick: (Partido) -> Unit, // Recibe un Partido
    var updateOnClick: (Partido) -> Unit  // Recibe un Partido
) : RecyclerView.ViewHolder(view) {

    private val binding: ItemPartidoBinding = ItemPartidoBinding.bind(view)

    fun renderize(partido: Partido) {
        val isDrawableResource = !partido.imagen.startsWith("http")

        if (isDrawableResource) {
            // Si la imagen es un recurso drawable, obtener su ID
            val imageId = itemView.context.resources.getIdentifier(
                partido.imagen, "drawable", itemView.context.packageName
            )

            if (imageId != 0) {
                Glide
                    .with(itemView.context)
                    .load(imageId)
                    .centerCrop()
                    .into(binding.ivTrivia)
            } else {
                Glide
                    .with(itemView.context)
                    .load(R.drawable.bg_futbol)
                    .into(binding.ivTrivia)
            }
        } else {
            // Si la imagen es una URL, usarla directamente
            Glide
                .with(itemView.context)
                .load(partido.imagen)
                .centerCrop()
                .into(binding.ivTrivia)
        }

        binding.txtviewTitle.setText(partido.local+" vs "+partido.visitante)
        binding.resumenPartido.setText(partido.resumen_partido)
        binding.canalEmision.setText(partido.canal_emision)
        binding.btnEdit.setOnClickListener {
            updateOnClick(partido) // Pasamos el partido completo
        }
        setOnClickListeners(partido)
    }

    private fun setOnClickListeners(partido: Partido) {
        // Botón de editar
        binding.btnEdit.setOnClickListener {
            updateOnClick(partido) // Pasamos el partido completo
        }

        // Botón de eliminar
        binding.btnDelete.setOnClickListener {
            deleteOnClick(partido) // Pasamos el partido completo
        }
    }
}

