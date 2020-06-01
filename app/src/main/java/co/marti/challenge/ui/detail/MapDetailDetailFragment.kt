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
import co.marti.challenge.network.model.detail.SearchDetail
import com.bumptech.glide.Glide
import com.google.android.gms.maps.SupportMapFragment

/**
 * A simple [MapDetailDetailFragment] subclass as the third destination in the navigation.
 */
class MapDetailDetailFragment : Fragment(), MapDetailHandlers {

    private var _binding: FragmentMapDetailBinding? = null
    private val binding get() = _binding!!
    private lateinit var place: SearchDetail
    private val photoUrl =
        "https://maps.googleapis.com/maps/api/place/photo?maxwidth=400&photoreference=&sensor=false&key=AIzaSyCfTDvEQvE6GnJQbBjjuwy2WMDeFxnhKm4"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_map_detail, container, false)
        val view = binding.root
        binding.handlers = this
        return view

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        place = (arguments?.getSerializable("place2") as? SearchDetail)!!
        if (this::place.isInitialized) {
            binding.MapDetailAddress.text = place.result.formatted_address
            binding.MapDetailName.text = place.result.name
            place.result.photos?.let {
                val photoRef =it[0].photo_reference
                if (photoRef != null) {
                    val url =  "https://maps.googleapis.com/maps/api/place/photo?maxwidth=400&photoreference=$photoRef&sensor=false&key=AIzaSyCfTDvEQvE6GnJQbBjjuwy2WMDeFxnhKm4"
                    Glide.with(this)
                        .load(url)
                        .into(binding.MapDetailImage)
                }
            }

        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onBackClicked(view: View) {
        findNavController().navigate(R.id.action_MapDetailFragment_to_SearchFragment)
    }


}

interface MapDetailHandlers {
    fun onBackClicked(view: View);
}