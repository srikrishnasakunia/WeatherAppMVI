package dev.krishna.weatherappmvi.domain.location

import android.location.Location

interface LocationTracker {

    suspend fun getCurrentLocation(): Location?

}