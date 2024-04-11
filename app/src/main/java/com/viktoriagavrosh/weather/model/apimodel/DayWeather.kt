package com.viktoriagavrosh.weather.model.apimodel

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Serializable data class for JSON conversion
 */
@Serializable
data class DayWeather(
    @SerialName(value = "condition") override val weatherCondition: WeatherCondition = WeatherCondition(),
    @SerialName(value = "maxtemp_c") override val tempC: Double = 0.0,
    @SerialName(value = "maxtemp_f") override val tempF: Double = 0.0,
    @SerialName(value = "maxwind_mph") override val windSpeedMile: Double = 0.0,
    @SerialName(value = "maxwind_kph") override val windSpeedKm: Double = 0.0,
    @SerialName(value = "totalprecip_mm") override val precipitationMm: Double = 0.0,
    @SerialName(value = "totalprecip_in") override val precipitationInch: Double = 0.0,
    @SerialName(value = "avghumidity") override val humidity: Double = 0.0,
    @SerialName(value = "avgvis_km") val visibleKm: Double = 0.0,
    @SerialName(value = "vis_miles") val visibleMile: Double = 0.0,
    @SerialName(value = "daily_chance_of_rain") val rainChance: Double = 0.0,
    @SerialName(value = "daily_chance_of_snow") val snowChance: Double = 0.0,
    @SerialName(value = "uv") val uvIndex: Double = 0.0
) : Weather()