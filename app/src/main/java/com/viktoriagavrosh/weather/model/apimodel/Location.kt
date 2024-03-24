package com.viktoriagavrosh.weather.model.apimodel

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Location(
    @SerialName(value = "name") val cityName: String = "",
    val country: String = ""
)