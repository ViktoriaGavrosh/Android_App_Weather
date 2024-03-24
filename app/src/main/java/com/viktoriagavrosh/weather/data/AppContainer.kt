package com.viktoriagavrosh.weather.data

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

interface AppContainer {
    val weatherRepository: WeatherRepository
}

class DefaultAppContainer : AppContainer {

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
}

//http://api.weatherapi.com
// /v1/forecast.json?
// key= 38573003d5d94892b0c85204241003
// &q=London
// &days=3
// &aqi=no
// &alerts=no