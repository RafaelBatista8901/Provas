package pt.ipg.provas

import android.content.ContentValues
import android.database.Cursor
import android.provider.BaseColumns

data class Provas(
    var nomeProva: String,
    var localidade: String,
    var tipo: String,
    var data: String,
    var percursos: Percurso,
    var idProva: Long = -1
) {
    fun toContentValues(): ContentValues {
        val valores = ContentValues()

        valores.put(TabelaProvas.CAMPO_NOME_PROVA, nomeProva)
        valores.put(TabelaProvas.CAMPO_LOCALIDADE, localidade)
        valores.put(TabelaProvas.CAMPO_TIPO, tipo)
        valores.put(TabelaProvas.CAMPO_DATA, data)
        valores.put(TabelaProvas.CAMPO_FK_PERCURSOS, percursos.id)

        return valores
    }
    companion object{
        fun fromCursor(cursor: Cursor): Provas{
            val posId = cursor.getColumnIndex(BaseColumns._ID)
            val posNome = cursor.getColumnIndex(TabelaProvas.CAMPO_NOME_PROVA)
            val posLocalidade = cursor.getColumnIndex(TabelaProvas.CAMPO_LOCALIDADE)
            val posTipo = cursor.getColumnIndex(TabelaProvas.CAMPO_TIPO)
            val posData = cursor.getColumnIndex(TabelaProvas.CAMPO_DATA)
            val posFK_Percursos = cursor.getColumnIndex(TabelaProvas.CAMPO_FK_PERCURSOS)
            val posNomePercurso = cursor.getColumnIndex(TabelaProvas.CAMPO_NOME_PERCURSO)
            val posDistPercurso = cursor.getColumnIndex(TabelaProvas.CAMPO_DIST_PERCURSO)

            val id = cursor.getLong(posId)
            val nome = cursor.getString(posNome)
            val localidade = cursor.getString(posLocalidade)
            val tipo = cursor.getString(posTipo)
            val data = cursor.getString(posData)

            val percurso_id = cursor.getLong(posFK_Percursos)
            val nome_percurso = cursor.getString(posNomePercurso)
            val distancia_percurso = cursor.getString(posDistPercurso)

            return Provas(nome, localidade, tipo, data, Percurso(nome_percurso, distancia_percurso, percurso_id), id)
        }
    }
}