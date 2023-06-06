package pt.ipg.provas

import android.database.Cursor
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder

class AdapterProvas(val fragment: ListaProvasFragment) : RecyclerView.Adapter<AdapterProvas.ViewHolderProvas>() {
    var cursor: Cursor? = null
        set(value){
            field = value
            notifyDataSetChanged()
        }

    inner class ViewHolderProvas(contentor: View) : ViewHolder(contentor) {
        private val textViewNome = contentor.findViewById<TextView>(R.id.textView_nome)
        private val textViewPercurso = contentor.findViewById<TextView>(R.id.textView_Percurso)

        init {
            contentor.setOnClickListener {
                viewHolderSeleccionado?.desSeleciona()
                seleciona()
            }
        }

        internal var provas: Provas? = null
            set(value){
                field = value
                textViewNome.text = provas?.nome ?: ""
                textViewPercurso.text = provas?.percursos?.nome ?: ""
            }
        fun seleciona() {
            viewHolderSeleccionado = this
            fragment.ProvaSelecionada = provas
            itemView.setBackgroundResource(R.color.item_selecionado)
        }

        fun desSeleciona() {
            itemView.setBackgroundResource(android.R.color.white)
        }
    }

    private var viewHolderSeleccionado : ViewHolderProvas? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderProvas {
        return ViewHolderProvas(
        fragment.layoutInflater.inflate(R.layout.item_prova, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return cursor?.count ?: 0
    }

    override fun onBindViewHolder(holder: ViewHolderProvas, position: Int) {
        cursor!!.moveToPosition(position)
        holder.provas = Provas.fromCursor(cursor!!)
    }
}