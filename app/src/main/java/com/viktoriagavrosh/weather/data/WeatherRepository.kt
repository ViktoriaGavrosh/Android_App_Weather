package com.viktoriagavrosh.weather.data

import com.viktoriagavrosh.weather.model.apimodel.WeatherInfo

interface WeatherRepository {
    suspend fun getWeatherInfo(city: String): WeatherInfo
}

class NetworkWeatherRepository(
    private val weatherApiService: WeatherApiService
) : WeatherRepository {
    override suspend fun getWeatherInfo(city: String): WeatherInfo {
        return weatherApiService.getWeatherInfo(city = city)
    }
}