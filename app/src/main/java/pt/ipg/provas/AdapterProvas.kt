package pt.ipg.provas

import android.database.Cursor
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import java.util.PropertyResourceBundle

class AdapterProvas(val fragment: ListaProvasFragment) : RecyclerView.Adapter<AdapterProvas.ViewHolderProvas>() {
    var cursor: Cursor? = null
        set(value){
            field = value
            notifyDataSetChanged()
        }

    inner class ViewHolderProvas(contentor: View) : ViewHolder(contentor) {
        internal var provas: Provas
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderProvas {
        return ViewHolderProvas(
        fragment.layoutInflater.inflate(R.layout.item_prova, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return cursor?.count ?: 0
    }

    override fun onBindViewHolder(holder: ViewHolderProvas, position: Int) {
        cursor!!.move(position)
        holder.provas = Provas.fromCursor(cursor!!)
    }
}