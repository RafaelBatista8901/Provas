package pt.ipg.provas

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

private const val VERSAO_BADE_DADOS = 1

class BDProvasOpenHelper(
    context: Context?,
) : SQLiteOpenHelper(context, NOME_BADE_DADOS, null, VERSAO_BADE_DADOS) {
    override fun onCreate(db: SQLiteDatabase?) {
        requireNotNull(db)
        TabelaPercursos(db).cria()
        TabelaProvas(db).cria()
    }

    override fun onUpgrade(db: SQLiteDatabase?, OldVersion: Int, newVersion: Int) {

    }

    companion object {
        const val NOME_BADE_DADOS = "Provas.db"
    }
}