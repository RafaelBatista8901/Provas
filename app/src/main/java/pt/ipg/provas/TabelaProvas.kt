package pt.ipg.provas

import android.database.sqlite.SQLiteDatabase
import android.provider.BaseColumns

class TabelaProvas(db: SQLiteDatabase) : TabelaBD(db, NOME_TABELA) {
    override fun cria() {
        db.execSQL("CREATE TABLE $NOME_TABELA ($CHAVE_TABELA, $CAMPO_NOME TEXT NOT NULL, $CAMPO_LOCALIDADE TEXT NOT NULL, $CAMPO_TIPO TEXT NOT NULL, $CAMPO_DATA TEXT NOT NULL, $CAMPO_FK_PERCURSOS INTEGER NOT NULL, FOREIGN KEY ($CAMPO_FK_PERCURSOS) REFERENCES ${TabelaPercursos.NOME_TABELA}(${BaseColumns._ID}) ON DELETE RESTRICT)")
    }

    companion object {
        const val NOME_TABELA = "Provas"
        const val CAMPO_NOME = "nome"
        const val CAMPO_LOCALIDADE = "localidade"
        const val CAMPO_TIPO = "tipo"
        const val CAMPO_DATA = "data"
        const val CAMPO_FK_PERCURSOS = "id_Percursos"

        val CAMPOS = arrayOf(BaseColumns._ID,
            TabelaProvas.CAMPO_NOME,
            TabelaProvas.CAMPO_LOCALIDADE,
            TabelaProvas.CAMPO_TIPO,
            TabelaProvas.CAMPO_DATA,
            TabelaProvas.CAMPO_FK_PERCURSOS
        )
    }
}
