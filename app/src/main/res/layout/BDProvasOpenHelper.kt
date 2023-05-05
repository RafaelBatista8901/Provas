package layout

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import pt.ipg.provas.TabelaBD
import pt.ipg.provas.TabelaCategorias
import pt.ipg.provas.TabelaProvas

private const val VERSAO_BADE_DADOS = 1

private const val NOME_BADE_DADOS = "Provas.db"

class BDProvas(
    context: Context?,
) : SQLiteOpenHelper(context, NOME_BADE_DADOS, null, VERSAO_BADE_DADOS) {
    override fun onCreate(db: SQLiteDatabase?) {
        requireNotNull(db)
        TabelaCategorias(db).cria()
        TabelaProvas(db).cria()
    }

    override fun onUpgrade(db: SQLiteDatabase?, OldVersion: Int, p2: Int) {

    }
}