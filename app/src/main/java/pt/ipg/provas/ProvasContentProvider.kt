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
            URI_PERCURSOS -> TabelaPercursos(bd)
            URI_PERCURSO_ID -> TabelaProvas(bd)
            URI_PROVAS -> TabelaProvas(bd)
            URI_PROVA_ID -> TabelaProvas(bd)
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

    override fun getType(p0: Uri): String? {
        TODO("Not yet implemented")
    }

    override fun insert(p0: Uri, p1: ContentValues?): Uri? {
        TODO("Not yet implemented")
    }

    override fun delete(p0: Uri, p1: String?, p2: Array<out String>?): Int {
        TODO("Not yet implemented")
    }

    override fun update(p0: Uri, p1: ContentValues?, p2: String?, p3: Array<out String>?): Int {
        TODO("Not yet implemented")
    }

    companion object{
        private const val AUTORIDADE = "pt.ipg.provas"
        const val PROVAS = "provas"
        const val PERCURSO = "percurso"

        private const val URI_PERCURSOS = 100
        private const val URI_PERCURSO_ID = 101
        private const val URI_PROVAS = 200
        private const val URI_PROVA_ID = 201

        fun uriMatcher() = UriMatcher(UriMatcher.NO_MATCH).apply {
            addURI(AUTORIDADE, PERCURSO, URI_PERCURSOS)
            addURI(AUTORIDADE, "$PERCURSO", URI_PERCURSOS)
            addURI(AUTORIDADE, PROVAS, URI_PROVAS)
            addURI(AUTORIDADE, "$PROVAS", URI_PROVAS)
        }
    }
}