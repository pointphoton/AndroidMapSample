package co.marti.challenge.ui.search

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import co.marti.challenge.R
import co.marti.challenge.databinding.FragmentSearchBinding
import co.marti.challenge.network.getNetworkService
import co.marti.challenge.network.model.detail.SearchDetail
import co.marti.challenge.network.model.place.SearchPlace
import co.marti.challenge.util.DebugLog

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class SearchFragment : Fragment(), SearchHandlers, SearchAdapterCallback {
    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: SearchViewModel
    private lateinit var repository: SearchRepository
    private lateinit var adapter: SearchDataAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_search, container, false)
        val view = binding.root
        binding.handlers = this
        binding.isSpinnerVisible = false
        initViews()
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        repository = SearchRepository(getNetworkService())
        viewModel = ViewModelProvider(
            this,
            SearchViewModel.FACTORY(repository)
        ).get(SearchViewModel::class.java)



        viewModel.place.observe(viewLifecycleOwner, Observer<SearchPlace> { value ->
            // Update the UI, in this case, a TextView.
            value?.let {
                DebugLog.write(it.status)
                adapter.setPredictionsList(it.predictions)
            }
        })
        viewModel.detail.observe(viewLifecycleOwner, Observer<SearchDetail> { value ->
            // Update the UI, in this case, a TextView.
            value?.let {
                DebugLog.write(it.status)
                val action=SearchFragmentDirections.actionSearchFragmentToMapShowFragment(it)
                findNavController().navigate(action)
            }
        })


        viewModel.spinner.observe(this.viewLifecycleOwner, Observer<Boolean> { value ->
            value?.let {

                binding.isSpinnerVisible=it

            }
        })

        // Show a snackbar whenever the [ViewModel.snackbar] is updated a non-null value
        viewModel.snackbar.observe(viewLifecycleOwner, Observer<String?> { text ->
            text?.let {
                DebugLog.write()
                Snackbar.make(binding.root, text, Snackbar.LENGTH_SHORT).show()
                viewModel.onSnackbarShown()
            }
        })

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    override fun onClickSearch(view: View) {
        // findNavController().navigate(R.id.action_SearchFragment_to_MapFragment)
        val text = binding.searchFrgToolbarEditTextSearch.text.toString();
        viewModel.searchWithName(text)
    }


    private fun initViews() {
        binding.searchFrgRecyclerV.layoutManager = LinearLayoutManager(context)
        binding.searchFrgRecyclerV.setHasFixedSize(true)
        adapter = SearchDataAdapter(callback = this)
        binding.searchFrgRecyclerV.adapter = adapter
    }

    override fun getSelectedPlace(placeId: String) {
        DebugLog.write("place id= $placeId")
        viewModel.searcPlaceDetail(placeId)


    }
}

interface SearchHandlers {
    fun onClickSearch(view: View)
}

interface SearchAdapterCallback {
    fun getSelectedPlace(placeId: String)
}