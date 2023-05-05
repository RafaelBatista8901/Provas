package layout

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

private const val VERSAO_BADE_DADOS = 1

private const val NOME_BADE_DADOS = "Provas.db"

class BDProvas(
    context: Context?,
) : SQLiteOpenHelper(context, "Provas.db", null, 1) {
    override fun onCreate(db: SQLiteDatabase?) {

    }

    override fun onUpgrade(db: SQLiteDatabase?, OldVersion: Int, p2: Int) {

    }
}