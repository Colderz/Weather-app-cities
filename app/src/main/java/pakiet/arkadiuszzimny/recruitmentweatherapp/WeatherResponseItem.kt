package pakiet.arkadiuszzimny.recruitmentweatherapp

data class WeatherResponseItem(
    val city: String,
    val hourly_temp: List<HourlyTemp>,
    val weather: String
)