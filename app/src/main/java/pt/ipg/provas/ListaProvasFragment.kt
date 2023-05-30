package pt.ipg.provas

import android.database.Cursor
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.loader.app.LoaderManager
import androidx.loader.content.CursorLoader
import androidx.loader.content.Loader
import androidx.recyclerview.widget.LinearLayoutManager
import pt.ipg.provas.databinding.FragmentListaProvasBinding

private const val ID_LOADER_PROVAS = 0

class ListaProvasFragment : Fragment(), LoaderManager.LoaderCallbacks<Cursor>{
    private var _binding: FragmentListaProvasBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentListaProvasBinding.inflate(inflater, container, false)
        return binding.root

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_lista_provas, container, false)
    }

    private val adapterProvas: AdapterProvas? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapterProvas = AdapterProvas(this)
        binding.RecycleViewLivros.adapter = adapterProvas
        binding.RecycleViewLivros.layoutManager = LinearLayoutManager(requireContext())

        val loader = LoaderManager.getInstance(this)
        loader.initLoader(ID_LOADER_PROVAS, null, this)
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
            TabelaProvas.CAMPO_NOME)
    }

    override fun onLoaderReset(loader: Loader<Cursor>) {
        adapterProvas!!.cursor = null
    }

    override fun onLoadFinished(loader: Loader<Cursor>, data: Cursor?) {
        adapterProvas!!.cursor = data
    }
}