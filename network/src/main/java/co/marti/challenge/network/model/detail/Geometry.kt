package co.marti.challenge.network.model.detail

import java.io.Serializable

data class Geometry(
    val location: Location,
    val viewport: Viewport
): Serializable