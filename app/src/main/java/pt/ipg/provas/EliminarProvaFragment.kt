package pt.ipg.provas

import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import pt.ipg.provas.databinding.FragmentEliminarProvaBinding
class EliminarProvaFragment : Fragment() {
    private lateinit var provas: Provas
    private var _binding: FragmentEliminarProvaBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentEliminarProvaBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val activity = activity as MainActivity
        activity.fragment = this
        activity.IdMenuAtual = R.menu.menu_eliminar

        provas = EliminarProvaFragmentArgs.fromBundle(requireArguments()).prova

        binding.textViewNome2Prova.text = provas.nomeProva
        binding.textViewLocalidade2Prova.text = provas.localidade
        binding.textViewTipo2Prova.text = provas.tipo
        binding.textViewData2Prova.text = provas.data
        binding.textViewPercurso2Prova.text = provas.percursos.nomePercurso
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun processaOpcaoMenu(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_cancelar -> {
                voltaListaProvas()
                true
            }
            R.id.action_eliminar -> {
                eliminar()
                true
            }
            else -> false
        }
    }

    private fun voltaListaProvas() {
        findNavController().navigate(R.id.action_EliminarProvaFragment_to_ListaProvasFragment)
    }

    private fun eliminar() {
        val enderecoProva = Uri.withAppendedPath(ProvasContentProvider.ENDERECO_PROVAS, provas.idProva.toString())
        val numProvasEliminadas = requireActivity().contentResolver.delete(enderecoProva, null, null)

        if (numProvasEliminadas == 1) {
            Toast.makeText(requireContext(), getString(R.string.provas_eliminadas_com_sucesso), Toast.LENGTH_LONG).show()
            voltaListaProvas()
        } else {
            Toast.makeText(requireContext(), getString(R.string.erro_eliminar_prova), Toast.LENGTH_LONG).show()
        }
    }
}