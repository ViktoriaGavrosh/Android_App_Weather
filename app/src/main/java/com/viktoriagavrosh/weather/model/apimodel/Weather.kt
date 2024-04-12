package com.viktoriagavrosh.weather.model.apimodel


abstract class Weather {
    abstract val weatherCondition: WeatherCondition
    abstract val tempC: String
    abstract val tempF: String
    abstract val windSpeedMile: String
    abstract val windSpeedKm: String
    abstract val precipitationMm: String
    abstract val precipitationInch: String
    abstract val humidityPercent: String
}