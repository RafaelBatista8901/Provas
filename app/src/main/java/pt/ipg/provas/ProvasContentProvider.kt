package pt.ipg.provas

import android.content.ContentProvider
import android.content.ContentValues
import android.content.UriMatcher
import android.database.Cursor
import android.net.Uri

class ProvasContentProvider : ContentProvider() {
    private var bdOpenHelper : BDProvasOpenHelper?= null

    override fun onCreate(): Boolean {
        bdOpenHelper = BDProvasOpenHelper(context)
        return true
    }

    override fun query(
        p0: Uri,
        p1: Array<out String>?,
        p2: String?,
        p3: Array<out String>?,
        p4: String?
    ): Cursor? {
        TODO("Not yet implemented")
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
        private const val URI_PROVAS = 200
        fun uriMatcher() = UriMatcher(UriMatcher.NO_MATCH).apply {
            addURI(AUTORIDADE, PERCURSO, URI_PERCURSOS)
            addURI(AUTORIDADE, PROVAS, URI_PROVAS)
        }
    }
}