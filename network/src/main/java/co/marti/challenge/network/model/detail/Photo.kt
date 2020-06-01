package co.marti.challenge.network.model.detail

import java.io.Serializable

data class Photo(
    val height: Int,
    val html_attributions: List<String>,
    val photo_reference: String,
    val width: Int
): Serializable