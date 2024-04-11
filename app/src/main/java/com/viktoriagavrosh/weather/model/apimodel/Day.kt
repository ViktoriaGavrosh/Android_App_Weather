package com.viktoriagavrosh.weather.model.apimodel

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Serializable data class for JSON conversion
 */
@Serializable
data class Day(
    val date: String = "",
    @SerialName(value = "day") val dayWeather: DayWeather = DayWeather(),
    @SerialName(value = "astro") val dayAstro: DayAstro = DayAstro(),
    @SerialName(value = "hour") val hours: List<Hour> = List(3) { Hour() }
)