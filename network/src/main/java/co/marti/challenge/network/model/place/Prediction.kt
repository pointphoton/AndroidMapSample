package co.marti.challenge.network.model.place

import co.marti.challenge.network.model.place.MatchedSubstring
import co.marti.challenge.network.model.place.StructuredFormatting
import co.marti.challenge.network.model.place.Term

data class Prediction(
    val description: String,
    val id: String,
    val matched_substrings: List<MatchedSubstring>,
    val place_id: String,
    val reference: String,
    val structured_formatting: StructuredFormatting,
    val terms: List<Term>,
    val types: List<String>
)