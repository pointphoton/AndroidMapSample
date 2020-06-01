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
            throw SearchPlaceError("Unable to fetch data", cause)
        }
    }


    suspend fun searchPlaceDetail(placeId: String) {
        try {
            // Make network request using a blocking call
            val response = network.searchPlaceDetail(placeid = placeId)
            detailLD.value = response
        } catch (cause: Throwable) {
            // If anything throws an exception, inform the caller
            throw SearchPlaceError("Unable to fetch data", cause)
        }
    }

    /*
    suspend  fun searchWithName3(name :String){

            // Make network request using a blocking call
            val call = network.searchPlaceWithName(input = "ankara")
            //nameLD.value =result
           DebugLog.write(call.isSuccessful)

           /* call.enqueue(object : Callback<SearchPlace> {
                override fun onResponse(
                    call: Call<SearchPlace>,
                    response: Response<SearchPlace>
                ) {
                    if (response.code() == 200) {
                        DebugLog.write(
                            response.code().toString() + " " + Thread.currentThread()
                                .name
                        )
                        // na.setValue(true)
                    }
                }

                override fun onFailure(
                    call: Call<SearchPlace>,
                    t: Throwable
                ) {
                    DebugLog.write()
                    // mldUpdateLevel.setValue(false)
                }
            })

        } catch (cause: Throwable) {
            // If anything throws an exception, inform the caller
            throw SearchPlaceError("Unable to fetch data", cause)
        }*/
    }


    fun searchWithName2(name :String){
         val mThingSpeakService =
             ThingSpeakServiceGenerator.createService(ThingSpeakService::class.java)
         val call: Call<SearchPlace> =
             mThingSpeakService.searchPlaceWithName(ThingSpeakServiceGenerator.API_KEY,"tr","ankara")

         call.enqueue(object : Callback<SearchPlace> {
             override fun onResponse(
                 call: Call<SearchPlace>,
                 response: Response<SearchPlace>
             ) {
                 if (response.code() == 200) {
                     DebugLog.write(
                         response.code().toString() + " " + Thread.currentThread()
                             .name
                     )
                    // na.setValue(true)
                 }
             }

             override fun onFailure(
                 call: Call<SearchPlace>,
                 t: Throwable
             ) {
                 DebugLog.write()
                // mldUpdateLevel.setValue(false)
             }
         })
    }
*/
}

class SearchPlaceError(message: String, cause: Throwable?) : Throwable(message, cause)

class APIError : Throwable()
