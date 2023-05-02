package layout

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

private const val NOME_BASE_DADOS = "Provas.db"

private const val VERSAO_BASE_DADOS = 1

class BDProvas(
    context: Context?,
) : SQLiteOpenHelper(context, "Provas.db", null, 1) {
    override fun onCreate(p0: SQLiteDatabase?) {

    }

    override fun onUpgrade(p0: SQLiteDatabase?, OldVersion: Int, p2: Int) {

    }
}