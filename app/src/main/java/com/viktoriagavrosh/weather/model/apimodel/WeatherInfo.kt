package com.viktoriagavrosh.weather.model.apimodel

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class WeatherInfo(
    val location: Location = Location(),
    @SerialName(value = "current")
    val currentWeather: CurrentWeather = CurrentWeather(),
    val forecast: Forecast = Forecast()
)