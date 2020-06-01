package co.marti.challenge.ui.search

import androidx.lifecycle.MutableLiveData
import co.marti.challenge.network.MainNetwork
import co.marti.challenge.network.model.detail.SearchDetail
import co.marti.challenge.network.model.place.SearchPlace

class SearchRepository(val network: MainNetwork) {

    val placeLD: MutableLiveData<SearchPlace> = MutableLiveData<SearchPlace>()
    val detailLD: MutableLiveData<SearchDetail> = MutableLiveData<SearchDetail>()

    suspend fun searchWithName(name: String) {
        try {
            // Make network request using a blocking call
            val response = network.searchPlaceWithName(input = name)
            placeLD.value = response
        } catch (cause: Throwable) {
            // If anything throws an exception, inform the caller
            throw SearchPlaceError("Unable to fetch place information", cause)
        }
    }


    suspend fun searchPlaceDetail(placeId: String) {
        try {
            // Make network request using a blocking call
            val response = network.searchPlaceDetail(placeid = placeId)
            detailLD.value = response
        } catch (cause: Throwable) {
            // If anything throws an exception, inform the caller
            throw SearchPlaceError("Unable to fetch detailed data", cause)
        }
    }


}

class SearchPlaceError(message: String, cause: Throwable?) : Throwable(message, cause)

