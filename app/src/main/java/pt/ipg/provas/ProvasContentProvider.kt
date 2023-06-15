package pt.ipg.provas

import android.content.ContentProvider
import android.content.ContentValues
import android.content.UriMatcher
import android.database.Cursor
import android.net.Uri
import android.provider.BaseColumns

class ProvasContentProvider : ContentProvider() {
    private var bdOpenHelper : BDProvasOpenHelper?= null

    override fun onCreate(): Boolean {
        bdOpenHelper = BDProvasOpenHelper(context)
        return true
    }

    override fun query(
        uri: Uri,
        projection: Array<out String>?,
        selection: String?,
        selectionArgs: Array<out String>?,
        sortOrder: String?
    ): Cursor? {
        val bd = bdOpenHelper!!.readableDatabase
        val id = uri.lastPathSegment

        val endereco = uriMatcher().match(uri)

        val tabela = when (endereco){
            URI_PERCURSOS, URI_PERCURSO_ID -> TabelaPercursos(bd)
            URI_PROVAS, URI_PROVA_ID -> TabelaProvas(bd)
            else -> null
        }

        val (selecao, argsSel) = when (endereco) {
            URI_PERCURSO_ID, URI_PROVA_ID -> Pair("${BaseColumns._ID}=?", arrayOf(id))
            else -> Pair(selection, selectionArgs)
        }

        return tabela?.consulta(
            projection as Array<String>,
            selecao,
            argsSel as Array<String>?,
            null,
            null,
            sortOrder)
    }

    override fun getType(uri: Uri): String? {
        val endereco = uriMatcher().match(uri)

        return when (endereco){
            URI_PERCURSOS -> "vnd.android.cursor.dir/$PERCURSO"
            URI_PERCURSO_ID -> "vnd.android.cursor.item/$PERCURSO"
            URI_PROVAS -> "vnd.android.cursor.dir/$PROVAS"
            URI_PROVA_ID -> "vnd.android.cursor.item/$PROVAS"
            else -> null
        }
    }

    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        val bd = bdOpenHelper!!.writableDatabase

        val endereco = uriMatcher().match(uri)

        val tabela = when (endereco){
            URI_PERCURSOS -> TabelaPercursos(bd)
            URI_PROVAS -> TabelaProvas(bd)
            else -> return null
        }

        val id = tabela.insere(values!!)
        if (id == -1L) {
            return null
        }
        return Uri.withAppendedPath(uri, id.toString())
    }

    override fun delete(uri: Uri, values: String?, selection: Array<out String>?): Int {
        val bd = bdOpenHelper!!.writableDatabase

        val endereco = uriMatcher().match(uri)

        val tabela = when (endereco){
            URI_PERCURSO_ID -> TabelaPercursos(bd)
            URI_PROVA_ID -> TabelaProvas(bd)
            else -> return 0
        }

        val id = uri.lastPathSegment!!
        return tabela.elimina("${BaseColumns._ID}=?", arrayOf(id))
    }

    override fun update(uri: Uri, values: ContentValues?, selection: String?, selectionArgs: Array<out String>?): Int {
        val bd = bdOpenHelper!!.writableDatabase

        val endereco = uriMatcher().match(uri)

        val tabela = when (endereco){
            URI_PERCURSO_ID -> TabelaPercursos(bd)
            URI_PROVA_ID -> TabelaProvas(bd)
            else -> return 0
        }

        val id = uri.lastPathSegment!!
        return tabela.altera(values!!, "${BaseColumns._ID}=?", arrayOf(id))
    }

    companion object{
        private const val AUTORIDADE = "pt.ipg.provas"
        private const val PROVAS = "provas"
        private const val PERCURSO = "percurso"

        val ENDERECO_BASE = Uri.parse("content://$AUTORIDADE")

        val ENDERECO_PERCURSO = Uri.withAppendedPath(ENDERECO_BASE, PERCURSO)
        val ENDERECO_PROVAS = Uri.withAppendedPath(ENDERECO_BASE, PROVAS)

        private const val URI_PERCURSOS = 100
        private const val URI_PERCURSO_ID = 101
        private const val URI_PROVAS = 200
        private const val URI_PROVA_ID = 201

        fun uriMatcher() = UriMatcher(UriMatcher.NO_MATCH).apply {
            addURI(AUTORIDADE, PERCURSO, URI_PERCURSOS)
            addURI(AUTORIDADE, "$PERCURSO/#", URI_PERCURSO_ID)
            addURI(AUTORIDADE, PROVAS, URI_PROVAS)
            addURI(AUTORIDADE, "$PROVAS/#", URI_PROVA_ID)
        }
    }
}