package pt.ipg.provas

import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteQueryBuilder
import android.provider.BaseColumns

class TabelaProvas(db: SQLiteDatabase) : TabelaBD(db, NOME_TABELA) {
    override fun cria() {
        db.execSQL("CREATE TABLE $NOME_TABELA ($CHAVE_TABELA, $CAMPO_NOME_PROVA TEXT NOT NULL, $CAMPO_LOCALIDADE TEXT NOT NULL, $CAMPO_TIPO TEXT NOT NULL, $CAMPO_DATA TEXT NOT NULL, $CAMPO_FK_PERCURSOS INTEGER NOT NULL, FOREIGN KEY ($CAMPO_FK_PERCURSOS) REFERENCES ${TabelaPercursos.NOME_TABELA}(${BaseColumns._ID}) ON DELETE RESTRICT)")
    }
    override fun consulta(
        colunas: Array<String>,
        selecao: String?,
        argsSelecao: Array<String>?,
        groupby: String?,
        having: String?,
        orderby: String?
    ) : Cursor {
        val sql = SQLiteQueryBuilder()
        sql.tables = "$NOME_TABELA INNER JOIN ${TabelaPercursos.NOME_TABELA} ON ${TabelaPercursos.CAMPO_ID}=$CAMPO_FK_PERCURSOS"

        return sql.query(db, colunas, selecao, argsSelecao, groupby, having, orderby)
    }

    companion object {
        const val NOME_TABELA = "Provas"

        const val CAMPO_ID = "$NOME_TABELA.${BaseColumns._ID}"
        const val CAMPO_NOME_PROVA = "nome_prova"
        const val CAMPO_LOCALIDADE = "localidade"
        const val CAMPO_TIPO = "tipo"
        const val CAMPO_DATA = "data"
        const val CAMPO_FK_PERCURSOS = "id_Percursos"
        const val CAMPO_NOME_PERCURSO = TabelaPercursos.CAMPO_NOME_PERCURSO
        const val CAMPO_DIST_PERCURSO = TabelaPercursos.CAMPO_DISTANCIA

        val CAMPOS = arrayOf(CAMPO_ID,
            CAMPO_NOME_PROVA,
            CAMPO_LOCALIDADE,
            CAMPO_TIPO,
            CAMPO_DATA,
            CAMPO_FK_PERCURSOS,
            CAMPO_NOME_PERCURSO,
            CAMPO_DIST_PERCURSO)
    }
}
