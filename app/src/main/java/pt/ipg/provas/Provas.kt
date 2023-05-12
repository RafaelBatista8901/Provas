package pt.ipg.provas

import android.content.ContentValues
import android.database.AbstractCursor
import android.database.Cursor
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
}