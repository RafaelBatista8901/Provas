package pt.ipg.provas

import android.database.sqlite.SQLiteDatabase
import android.provider.BaseColumns

class TabelaProvas(db: SQLiteDatabase) : TabelaBD(db, NOME_TABELA) {
    override fun cria() {
        db.execSQL("CREATE TABLE $NOME_TABELA ($CHAVE_TABELA, nome TEXT NOT NULL, localidade TEXT NOT NULL, tipo TEXT NOT NULL, data DATE NOT NULL, id_Percursos INTEGER NOT NULL), FOREIGN KEY (id_Percursos) REFERENCES PERCURSOS ${TabelaPercursos.NOME_TABELA}(${BaseColumns._ID}) ON DELETE RESTRICT")
    }

    companion object {
        const val NOME_TABELA = "Provas"
    }
}
