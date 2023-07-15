package dev.krishna.weatherappmvi.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dev.krishna.weatherappmvi.data.location.DefaultLocationTracker
import dev.krishna.weatherappmvi.domain.location.LocationTracker
import javax.inject.Singleton

@Module
@InstallIn(Singleton::class)
abstract class LocationModule {

    @Binds
    @Singleton
    abstract fun bindLocationTracker(defaultLocationTracker: DefaultLocationTracker): LocationTracker

}