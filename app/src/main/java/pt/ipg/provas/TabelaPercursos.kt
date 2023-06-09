package pt.ipg.provas

import android.database.sqlite.SQLiteDatabase
import android.provider.BaseColumns

class TabelaPercursos(db: SQLiteDatabase) : TabelaBD(db, NOME_TABELA) {
    override fun cria() {
        db.execSQL("CREATE TABLE $NOME_TABELA ($CHAVE_TABELA, $CAMPO_NOME_PERCURSO TEXT NOT NULL, $CAMPO_DISTANCIA INTEGER NOT NULL)")
    }

    companion object {
        const val NOME_TABELA = "percursos"

        const val CAMPO_ID = "${NOME_TABELA}.${BaseColumns._ID}"
        const val CAMPO_NOME_PERCURSO = "nome_percurso"
        const val CAMPO_DISTANCIA = "distancia"

        val CAMPOS = arrayOf(BaseColumns._ID, CAMPO_NOME_PERCURSO, CAMPO_DISTANCIA)

    }
}