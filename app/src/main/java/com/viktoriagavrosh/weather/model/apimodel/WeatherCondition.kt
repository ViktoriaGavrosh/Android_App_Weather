package com.viktoriagavrosh.weather.model.apimodel

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class WeatherCondition (
    @SerialName(value = "text") val condition: String = "",
    private val icon: String = ""
) {
    val iconUri = "https://$icon"
}