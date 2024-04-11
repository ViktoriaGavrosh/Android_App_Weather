package com.viktoriagavrosh.weather.model.apimodel

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
/**
 * Serializable data class for JSON conversion
 */
@Serializable
data class Forecast(
    @SerialName(value = "forecastday") val days: List<Day> = List(3) { Day() }
)