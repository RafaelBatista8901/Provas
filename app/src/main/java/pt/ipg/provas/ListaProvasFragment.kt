package pt.ipg.provas

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import pt.ipg.provas.databinding.FragmentListaProvasBinding
import pt.ipg.provas.databinding.FragmentMenuPrincipalBinding

class ListaProvasFragment : Fragment() {
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
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_lista_provas, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapterProvas = AdapterProvas()
        binding.RecycleViewLivros.adapter = adapterProvas
        binding.RecycleViewLivros.layoutManager = LinearLayoutManager(requireContext())
    }

    companion object {

    }
}