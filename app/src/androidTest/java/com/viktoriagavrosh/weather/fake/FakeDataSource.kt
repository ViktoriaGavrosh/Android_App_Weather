package com.viktoriagavrosh.weather.fake

import com.viktoriagavrosh.weather.model.Wallpaper
import com.viktoriagavrosh.weather.model.apimodel.CurrentWeather
import com.viktoriagavrosh.weather.model.apimodel.Day
import com.viktoriagavrosh.weather.model.apimodel.DayAstro
import com.viktoriagavrosh.weather.model.apimodel.DayWeather
import com.viktoriagavrosh.weather.model.apimodel.Forecast
import com.viktoriagavrosh.weather.model.apimodel.Hour
import com.viktoriagavrosh.weather.model.apimodel.WeatherCondition
import com.viktoriagavrosh.weather.model.apimodel.WeatherInfo

object FakeDataSource {
    const val condition = "Condition"
    private const val icon = "icon"
    private const val tempC = 10.0
    private const val tempF = 20.0
    private const val feelsLikeTempC = 15.0
    private const val feelsLikeTempF = 25.0
    private const val windSpeedKmApi = 5.0
    private const val windSpeedMileApi = 7.0
    private const val windGustKm = 4.0
    private const val windGustMile = 6.0
    private const val windDirection = "wind direction"
    private const val precipMm = 16.0
    private const val precipInch = 18.0
    private const val humidity = 3.0
    private const val presMb = 1.0
    private const val presIn = 2.0
    private const val cloud = 8.0
    private const val visibleKm = 9.0
    private const val visibleMile = 11.0
    private const val uvIndex = 12.0

    const val isMusic = true
    const val isCelsius = true
    val wallpaperId = Wallpaper.CLOUDY.imageId

    private val hours = listOf(
        Hour(
            time = "ti me",
            weatherCondition = WeatherCondition(
                condition = "hour $condition",
                icon = "hour $icon"
            ),
            tempCApi = tempC + 4,
            tempFApi = tempF + 4,
            windSpeedKmApi = windSpeedKmApi + 4,
            windSpeedMileApi = windSpeedMileApi + 4,
            precipitationMmApi = precipMm + 4,
            precipitationInchApi = precipInch + 4,
            humidityApi = humidity + 4
        )
    )

    private val currentWeather = CurrentWeather(
        weatherCondition = WeatherCondition(
            condition = condition,
            icon = icon
        ),
        tempCApi = tempC,
        tempFApi = tempF,
        feelsLikeTempCApi = feelsLikeTempC,
        feelsLikeTempFApi = feelsLikeTempF,
        windSpeedKmApi = windSpeedKmApi,
        windSpeedMileApi = windSpeedMileApi,
        windGustKmApi = windGustKm,
        windGustMileApi = windGustMile,
        windDirection = windDirection,
        precipitationMmApi = precipMm,
        precipitationInchApi = precipInch,
        humidityApi = humidity,
        pressureMb = presMb,
        pressureInApi = presIn,
        cloudApi = cloud,
        visibleKmApi = visibleKm,
        visibleMileApi = visibleMile,
        uvIndexApi = uvIndex
    )

    private val dayAstro = DayAstro(
        sunriseTime = "sunrise",
        sunsetTime = "sunset",
        moonriseTime = "moonrise",
        moonsetTime = "moonset",
        moonPhase = "Moon phase"
    )

    val fakeWeatherInfo = WeatherInfo(
        currentWeather = currentWeather,
        forecast = Forecast(
            days = List<Day>(3) {
                Day(
                    date = "${it + 1}",
                    dayWeather = DayWeather(
                        weatherCondition = WeatherCondition(
                            condition = "$it $condition",
                            icon = "$it $icon"
                        ),
                        tempCApi = tempC + it,
                        tempFApi = tempF + it,
                        windSpeedKmApi = windSpeedKmApi + it,
                        windSpeedMileApi = windSpeedMileApi + it,
                        precipitationMmApi = precipMm + it,
                        precipitationInchApi = precipInch + it,
                        humidityApi = humidity + it,
                        visibleMileApi = visibleMile + it,
                        visibleKmApi = visibleKm + it,
                        uvIndexApi = uvIndex + it
                    ),
                    dayAstro = dayAstro,
                    hours = hours
                )
            }
        )
    )
}