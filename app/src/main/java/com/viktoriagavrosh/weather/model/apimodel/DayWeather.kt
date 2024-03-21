package com.viktoriagavrosh.weather.model.apimodel

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DayWeather(
    @SerialName(value = "condition") val weatherCondition: WeatherCondition = WeatherCondition(),
    @SerialName(value = "maxtemp_c") val tempC: Double = 0.0,
    @SerialName(value = "maxtemp_f") val tempF: Double = 0.0,
    @SerialName(value = "maxwind_mph") val windSpeedMile: Double = 0.0,
    @SerialName(value = "maxwind_kph") val windSpeedKm: Double = 0.0,
    @SerialName(value = "totalprecip_mm") val precipitationMm: Double = 0.0,
    @SerialName(value = "totalprecip_in") val precipitationInch: Double = 0.0,
    @SerialName(value = "avghumidity") val humidity: Double = 0.0,    // Int?
    @SerialName(value = "avgvis_km") val visibleKm: Double = 0.0,
    @SerialName(value = "vis_miles") val visibleMile: Double = 0.0,
    @SerialName(value = "daily_chance_of_rain") val rainChance: Double = 0.0,
    @SerialName(value = "daily_chance_of_snow") val snowChance: Double = 0.0,
    @SerialName(value = "uv") val uvIndex: Double = 0.0
)