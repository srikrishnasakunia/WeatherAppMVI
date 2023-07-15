package dev.krishna.weatherappmvi.data.mappers

import dev.krishna.weatherappmvi.data.remote.WeatherDataDto
import dev.krishna.weatherappmvi.data.remote.WeatherDto
import dev.krishna.weatherappmvi.domain.weather.WeatherData
import dev.krishna.weatherappmvi.domain.weather.WeatherInfo
import dev.krishna.weatherappmvi.domain.weather.WeatherType
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

private data class IndexedWeatherData(
    val index: Int,
    val data: WeatherData
)

fun WeatherDataDto.toWeatherDataMap(): Map<Int, List<WeatherData>> {
    return time.mapIndexed { index, time ->
        val temperature = temperatures[index]
        val weatherCode = weatherCodes[index]
        val windSpeed = windSpeeds[index]
        val pressure = pressures[index]
        val humidity = humidities[index]

        IndexedWeatherData(
            index = index,
            data = WeatherData(
                time = LocalDateTime.parse(time, DateTimeFormatter.ISO_DATE),
                temperatureCelsius = temperature,
                pressure = pressure,
                windSpeed = windSpeed,
                humidity = humidity,
                weatherType = WeatherType.fromWMO(weatherCode)
            )
        )
    }.groupBy {
        /** Since the Data we get will in 24 hr format followed by next day, we basically calculate
         * if its of same day or not by dividing it with 24 hrs. if same day we get 0 index, else
         * 1, 2, 3 ... so on. Hence we get index in form of 0 -> today, 1 -> tmrw, etc */
        it.index / 24
    }.mapValues {
        // Here we map the values of today tomorrow with respective days.
        it.value.map { indexedWeatherData ->
            indexedWeatherData.data
        }
    }
}


fun WeatherDto.toWeatherInfo(): WeatherInfo {
    val weatherDataMap = weatherData.toWeatherDataMap()
    val currentTime = LocalDateTime.now()
    val currentWeatherData = weatherDataMap[0]?.find {
        val hour = if (currentTime.minute < 30) currentTime.hour else currentTime.hour + 1
        it.time.hour == hour
    }
    return WeatherInfo(
        weatherDataPerDay = weatherDataMap,
        currentWeatherData = currentWeatherData
    )
}