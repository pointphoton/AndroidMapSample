package co.marti.challenge.ui.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import co.marti.challenge.R
import co.marti.challenge.databinding.FragmentMapDetailBinding

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class MapShowFragment : Fragment() ,MapHandlers{

    private var _binding: FragmentMapDetailBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_map_detail, container, false)
        val view = binding.root
        binding.handlers = this
        //initViews()
        return view

    }

      override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onSelected(view: View) {
        //findNavController().navigate(R.id.action_MapFragment_to_SearchFragment)
    }


}

interface MapHandlers {

    fun onSelected(view: View);
}