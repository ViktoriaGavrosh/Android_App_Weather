package com.viktoriagavrosh.weather.fake

import com.viktoriagavrosh.weather.data.WeatherRepository
import com.viktoriagavrosh.weather.model.apimodel.WeatherInfo

class FakeNetworkWeatherRepository : WeatherRepository {
    override suspend fun getWeatherInfo(city: String): WeatherInfo {
        return FakeDataSource.weatherInfo
    }
}