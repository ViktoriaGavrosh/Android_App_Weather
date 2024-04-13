package com.viktoriagavrosh.weather.model.apimodel

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Serializable data class for JSON conversion
 */
@Serializable
data class Hour(
    val time: String = "",
    @SerialName(value = "condition") override val weatherCondition: WeatherCondition = WeatherCondition(),
    @SerialName(value = "wind_dir") val windDirection: String = "",
    @SerialName(value = "temp_c") private val tempCApi: Double = 0.0,
    @SerialName(value = "temp_f") private val tempFApi: Double = 0.0,
    //@SerialName(value = "feelslike_c") private val feelsLikeTempCApi: Double = 0.0,
    //@SerialName(value = "feelslike_f") private val feelsLikeTempFApi: Double = 0.0,
    @SerialName(value = "wind_mph") private val windSpeedMileApi: Double = 0.0,
    @SerialName(value = "wind_kph") private val windSpeedKmApi: Double = 0.0,
    @SerialName(value = "precip_mm") private val precipitationMmApi: Double = 0.0,
    @SerialName(value = "precip_in") private val precipitationInchApi: Double = 0.0,
    @SerialName(value = "humidity") private val humidityApi: Double = 0.0,
    @SerialName(value = "gust_mph") private val windGustMileApi: Double = 0.0,
    @SerialName(value = "gust_kph") private val windGustKmApi: Double = 0.0,
    @SerialName(value = "pressure_mb") private val pressureMb: Double = 0.0,
    @SerialName(value = "pressure_in") private val pressureInApi: Double = 0.0,
    @SerialName(value = "cloud") private val cloudApi: Double = 0.0,
    @SerialName(value = "vis_km") private val visibleKmApi: Double = 0.0,
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
    //val pressureMm = "${(pressureMb * 0.75).toInt()} mmHg"        TODO for hour details
    //val pressureIn = "${pressureInApi.toInt()} inHg"
    //val visibleKm = "${visibleKmApi.toInt()} km"
    //val visibleMile = "${visibleMileApi.toInt()} mi"
    //val windGustMile = "${windGustMileApi.toInt()} mph"
    //val windGustKm = "${windGustKmApi.toInt()} km/h"
    //val uVIndex = "${uvIndexApi.toInt()}"
    //val cloudy = "${cloudApi.toInt()} %"
    // val feelsLikeTempC = "${feelsLikeTempCApi.toInt()} ℃"
    // val feelsLikeTempF = "${feelsLikeTempFApi.toInt()} ℉"
}