package com.viktoriagavrosh.weather.ui.current

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertHasNoClickAction
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import com.viktoriagavrosh.weather.R
import com.viktoriagavrosh.weather.fake.FakeDataSource
import com.viktoriagavrosh.weather.ui.screens.CurrentWeatherScreen
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class CurrentWeatherScreenFahrenheitTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Before
    fun launchCurrentScreen() {
        composeTestRule.setContent {
            CurrentWeatherScreen(
                city = "FakeCity",
                weatherInfo = FakeDataSource.weatherInfoForCurrentScreen,
                isCelsius = false,
                onDetailsClick = {}
            )
        }
    }

    @Test
    fun currentScreen_mainCardFahrenheit_tempDisplayed() {
        composeTestRule.onNodeWithText(FakeDataSource.weatherInfoForCurrentScreen.currentWeather.tempF)
            .assertIsDisplayed()
    }

    @Test
    fun currentScreen_mainCardFahrenheit_tempHasNoClick() {
        composeTestRule.onNodeWithText(FakeDataSource.weatherInfoForCurrentScreen.currentWeather.tempF)
            .assertHasNoClickAction()
    }

    @Test
    fun currentScreen_fahrenheit_FeelsLikeTempDisplayed() {
        val text = composeTestRule.activity.getString(
            R.string.feels_like,
            FakeDataSource.weatherInfoForCurrentScreen.currentWeather.feelsLikeTempF
        )
        composeTestRule.onNodeWithText(text).assertIsDisplayed()
    }

    @Test
    fun currentScreen_fahrenheit_FeelsLikeTempHasNoClick() {
        val text = composeTestRule.activity.getString(
            R.string.feels_like,
            FakeDataSource.weatherInfoForCurrentScreen.currentWeather.feelsLikeTempF
        )
        composeTestRule.onNodeWithText(text).assertHasNoClickAction()
    }

    @Test
    fun currentScreen_fahrenheit_windDisplayed() {
        val text = composeTestRule.activity.getString(
            R.string.wind,
            FakeDataSource.weatherInfoForCurrentScreen.currentWeather.windSpeedMile
        )
        composeTestRule.onNodeWithText(text).assertIsDisplayed()
    }

    @Test
    fun currentScreen_fahrenheit_windHasNoClick() {
        val text = composeTestRule.activity.getString(
            R.string.wind,
            FakeDataSource.weatherInfoForCurrentScreen.currentWeather.windSpeedMile
        )
        composeTestRule.onNodeWithText(text).assertHasNoClickAction()
    }

    @Test
    fun currentScreen_fahrenheit_precipitationDisplayed() {
        val text = composeTestRule.activity.getString(
            R.string.precipitation,
            FakeDataSource.weatherInfoForCurrentScreen.currentWeather.precipitationInch
        )
        composeTestRule.onNodeWithText(text).assertIsDisplayed()
    }

    @Test
    fun currentScreen_fahrenheit_precipitationHasNoClick() {
        val text = composeTestRule.activity.getString(
            R.string.precipitation,
            FakeDataSource.weatherInfoForCurrentScreen.currentWeather.precipitationInch
        )
        composeTestRule.onNodeWithText(text).assertHasNoClickAction()
    }

    @Test
    fun currentScreen_fahrenheit_pressureDisplayed() {
        val text = composeTestRule.activity.getString(
            R.string.pressure,
            FakeDataSource.weatherInfoForCurrentScreen.currentWeather.pressureIn
        )
        composeTestRule.onNodeWithText(text).assertIsDisplayed()
    }

    @Test
    fun currentScreen_fahrenheit_pressureHasNoClick() {
        val text = composeTestRule.activity.getString(
            R.string.pressure,
            FakeDataSource.weatherInfoForCurrentScreen.currentWeather.pressureIn
        )
        composeTestRule.onNodeWithText(text).assertHasNoClickAction()
    }
}