package pt.ipg.provas

import android.content.ContentValues
import android.database.AbstractCursor
import android.database.Cursor
import android.provider.BaseColumns
import java.util.Date

data class Provas(
    var nome: String,
    var localidade: String,
    var tipo: String,
    var data: String,
    var id_Percursos: Long,
    var id: Long = -1
) {
    fun toContentValues(): ContentValues {
        val valores = ContentValues()

        valores.put(TabelaProvas.CAMPO_NOME, nome)
        valores.put(TabelaProvas.CAMPO_LOCALIDADE, localidade)
        valores.put(TabelaProvas.CAMPO_TIPO, tipo)
        valores.put(TabelaProvas.CAMPO_DATA, data)
        valores.put(TabelaProvas.CAMPO_FK_PERCURSOS, id_Percursos)

        return valores
    }
    companion object{
        fun fromCursor(cursor: Cursor): Provas{
            val posId = cursor.getColumnIndex(BaseColumns._ID)
            val posNome = cursor.getColumnIndex(TabelaProvas.CAMPO_NOME)
            val posLocalidade = cursor.getColumnIndex(TabelaProvas.CAMPO_LOCALIDADE)
            val posTipo = cursor.getColumnIndex(TabelaProvas.CAMPO_TIPO)
            val posData = cursor.getColumnIndex(TabelaProvas.CAMPO_DATA)
            val posFK_Percursos = cursor.getColumnIndex(TabelaProvas.CAMPO_FK_PERCURSOS)

            val nome = cursor.getString(posNome)
            val localidade = cursor.getString(posLocalidade)
            val tipo = cursor.getString(posTipo)
            val data = cursor.getString(posData)
            val percurso_Id = cursor.getLong(posFK_Percursos)
            val id = cursor.getLong(posId)

            return Provas(nome, localidade, tipo, data, percurso_Id, id)
        }
    }
}