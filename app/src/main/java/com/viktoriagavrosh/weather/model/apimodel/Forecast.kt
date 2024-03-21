package com.viktoriagavrosh.weather.model.apimodel

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Forecast (
    @SerialName(value = "forecastday") val days: List<Day> = emptyList()
)