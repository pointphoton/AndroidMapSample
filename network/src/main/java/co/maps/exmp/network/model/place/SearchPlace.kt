package co.maps.exmp.network.model.place

data class SearchPlace(
    val predictions: ArrayList<Prediction?>,
    val status: String
)