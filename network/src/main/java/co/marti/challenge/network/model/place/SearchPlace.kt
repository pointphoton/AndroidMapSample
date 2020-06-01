package co.marti.challenge.network.model.place

import co.marti.challenge.network.model.place.Prediction

data class SearchPlace(
    val predictions: ArrayList<Prediction?>,
    val status: String
)