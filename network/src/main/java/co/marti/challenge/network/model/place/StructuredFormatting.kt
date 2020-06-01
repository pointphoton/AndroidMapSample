package co.marti.challenge.network.model.place

import co.marti.challenge.network.model.place.MainTextMatchedSubstring

data class StructuredFormatting(
    val main_text: String,
    val main_text_matched_substrings: List<MainTextMatchedSubstring>,
    val secondary_text: String
)