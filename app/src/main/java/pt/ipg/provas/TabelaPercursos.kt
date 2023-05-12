package pt.ipg.provas

import android.database.sqlite.SQLiteDatabase

class TabelaPercursos(db: SQLiteDatabase) : TabelaBD(db, NOME_TABELA) {
    override fun cria() {
        db.execSQL("CREATE TABLE $NOME_TABELA ($CHAVE_TABELA, $CAMPO_NOME TEXT NOT NULL, distancia INTEGER NOT NULL)")
    }

    companion object {
        const val NOME_TABELA = "categorias"
        const val CAMPO_NOME = "nome"
    }
}