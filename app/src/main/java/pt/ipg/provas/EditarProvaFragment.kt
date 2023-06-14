package pt.ipg.provas

import android.database.Cursor
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.SimpleCursorAdapter
import android.widget.Toast
import androidx.loader.app.LoaderManager
import androidx.loader.content.CursorLoader
import androidx.loader.content.Loader
import androidx.navigation.fragment.findNavController
import pt.ipg.provas.databinding.FragmentNovaProvaBinding


private const val ID_LOADER_PERCURSOS = 0

class EditarProvaFragment : Fragment(), LoaderManager.LoaderCallbacks<Cursor> {
    private var _binding: FragmentNovaProvaBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentNovaProvaBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val loader = LoaderManager.getInstance(this)
        loader.initLoader(ID_LOADER_PERCURSOS, null, this)

        val activity = activity as MainActivity
        activity.fragment = this
        activity.IdMenuAtual = R.menu.menu_guardar_cancelar
        }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun processaOpcaoMenu(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_guardar -> {
                guardar()
                true
            }
            R.id.action_cancelar -> {
                cancelar()
                true
            }
            else -> false
        }
    }

    private fun cancelar() {
        findNavController().navigate(R.id.action_novaProvaFragment_to_ListaProvasFragment)
    }

    private fun guardar() {
        val nome = binding.editTextNome.text.toString()
        if (nome.isBlank()){
            binding.editTextNome.error = getString(R.string.nome_obrigatorio)
            binding.editTextNome.requestFocus()
            return
        }

        val percursosId = binding.spinnerPercursos.selectedItemId

        val localidade = binding.editTextLocalidade.text.toString()
        if (localidade.isBlank()){
            binding.editTextLocalidade.error = getString(R.string.localidade_obrigatorio)
            binding.editTextLocalidade.requestFocus()
            return
        }

        val tipo = binding.editTextTipo.text.toString()
        if (tipo.isBlank()){
            binding.editTextTipo.error = getString(R.string.tipo_obrigatorio)
            binding.editTextTipo.requestFocus()
            return
        }

        val data = binding.editTextData.text.toString()
        if (data.isBlank()){
            binding.editTextData.error = getString(R.string.data_obrigatorio)
            binding.editTextData.requestFocus()
            return
        }

        val prova = Provas(
            nome,
            localidade,
            tipo,
            data,
            Percurso("?", "?", percursosId)
        )

        val id = requireActivity().contentResolver.insert(
            ProvasContentProvider.ENDERECO_PROVAS,
            prova.toContentValues()
        )

        if (id == null) {
            binding.editTextNome.error = getString(R.string.erro_guardar_prova)
            return
        }

        Toast.makeText(requireContext(), getString(R.string.prova_guardado_com_sucesso), Toast.LENGTH_SHORT).show()
        cancelar()

    }

    override fun onCreateLoader(id: Int, args: Bundle?): Loader<Cursor> {
        return CursorLoader(requireContext(),
            ProvasContentProvider.ENDERECO_PERCURSO,
            TabelaPercursos.CAMPOS,
            null, null,
            TabelaPercursos.CAMPO_NOME_PERCURSO)
    }

    override fun onLoaderReset(loader: Loader<Cursor>) {
       binding.spinnerPercursos.adapter = null
    }

    override fun onLoadFinished(loader: Loader<Cursor>, data: Cursor?) {
        if (data == null){
            binding.spinnerPercursos.adapter = null
            return
        }

        binding.spinnerPercursos.adapter = SimpleCursorAdapter(
            requireContext(),
            android.R.layout.simple_list_item_1,
            data,
            arrayOf(TabelaPercursos.CAMPO_NOME_PERCURSO),
            intArrayOf(android.R.id.text1),
            0
        )
    }
}