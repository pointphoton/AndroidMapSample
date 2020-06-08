package co.maps.exmp.network.model.detail

import java.io.Serializable

data class SearchDetail (
    val html_attributions: List<Any>,
    val result: Result,
    val status: String
): Serializable