package co.marti.challenge.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.marti.challenge.network.model.detail.SearchDetail
import co.marti.challenge.network.model.place.SearchPlace
import co.marti.challenge.util.singleArgViewModelFactory
import kotlinx.coroutines.launch


class SearchViewModel(private val repository: SearchRepository) : ViewModel() {


    companion object {

        val FACTORY = singleArgViewModelFactory(::SearchViewModel)
    }


    /**
     * Get places via this LiveData
     */
    val place: LiveData<SearchPlace> = repository.placeLD


    /**
     * Get place details via this LiveData
     */
    val detail: LiveData<SearchDetail> = repository.detailLD

    /**
     * Request a snackbar to display a string.
     *
     * This variable is private because we don't want to expose MutableLiveData
     *
     * MutableLiveData allows anyone to set a value, and SearchViewModel is the only
     * class that should be setting values.
     */
    private val _snackBar = MutableLiveData<String?>()

    /**
     * Request a snackbar to display a string.
     */
    val snackbar: LiveData<String?>
        get() = _snackBar

    private val _spinner = MutableLiveData<Boolean>(false)

    /**
     * Show a loading spinner if true
     */
    val spinner: LiveData<Boolean>
        get() = _spinner

    fun searchWithName(name: String) {
        viewModelScope.launch {
            try {
                _spinner.value = true
                repository.searchWithName(name)
            } catch (error: SearchPlaceError) {
                _snackBar.value = error.message
            } finally {
                _spinner.value = false
            }
        }
    }

    fun searcPlaceDetail(placeId: String) {
        viewModelScope.launch {
            try {
                _spinner.value = true
                repository.searchPlaceDetail(placeId)
            } catch (error: SearchPlaceError) {
                _snackBar.value = error.message
            } finally {
                _spinner.value = false
            }
        }
    }

    /**
     * Called immediately after the UI shows the snackbar.
     */
    fun onSnackbarShown() {
        _snackBar.value = null
    }

}