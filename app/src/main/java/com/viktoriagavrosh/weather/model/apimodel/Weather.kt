package com.viktoriagavrosh.weather.model.apimodel

abstract class Weather {
    abstract val weatherCondition: WeatherCondition
    abstract val tempC: Double
    abstract val tempF: Double
    abstract val windSpeedMile: Double
    abstract val windSpeedKm: Double
    abstract val precipitationMm: Double
    abstract val precipitationInch: Double
    abstract val humidity: Double
}