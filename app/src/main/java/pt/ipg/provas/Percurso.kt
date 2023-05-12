package pt.ipg.provas

import android.content.ContentValues
import android.database.Cursor
import android.provider.BaseColumns

data class Percurso(
    var nome: String,
    var distancia: Int,
    var id: Long = -1
){
    fun toContentValues(): ContentValues{
        val valores = ContentValues()

        valores.put(TabelaPercursos.CAMPO_NOME, nome)
        valores.put(TabelaPercursos.CAMPO_DISTANCIA, distancia)

        return valores
    }
    companion object{
        fun fromCursor(cursor: Cursor): Percurso{
            val posId = cursor.getColumnIndex(BaseColumns._ID)
            val posNome = cursor.getColumnIndex(TabelaPercursos.CAMPO_NOME)
            val posDistancia = cursor.getColumnIndex(TabelaPercursos.CAMPO_DISTANCIA)

            val nome = cursor.getString(posNome)
            val distancia = cursor.getInt(posDistancia)
            val id = cursor.getLong(posId)

            return Percurso(nome, distancia, id)
        }
    }
}