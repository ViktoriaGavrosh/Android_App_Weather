package com.viktoriagavrosh.weather.fake

import com.viktoriagavrosh.weather.model.apimodel.Day
import com.viktoriagavrosh.weather.model.apimodel.Forecast
import com.viktoriagavrosh.weather.model.apimodel.Location
import com.viktoriagavrosh.weather.model.apimodel.WeatherInfo

object FakeDataSource {
    const val fakeCity = "FakeCity"
    const val isMusic = true
    const val isCelsuis = true
    val wallpaperId = 1

    private val days = listOf(Day(), Day())

    val weatherInfo = WeatherInfo(
        location = Location(
            cityName = fakeCity
        ),
        forecast = Forecast(
            days = days
        )
    )
}