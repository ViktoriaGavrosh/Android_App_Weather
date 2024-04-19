package com.viktoriagavrosh.weather.fake

import com.viktoriagavrosh.weather.data.WeatherApiService
import com.viktoriagavrosh.weather.model.apimodel.WeatherInfo

class FakeWeatherApiService : WeatherApiService {
    override suspend fun getWeatherInfo(
        key: String,
        city: String,
        days: Int,
        aqi: String,
        alerts: String
    ): WeatherInfo {
        return FakeDataSource.weatherInfo
    }
}