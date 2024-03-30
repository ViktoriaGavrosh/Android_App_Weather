package com.viktoriagavrosh.weather.model.apimodel

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class CurrentWeather(
    @SerialName(value = "condition") override val weatherCondition: WeatherCondition = WeatherCondition(),
    @SerialName(value = "temp_c") override val tempC: Double = 0.0,
    @SerialName(value = "temp_f") override val tempF: Double = 0.0,
    @SerialName(value = "wind_mph") override val windSpeedMile: Double = 0.0,
    @SerialName(value = "wind_kph") override val windSpeedKm: Double = 0.0,
    @SerialName(value = "precip_mm") override val precipitationMm: Double = 0.0,
    @SerialName(value = "precip_in") override val precipitationInch: Double = 0.0,
    override val humidity: Double = 0.0,    // Int?
    @SerialName(value = "feelslike_c") val feelsLikeTempC: Double = 0.0,
    @SerialName(value = "feelslike_f") val feelsLikeTempF: Double = 0.0,
    val cloud: Double = 0.0,       // Int?
    @SerialName(value = "wind_dir") val windDirection: String = "",
    @SerialName(value = "gust_mph") val windGustMile: Double = 0.0,
    @SerialName(value = "gust_kph") val windGustKm: Double = 0.0,
    @SerialName(value = "pressure_in") val pressureIn: Double = 0.0,
    @SerialName(value = "vis_km") val visibleKm: Double = 0.0,
    @SerialName(value = "vis_miles") val visibleMile: Double = 0.0,
    @SerialName(value = "uv") val uvIndex: Double = 0.0,
    @SerialName(value = "pressure_mb") private val pressureMb: Double = 0.0
) : Weather() {
    val pressureMm = pressureMb * 0.75
}