package com.viktoriagavrosh.weather.model.apimodel

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Serializable data class for JSON conversion
 */
@Serializable
data class DayWeather(
    @SerialName(value = "condition") override val weatherCondition: WeatherCondition = WeatherCondition(),
    @SerialName(value = "maxtemp_c") private val tempCApi: Double = 0.0,
    @SerialName(value = "maxtemp_f") private val tempFApi: Double = 0.0,
    @SerialName(value = "maxwind_mph") private val windSpeedMileApi: Double = 0.0,
    @SerialName(value = "maxwind_kph") private val windSpeedKmApi: Double = 0.0,
    @SerialName(value = "totalprecip_mm") private val precipitationMmApi: Double = 0.0,
    @SerialName(value = "totalprecip_in") private val precipitationInchApi: Double = 0.0,
    @SerialName(value = "avghumidity") private val humidityApi: Double = 0.0,
    @SerialName(value = "avgvis_km") private val visibleKmApi: Double = 0.0,
    @SerialName(value = "vis_miles") private val visibleMileApi: Double = 0.0,
    @SerialName(value = "uv") private val uvIndexApi: Double = 0.0
) : Weather() {
    override val tempC = "${tempCApi.toInt()} ℃"
    override val tempF = "${tempFApi.toInt()} ℉"
    override val windSpeedMile = "${windSpeedMileApi.toInt()} mph"
    override val windSpeedKm = "${windSpeedKmApi.toInt()} km/h"
    override val precipitationMm = "$precipitationMmApi mm"
    override val precipitationInch = "$precipitationInchApi in"
    override val humidityPercent = "${humidityApi.toInt()} %"
    val visibleKm = "${visibleKmApi.toInt()} km"
    val visibleMile = "${visibleMileApi.toInt()} mi"
    val uVIndex = "${uvIndexApi.toInt()}"
}