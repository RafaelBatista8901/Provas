package pt.ipg.provas

import android.content.Context
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Before

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
        val openHelper = BDProvasOpenHelper(getAppContext())
        val bd = openHelper.writableDatabase

        val percurso = Percurso("Trail Curto", 25,)

        TabelaPercursos(bd).insere()
    }
}