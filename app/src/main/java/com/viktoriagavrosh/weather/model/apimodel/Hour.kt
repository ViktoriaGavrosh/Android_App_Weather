package com.viktoriagavrosh.weather.model.apimodel

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Hour (     // попроб наследов от CurrentWeather
    val time: String,
    @SerialName(value = "condition") val weatherCondition: WeatherCondition = WeatherCondition(),
    @SerialName(value = "temp_c") val tempC: Double = 0.0,
    @SerialName(value = "temp_f") val tempF: Double = 0.0,
    @SerialName(value = "feelslike_c") val feelsLikeTempC: Double = 0.0,
    @SerialName(value = "feelslike_f") val feelsLikeTempF: Double = 0.0,
    @SerialName(value = "wind_mph") val windSpeedMile: Double = 0.0,
    @SerialName(value = "wind_kph") val windSpeedKm: Double = 0.0,
    @SerialName(value = "wind_dir") val windDirection: String = "",
    @SerialName(value = "gust_mph") val windGustMile: Double = 0.0,
    @SerialName(value = "gust_kph") val windGustKm: Double = 0.0,
    @SerialName(value = "pressure_mb") val pressureMb: Double = 0.0,
    @SerialName(value = "pressure_in") val pressureIn: Double = 0.0,
    @SerialName(value = "precip_mm") val precipitationMm: Double = 0.0,
    @SerialName(value = "precip_in") val precipitationInch: Double = 0.0,
    val humidity: Double = 0.0,    // Int?
    val cloud: Double = 0.0,       // Int?
    @SerialName(value = "vis_km") val visibleKm: Double = 0.0,
    @SerialName(value = "vis_miles") val visibleMile: Double = 0.0,
    @SerialName(value = "uv") val uvIndex: Double = 0.0
)