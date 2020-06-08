package co.maps.exmp.network

import co.maps.exmp.network.model.detail.SearchDetail
import co.maps.exmp.network.model.place.SearchPlace
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

 private val service: MainNetwork by lazy {

    val BASE_URL = "https://maps.googleapis.com"
    val logging = HttpLoggingInterceptor()
        .setLevel(HttpLoggingInterceptor.Level.BODY)
    val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(logging)
        .build()

    val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    retrofit.create(MainNetwork::class.java)

}

 fun getNetworkService() = service



interface MainNetwork {

    @GET("/maps/api/place/autocomplete/json")
    suspend fun searchPlaceWithName(
        @Query("key") key: String="AIzaSyCfTDvEQvE6GnJQbBjjuwy2WMDeFxnhKm4",
        @Query("language") lang:String="tr",
        @Query("input")  input: String
    ): SearchPlace

    @GET("/maps/api/place/details/json")
    suspend fun searchPlaceDetail(
        @Query("key") key: String="AIzaSyCfTDvEQvE6GnJQbBjjuwy2WMDeFxnhKm4",
        @Query("language") lang:String="tr",
        @Query("placeid")  placeid: String
    ): SearchDetail
}
