package com.viktoriagavrosh.weather.ui.forecastscreen

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertHasNoClickAction
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import com.viktoriagavrosh.weather.R
import com.viktoriagavrosh.weather.fake.FakeDataSource
import com.viktoriagavrosh.weather.ui.screens.ForecastScreen
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class ForecastScreenCelsiusTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Before
    fun launchForecastScreen() {
        composeTestRule.setContent {
            ForecastScreen(
                days = FakeDataSource.daysForForecastScreen,
                dateSelectedDay = FakeDataSource.daysForForecastScreen[0].date,
                isCelsius = true,
                onDetailsClick = {}
            )
        }
    }

    @Test
    fun forecastScreen_celsius_tempDisplayed() {
        val text = FakeDataSource.daysForForecastScreen[0].dayWeather.tempC
        composeTestRule.onNodeWithText(text).assertIsDisplayed()
    }

    @Test
    fun forecastScreen_celsius_tempHasNoClick() {
        val text = FakeDataSource.daysForForecastScreen[0].dayWeather.tempC
        composeTestRule.onNodeWithText(text).assertHasNoClickAction()
    }

    @Test
    fun forecastScreen_celsius_windDisplayed() {
        val text = composeTestRule.activity.getString(
            R.string.wind,
            FakeDataSource.daysForForecastScreen[0].dayWeather.windSpeedKm
        )
        composeTestRule.onNodeWithText(text).assertIsDisplayed()
    }

    @Test
    fun forecastScreen_celsius_windHasNoClick() {
        val text = composeTestRule.activity.getString(
            R.string.wind,
            FakeDataSource.daysForForecastScreen[0].dayWeather.windSpeedKm
        )
        composeTestRule.onNodeWithText(text).assertHasNoClickAction()
    }

    @Test
    fun forecastScreen_celsius_precipitationDisplayed() {
        val text = composeTestRule.activity.getString(
            R.string.precipitation,
            FakeDataSource.daysForForecastScreen[0].dayWeather.precipitationMm
        )
        composeTestRule.onNodeWithText(text).assertIsDisplayed()
    }

    @Test
    fun forecastScreen_celsius_precipitationHasNoClick() {
        val text = composeTestRule.activity.getString(
            R.string.precipitation,
            FakeDataSource.daysForForecastScreen[0].dayWeather.precipitationMm
        )
        composeTestRule.onNodeWithText(text).assertHasNoClickAction()
    }

    @Test
    fun forecastScreenHourCard_celsius_tempDisplayed() {
        val text = FakeDataSource.daysForForecastScreen[0].hours[0].tempC
        composeTestRule.onNodeWithText(text).assertIsDisplayed()
    }
}