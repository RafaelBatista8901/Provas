package pt.ipg.provas

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import pt.ipg.provas.databinding.FragmentNovaProvaBinding


class NovaProvaFragment : Fragment() {
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

            val activity = activity as MainActivity
            activity.fragment = this
            activity.IdMenuAtual = R.menu.menu_main
        }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}