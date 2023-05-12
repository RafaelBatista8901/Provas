package pt.ipg.provas

import android.content.ContentValues

data class Percurso(
    var nome: String,
    var distancia: Int,
    var id: Long = -1
){
    fun toContentValues(): ContentValues{
        val valores = ContentValues()

        valores.put(TabelaPercursos.CAMPO_NOME, nome)
        valores.put("distancia", distancia)

        return valores
    }
}