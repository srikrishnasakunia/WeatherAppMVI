package dev.krishna.weatherappmvi.domain.repository

import dev.krishna.weatherappmvi.domain.util.Resource
import dev.krishna.weatherappmvi.domain.weather.WeatherInfo

interface WeatherRepository {
    suspend fun getWeatherData(lat: Double, long: Double): Resource<WeatherInfo>
}