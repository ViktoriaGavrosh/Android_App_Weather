package com.viktoriagavrosh.weather.data

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit


interface AppContainer {
    val weatherRepository: WeatherRepository
    val settingsRepository: SettingsRepository
}

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
    override val weatherRepository: WeatherRepository by lazy {
        NetworkWeatherRepository(retrofitService)
    }

    override val settingsRepository: SettingsRepository by lazy {
        UserSettingsRepository(dataStore = dataStore)
    }
}
