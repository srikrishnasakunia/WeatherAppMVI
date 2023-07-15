package dev.krishna.weatherappmvi.data.repository

import dev.krishna.weatherappmvi.data.mappers.toWeatherInfo
import dev.krishna.weatherappmvi.data.remote.WeatherApi
import dev.krishna.weatherappmvi.domain.repository.WeatherRepository
import dev.krishna.weatherappmvi.domain.util.Resource
import dev.krishna.weatherappmvi.domain.weather.WeatherInfo
import javax.inject.Inject

class WeatherRepositoryImp @Inject constructor(
    private val api: WeatherApi
): WeatherRepository {
    override suspend fun getWeatherData(lat: Double, long: Double): Resource<WeatherInfo> {
        return try {
            Resource.Success(
                api.getWeatherData(
                    lat,
                    long
                ).toWeatherInfo()
            )
        } catch (e: Exception){
            Resource.Error(e.message ?: "An Unknown Error has occured.")
        }
    }
}