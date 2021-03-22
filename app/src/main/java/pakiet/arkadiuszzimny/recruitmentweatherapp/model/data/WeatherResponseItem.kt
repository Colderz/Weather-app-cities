package pakiet.arkadiuszzimny.recruitmentweatherapp.model.data

data class WeatherResponseItem(
    val city: String,
    val hourly_temp: List<HourlyTemp>,
    val weather: String
)