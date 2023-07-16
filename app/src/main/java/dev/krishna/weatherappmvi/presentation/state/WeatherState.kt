package dev.krishna.weatherappmvi.presentation.state

import dev.krishna.weatherappmvi.domain.weather.WeatherInfo

data class WeatherState(
    val weatherInfo: WeatherInfo? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)
