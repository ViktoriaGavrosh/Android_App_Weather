package com.viktoriagavrosh.weather.fake

import com.viktoriagavrosh.weather.model.Wallpaper
import com.viktoriagavrosh.weather.model.apimodel.CurrentWeather
import com.viktoriagavrosh.weather.model.apimodel.Day
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
    private const val precipMm = 16.0
    private const val precipInch = 18.0
    private const val humidity = 3.0
    private const val presMb = 1.0
    private const val presIn = 2.0

    const val isMusic = true
    const val isCelsius = true
    val wallpaperId = Wallpaper.CLOUDY.imageId

    val days = List<Day>(3) { Day(date = "${it + 1}") }
    val hours = listOf(
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

    val weatherInfoForCurrentScreen = WeatherInfo(
        currentWeather = CurrentWeather(
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
            precipitationMmApi = precipMm,
            precipitationInchApi = precipInch,
            humidityApi = humidity,
            pressureMb = presMb,
            pressureInApi = presIn
        ),
        forecast = Forecast(
            days = days
        )
    )

    val daysForForecastScreen = List<Day>(3) {
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
                humidityApi = humidity + it
            ),
            hours = hours
        )
    }
}