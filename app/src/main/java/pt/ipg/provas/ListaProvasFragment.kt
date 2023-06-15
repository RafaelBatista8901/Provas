package pt.ipg.provas

import android.database.Cursor
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.loader.app.LoaderManager
import androidx.loader.content.CursorLoader
import androidx.loader.content.Loader
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import pt.ipg.provas.databinding.FragmentListaProvasBinding

private const val ID_LOADER_PROVAS = 0

class ListaProvasFragment : Fragment(), LoaderManager.LoaderCallbacks<Cursor>{
    private var _binding: FragmentListaProvasBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    var ProvaSelecionada : Provas? = null
        set (value){
            field = value

            val mostrarEliminarAlterar = (value != null)

            val activity = activity as MainActivity
            activity.mostrarOpcaoMenu(R.id.action_editar, mostrarEliminarAlterar)
            activity.mostrarOpcaoMenu(R.id.action_eliminar, mostrarEliminarAlterar)
        }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentListaProvasBinding.inflate(inflater, container, false)
        return binding.root
    }

    private var adapterProvas: AdapterProvas? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapterProvas = AdapterProvas(this)
        binding.RecycleViewLivros.adapter = adapterProvas
        binding.RecycleViewLivros.layoutManager = LinearLayoutManager(requireContext())

        val loader = LoaderManager.getInstance(this)
        loader.initLoader(ID_LOADER_PROVAS, null, this)

        val activity = activity as MainActivity
        activity.fragment = this
        activity.IdMenuAtual = R.menu.menu_lista_provas
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onCreateLoader(id: Int, args: Bundle?): Loader<Cursor> {
        return CursorLoader(requireContext(),
            ProvasContentProvider.ENDERECO_PROVAS,
            TabelaProvas.CAMPOS,
            null, null,
            TabelaProvas.CAMPO_NOME_PROVA)
    }

    override fun onLoaderReset(loader: Loader<Cursor>) {
        adapterProvas!!.cursor = null
    }

    override fun onLoadFinished(loader: Loader<Cursor>, data: Cursor?) {
        adapterProvas!!.cursor = data
    }

    fun processaOpcaoMenu(item: MenuItem) : Boolean {
        return when (item.itemId) {
            R.id.action_adicionar -> {
                adicionaProvas()
                true
            }
            R.id.action_editar -> {
                editarProvas()
                true
            }
            R.id.action_eliminar -> {
                eliminarProvas()
                true
            }
            else -> false
        }
    }

    private fun eliminarProvas() {
        val acao = ListaProvasFragmentDirections.actionListaProvasFragmentToEliminarProvaFragment(ProvaSelecionada!!)
        findNavController().navigate(acao)
    }

    private fun editarProvas() {
        val acao = ListaProvasFragmentDirections.actionListaProvasFragmentToEditarProvaFragment(ProvaSelecionada!!)
        findNavController().navigate(acao)
    }

    private fun adicionaProvas() {
        val acao = ListaProvasFragmentDirections.actionListaProvasFragmentToEditarProvaFragment(null)
        findNavController().navigate(acao)
    }
}