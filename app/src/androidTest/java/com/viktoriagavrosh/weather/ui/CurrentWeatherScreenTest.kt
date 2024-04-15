package com.viktoriagavrosh.weather.ui

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.viktoriagavrosh.weather.R
import com.viktoriagavrosh.weather.fake.FakeDataSource
import com.viktoriagavrosh.weather.ui.screens.CurrentWeatherScreen
import com.viktoriagavrosh.weather.utils.onNodeWithStringId
import org.junit.Rule
import org.junit.Test

class CurrentWeatherScreenTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun currentScreen_backButtonNotExistOnTopBar() {
        launchCurrentScreen()
        val backText = composeTestRule.activity.getString(R.string.back)
        composeTestRule.onNodeWithContentDescription(backText).assertDoesNotExist()
    }

    @Test
    fun currentScreen_cityDisplayedOnTopBar() {
        val fakeCity = "FakeCity"
        launchCurrentScreen(city = fakeCity)
        composeTestRule.onNodeWithText(fakeCity).assertIsDisplayed()
    }

    @Test
    fun currentScreen_settingsButtonDisplayedOnTopBar() {
        launchCurrentScreen()
        val settingsText = composeTestRule.activity.getString(R.string.settings)
        composeTestRule.onNodeWithContentDescription(settingsText).assertIsDisplayed()
    }

    @Test
    fun currentScreen_nowTabExist() {
        launchCurrentScreen()
        composeTestRule.onNodeWithStringId(R.string.now).assertExists()
    }

    @Test
    fun currentScreen_forecastTabExist() {
        launchCurrentScreen()
        composeTestRule.onNodeWithStringId(R.string.forecast).assertExists()
    }

    @Test
    fun currentScreen_mainCard_conditionDisplayed() {
        launchCurrentScreen()
        composeTestRule.onNodeWithText(FakeDataSource.condition).assertIsDisplayed()
    }

    @Test
    fun currentScreen_mainCardCelsius_tempDisplayed() {
        launchCurrentScreen()
        composeTestRule.onNodeWithText(FakeDataSource.weatherInfoForCurrentScreen.currentWeather.tempC)
            .assertIsDisplayed()
    }

    @Test
    fun currentScreen_mainCardFahrenheit_tempDisplayed() {
        launchCurrentScreen(isCelsius = false)
        composeTestRule.onNodeWithText(FakeDataSource.weatherInfoForCurrentScreen.currentWeather.tempF)
            .assertIsDisplayed()
    }

    @Test
    fun currentScreen_mainCard_iconDisplayed() {
        launchCurrentScreen()
        composeTestRule.onNodeWithContentDescription(FakeDataSource.condition)
            .assertIsDisplayed()
    }

    @Test
    fun currentScreen_celsius_FeelsLikeTempDisplayed() {
        launchCurrentScreen()
        val text = composeTestRule.activity.getString(
            R.string.feels_like,
            FakeDataSource.weatherInfoForCurrentScreen.currentWeather.feelsLikeTempC
        )
        composeTestRule.onNodeWithText(text).assertIsDisplayed()
    }

    @Test
    fun currentScreen_fahrenheit_FeelsLikeTempDisplayed() {
        launchCurrentScreen(isCelsius = false)
        val text = composeTestRule.activity.getString(
            R.string.feels_like,
            FakeDataSource.weatherInfoForCurrentScreen.currentWeather.feelsLikeTempF
        )
        composeTestRule.onNodeWithText(text).assertIsDisplayed()
    }

    @Test
    fun currentScreen_celsius_windDisplayed() {
        launchCurrentScreen()
        val text = composeTestRule.activity.getString(
            R.string.wind,
            FakeDataSource.weatherInfoForCurrentScreen.currentWeather.windSpeedKm
        )
        composeTestRule.onNodeWithText(text).assertIsDisplayed()
    }

    @Test
    fun currentScreen_fahrenheit_windDisplayed() {
        launchCurrentScreen(isCelsius = false)
        val text = composeTestRule.activity.getString(
            R.string.wind,
            FakeDataSource.weatherInfoForCurrentScreen.currentWeather.windSpeedMile
        )
        composeTestRule.onNodeWithText(text).assertIsDisplayed()
    }

    @Test
    fun currentScreen_celsius_precipitationDisplayed() {
        launchCurrentScreen()
        val text = composeTestRule.activity.getString(
            R.string.precipitation,
            FakeDataSource.weatherInfoForCurrentScreen.currentWeather.precipitationMm
        )
        composeTestRule.onNodeWithText(text).assertIsDisplayed()
    }

    @Test
    fun currentScreen_fahrenheit_precipitationDisplayed() {
        launchCurrentScreen(isCelsius = false)
        val text = composeTestRule.activity.getString(
            R.string.precipitation,
            FakeDataSource.weatherInfoForCurrentScreen.currentWeather.precipitationInch
        )
        composeTestRule.onNodeWithText(text).assertIsDisplayed()
    }

    @Test
    fun currentScreen_humidityDisplayed() {
        launchCurrentScreen()
        val text = composeTestRule.activity.getString(
            R.string.humidity,
            FakeDataSource.weatherInfoForCurrentScreen.currentWeather.humidityPercent
        )
        composeTestRule.onNodeWithText(text).assertIsDisplayed()
    }

    @Test
    fun currentScreen_celsius_pressureDisplayed() {
        launchCurrentScreen()
        val text = composeTestRule.activity.getString(
            R.string.pressure,
            FakeDataSource.weatherInfoForCurrentScreen.currentWeather.pressureMm
        )
        composeTestRule.onNodeWithText(text).assertIsDisplayed()
    }

    @Test
    fun currentScreen_fahrenheit_pressureDisplayed() {
        launchCurrentScreen(isCelsius = false)
        val text = composeTestRule.activity.getString(
            R.string.pressure,
            FakeDataSource.weatherInfoForCurrentScreen.currentWeather.pressureIn
        )
        composeTestRule.onNodeWithText(text).assertIsDisplayed()
    }

    @Test
    fun currentScreen_detailsButtonDisplayed() {
        launchCurrentScreen()
        val buttonText = composeTestRule.activity.getString(R.string.details)
        composeTestRule.onNodeWithContentDescription(buttonText).assertIsDisplayed()
    }

    @Test
    fun currentScreen_forecastTab_firstButtonDisplayed() {
        navigateToForecastTab()
        composeTestRule.onNodeWithText(FakeDataSource.days[0].date).assertIsDisplayed()
    }

    @Test
    fun currentScreen_forecastTab_secondButtonDisplayed() {
        navigateToForecastTab()
        composeTestRule.onNodeWithText(FakeDataSource.days[1].date).assertIsDisplayed()
    }

    @Test
    fun currentScreen_forecastTab_thirdButtonDisplayed() {
        navigateToForecastTab()
        composeTestRule.onNodeWithText(FakeDataSource.days[2].date).assertIsDisplayed()
    }

    private fun launchCurrentScreen(isCelsius: Boolean = true, city: String = "FakeCity") {
        composeTestRule.setContent {
            CurrentWeatherScreen(
                city = city,
                weatherInfo = FakeDataSource.weatherInfoForCurrentScreen,
                isCelsius = isCelsius,
                onDetailsClick = {}
            )
        }
    }

    private fun navigateToForecastTab() {
        launchCurrentScreen()
        composeTestRule.onNodeWithStringId(R.string.forecast).performClick()
    }
}