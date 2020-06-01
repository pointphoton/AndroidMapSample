package co.marti.challenge.ui.show

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import co.marti.challenge.R
import co.marti.challenge.network.model.detail.SearchDetail
import co.marti.challenge.util.DebugLog
import com.google.android.gms.maps.*
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class MapShowFragment : Fragment(), OnMapReadyCallback {

    fun MapShowFragment() {}


    private lateinit var mMap: GoogleMap
    private lateinit var place: SearchDetail

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        DebugLog.write()
        return inflater.inflate(R.layout.fragment_map_show, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        place = (arguments?.getSerializable("place") as? SearchDetail)!!
        if (requireActivity() != null) {
            val mapFragment =
                childFragmentManager.findFragmentById(R.id.map_show) as? SupportMapFragment
            mapFragment?.getMapAsync(this)
        }

    }

    override fun onMapReady(googleMap: GoogleMap?) {
        DebugLog.write(place?.result?.id.toString())
        mMap = googleMap!!
        mMap.setOnMarkerClickListener { marker ->

            marker.showInfoWindow()
            true
        }
        val loc = place.result.geometry.location
        val name = place.result.name
        // Add a marker in location and move the camera
        val pos = LatLng(loc.lat, loc.lng)
        mMap.addMarker(MarkerOptions().position(pos).title(name))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(pos))

    }

}