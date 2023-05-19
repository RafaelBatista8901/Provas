package pt.ipg.provas

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.provider.BaseColumns
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Before
import java.lang.NullPointerException

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {

    private fun getAppContext(): Context =
        InstrumentationRegistry.getInstrumentation().targetContext

    @Before
    fun apagaBaseDados(){
        getAppContext().deleteDatabase(BDProvasOpenHelper.NOME_BADE_DADOS)
    }

    @Test
    fun consegueAbrirBaseDados() {
        val openHelper = BDProvasOpenHelper(getAppContext())
        val bd = openHelper.readableDatabase
        assert(bd.isOpen)
    }
    @Test
    fun ConsegueInserirPercursos(){
        val bd = getWritableDatabase()

        val percurso = Percurso("Trail Curto", 10)
        inserePercursos(bd, percurso)
    }

    private fun inserePercursos(bd: SQLiteDatabase, percurso: Percurso) {
        percurso.id = TabelaPercursos(bd).insere(percurso.toContentValues())
        assertNotEquals(-1, percurso.id)
    }

    @Test
    fun ConsegueInserirProvas(){
        val bd = getWritableDatabase()

        val percurso = Percurso("Trail Longo", 40)
        inserePercursos(bd, percurso)

        val prova1 = Provas("24H Mem Martins", "Mem Martins", "BTT", "14/05/2023", percurso.id)
        insereProva(bd, prova1)

        val prova2 = Provas("VI Trail Trilhos do Ceireiro", "Beselga", "Trail", "14/05/2023", percurso.id)
        insereProva(bd, prova2)
    }

    @Test
    fun consegueLerPercurso() {
        val bd = getWritableDatabase()

        val percurso1 = Percurso("Maratona", 50)
        inserePercursos(bd, percurso1)

        val percurso2 = Percurso("Meia_Maratona", 25)
        inserePercursos(bd, percurso2)

        val tabelaPercursos = TabelaPercursos(bd)

        val cursor = tabelaPercursos.consulta(
            TabelaPercursos.CAMPOS, "${BaseColumns._ID}=?", arrayOf(percurso1.id.toString()),
            null,
            null,
            null
        )

        assert(cursor.moveToNext())
        val percursoBD = Percurso.fromCursor(cursor)

        assertEquals(percurso1, percursoBD)

        val cursorTodosPercursos = tabelaPercursos.consulta(
            TabelaPercursos.CAMPOS,
            null,
            null,
            null,
            null,
            TabelaPercursos.CAMPO_NOME
        )

        assert(cursorTodosPercursos.count > 1)
    }

    @Test
    fun consegueLerProvas(){
        val bd = getWritableDatabase()

        val percurso = Percurso("Mini-Maratona", 10)
        inserePercursos(bd, percurso)

        val prova1 = Provas("Assalto ao Cabeço das Fráguas", "Sabugal", "Trail", "21/05/2023", percurso.id)
        insereProva(bd, prova1)

        val prova2 = Provas("VII Trail Cidade de Estremoz", "Estremoz", "Trail", "22/05/2023", percurso.id)
        insereProva(bd, prova2)

        val tabelaProvas = TabelaProvas(bd)

        val cursor = tabelaProvas.consulta(
            TabelaProvas.CAMPOS, "${BaseColumns._ID}=?", arrayOf(prova1.id.toString()),
            null,
            null,
            null
        )

        assert(cursor.moveToNext())
        val provaBD = Provas.fromCursor(cursor)

        assertEquals(prova1, provaBD)

        val cursorTodosProvas = tabelaProvas.consulta(
            TabelaProvas.CAMPOS,
            null,
            null,
            null,
            null,
            TabelaProvas.CAMPO_NOME
        )

        assert(cursorTodosProvas.count > 1)
    }

    @Test
    fun consegueAlterarPercurso(){
        val bd = getWritableDatabase()

        val percurso = Percurso("Mini Trail", 75)
        inserePercursos(bd, percurso)

        percurso.nome = "Mega Trail"
        val registoAlterado = TabelaPercursos(bd).altera(percurso.toContentValues(), "${BaseColumns._ID}=?", arrayOf(percurso.id.toString()),)

        assertEquals(1, registoAlterado)
    }

    @Test
    fun consegueAlterarProva(){
        val bd = getWritableDatabase()

        val percurso = Percurso("Mini Trail", 75)
        inserePercursos(bd, percurso)

        val percurso2 = Percurso("Ultra Trail", 100)
        inserePercursos(bd, percurso2)

        val prova = Provas("7 Cidades Ultimate Trail", "São Miguel", "Trail", "20/05/2023", percurso.id)
        insereProva(bd, prova)

        prova.id_Percursos = percurso2.id
        prova.nome = "VII Trail Cidade de Estremoz"
        prova.data = "21/05/2023"

        val registosAlterados = TabelaProvas(bd).altera(prova.toContentValues(), "${BaseColumns._ID}=?", arrayOf(prova.id.toString()),)

        assertEquals(1, registosAlterados)
    }

    @Test
    fun consegueApagarPercurso(){
        val bd = getWritableDatabase()

        val percurso = Percurso("Mini Trail", 75)
        inserePercursos(bd, percurso)

        val registoEliminados = TabelaPercursos(bd).elimina("${BaseColumns._ID}=?", arrayOf(percurso.id.toString()),)

        assertEquals(1, registoEliminados)
    }

    @Test
    fun consegueApagarProvas(){
        val bd = getWritableDatabase()

        val percurso = Percurso("Mini Trail", 75)
        inserePercursos(bd, percurso)

        val prova = Provas("7 Cidades Ultimate Trail", "São Miguel", "Trail", "20/05/2023", percurso.id)
        insereProva(bd, prova)

        val registosEliminado = TabelaProvas(bd).elimina("${BaseColumns._ID}=?", arrayOf(prova.id.toString()),)

        assertEquals(1, registosEliminado)

    }

    private fun insereProva(bd: SQLiteDatabase, provas: Provas) {
        provas.id = TabelaProvas(bd).insere(provas.toContentValues())
        assertNotEquals(-1, provas.id)
    }

    private fun getWritableDatabase(): SQLiteDatabase {
        val openHelper = BDProvasOpenHelper(getAppContext())
        val bd = openHelper.writableDatabase
        return bd
    }
}