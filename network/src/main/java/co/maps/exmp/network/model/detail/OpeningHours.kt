package co.maps.exmp.network.model.detail

data class OpeningHours(
    val open_now: Boolean,
    val periods: List<Period>,
    val weekday_text: List<String>
)