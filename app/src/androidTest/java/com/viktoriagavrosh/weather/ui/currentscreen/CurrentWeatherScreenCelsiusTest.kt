package com.viktoriagavrosh.weather.ui.currentscreen

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

class CurrentWeatherScreenCelsiusTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Before
    fun launchCurrentScreen() {
        composeTestRule.setContent {
            CurrentWeatherScreen(
                city = "FakeCity",
                weatherInfo = FakeDataSource.weatherInfoForCurrentScreen,
                isCelsius = true,
                onDetailsClick = {}
            )
        }
    }

    @Test
    fun currentScreen_mainCardCelsius_tempDisplayed() {
        composeTestRule.onNodeWithText(FakeDataSource.weatherInfoForCurrentScreen.currentWeather.tempC)
            .assertIsDisplayed()
    }

    @Test
    fun currentScreen_mainCardCelsius_tempHasNoClick() {
        composeTestRule.onNodeWithText(FakeDataSource.weatherInfoForCurrentScreen.currentWeather.tempC)
            .assertHasNoClickAction()
    }

    @Test
    fun currentScreen_celsius_FeelsLikeTempDisplayed() {
        val text = composeTestRule.activity.getString(
            R.string.feels_like,
            FakeDataSource.weatherInfoForCurrentScreen.currentWeather.feelsLikeTempC
        )
        composeTestRule.onNodeWithText(text).assertIsDisplayed()
    }

    @Test
    fun currentScreen_celsius_FeelsLikeTempHasNoClick() {
        val text = composeTestRule.activity.getString(
            R.string.feels_like,
            FakeDataSource.weatherInfoForCurrentScreen.currentWeather.feelsLikeTempC
        )
        composeTestRule.onNodeWithText(text).assertHasNoClickAction()
    }

    @Test
    fun currentScreen_celsius_windDisplayed() {
        val text = composeTestRule.activity.getString(
            R.string.wind,
            FakeDataSource.weatherInfoForCurrentScreen.currentWeather.windSpeedKm
        )
        composeTestRule.onNodeWithText(text).assertIsDisplayed()
    }

    @Test
    fun currentScreen_celsius_windHasNoClick() {
        val text = composeTestRule.activity.getString(
            R.string.wind,
            FakeDataSource.weatherInfoForCurrentScreen.currentWeather.windSpeedKm
        )
        composeTestRule.onNodeWithText(text).assertHasNoClickAction()
    }

    @Test
    fun currentScreen_celsius_precipitationDisplayed() {
        val text = composeTestRule.activity.getString(
            R.string.precipitation,
            FakeDataSource.weatherInfoForCurrentScreen.currentWeather.precipitationMm
        )
        composeTestRule.onNodeWithText(text).assertIsDisplayed()
    }

    @Test
    fun currentScreen_celsius_precipitationHasNoClick() {
        val text = composeTestRule.activity.getString(
            R.string.precipitation,
            FakeDataSource.weatherInfoForCurrentScreen.currentWeather.precipitationMm
        )
        composeTestRule.onNodeWithText(text).assertHasNoClickAction()
    }

    @Test
    fun currentScreen_celsius_pressureDisplayed() {
        val text = composeTestRule.activity.getString(
            R.string.pressure,
            FakeDataSource.weatherInfoForCurrentScreen.currentWeather.pressureMm
        )
        composeTestRule.onNodeWithText(text).assertIsDisplayed()
    }

    @Test
    fun currentScreen_celsius_pressureHasNoClick() {
        val text = composeTestRule.activity.getString(
            R.string.pressure,
            FakeDataSource.weatherInfoForCurrentScreen.currentWeather.pressureMm
        )
        composeTestRule.onNodeWithText(text).assertHasNoClickAction()
    }
}