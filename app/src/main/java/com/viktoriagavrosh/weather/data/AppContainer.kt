package com.viktoriagavrosh.weather.data

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

/**
 * App container for Dependency injection
 */
interface AppContainer {
    val weatherRepository: WeatherRepository
    val settingsRepository: SettingsRepository
}

/**
 * [AppContainer] implementation that provides instances of [NetworkWeatherRepository] and
 * [UserSettingsRepository]
 */
class DefaultAppContainer(dataStore: DataStore<Preferences>) : AppContainer {

    private val baseUrl = "https://api.weatherapi.com"

    private val json = Json { ignoreUnknownKeys = true }

    private val retrofit = Retrofit
        .Builder()
        .addConverterFactory(
            json.asConverterFactory("application/json".toMediaType())
        )
        .baseUrl(baseUrl)
        .build()

    private val retrofitService: WeatherApiService by lazy {
        retrofit.create(WeatherApiService::class.java)
    }

    /**
     * implementation for [WeatherRepository]
     */
    override val weatherRepository: WeatherRepository by lazy {
        NetworkWeatherRepository(retrofitService)
    }

    /**
     * implementation for [SettingsRepository]
     */
    override val settingsRepository: SettingsRepository by lazy {
        UserSettingsRepository(dataStore = dataStore)
    }
}
