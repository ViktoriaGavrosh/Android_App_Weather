package com.viktoriagavrosh.weather.data

import com.viktoriagavrosh.weather.model.apimodel.WeatherInfo
import java.io.IOException

interface WeatherRepository {
    suspend fun getWeatherInfo(city: String): WeatherInfo
}

class NetworkWeatherRepository(
    private val weatherApiService: WeatherApiService
) : WeatherRepository {
    override suspend fun getWeatherInfo(city: String): WeatherInfo {
        return try {
            weatherApiService.getWeatherInfo(city = city)
        } catch (e: IOException) {
            WeatherInfo()
        }
    }
}