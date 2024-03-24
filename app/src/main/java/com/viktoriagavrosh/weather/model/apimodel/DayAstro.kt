package com.viktoriagavrosh.weather.model.apimodel

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DayAstro(
    @SerialName(value = "sunrise") val sunriseTime: String = "",
    @SerialName(value = "sunset") val sunsetTime: String = "",
    @SerialName(value = "moonrise") val moonriseTime: String = "",
    @SerialName(value = "moonset") val moonsetTime: String = "",
    @SerialName(value = "moon_phase") val moonPhase: String = ""
)