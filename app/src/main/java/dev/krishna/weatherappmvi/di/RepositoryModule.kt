package dev.krishna.weatherappmvi.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dev.krishna.weatherappmvi.data.repository.WeatherRepositoryImp
import dev.krishna.weatherappmvi.domain.repository.WeatherRepository
import javax.inject.Singleton

@Module
@InstallIn(Singleton::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindWeatherRepository(weatherRepositoryImp: WeatherRepositoryImp): WeatherRepository
}