package com.viktoriagavrosh.weather.model.apimodel

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Serializable data class for JSON conversion
 */
@Serializable
data class WeatherCondition(
    @SerialName(value = "text") val condition: String = "",
    private val icon: String = ""
) {
    val iconUri = "https:$icon"
}