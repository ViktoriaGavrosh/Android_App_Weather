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

class ForecastScreenFahrenheitTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Before
    fun launchForecastScreen() {
        composeTestRule.setContent {
            ForecastScreen(
                days = FakeDataSource.daysForForecastScreen,
                dateSelectedDay = FakeDataSource.daysForForecastScreen[0].date,
                isCelsius = false,
                onDetailsClick = {}
            )
        }
    }

    @Test
    fun forecastScreen_fahrenheit_tempDisplayed() {
        val text = FakeDataSource.daysForForecastScreen[0].dayWeather.tempF
        composeTestRule.onNodeWithText(text).assertIsDisplayed()
    }

    @Test
    fun forecastScreen_fahrenheit_tempHasNoClick() {
        val text = FakeDataSource.daysForForecastScreen[0].dayWeather.tempF
        composeTestRule.onNodeWithText(text).assertHasNoClickAction()
    }

    @Test
    fun forecastScreen_fahrenheit_windDisplayed() {
        val text = composeTestRule.activity.getString(
            R.string.wind,
            FakeDataSource.daysForForecastScreen[0].dayWeather.windSpeedMile
        )
        composeTestRule.onNodeWithText(text).assertIsDisplayed()
    }

    @Test
    fun forecastScreen_fahrenheit_windHasNoClick() {
        val text = composeTestRule.activity.getString(
            R.string.wind,
            FakeDataSource.daysForForecastScreen[0].dayWeather.windSpeedMile
        )
        composeTestRule.onNodeWithText(text).assertHasNoClickAction()
    }

    @Test
    fun forecastScreen_fahrenheit_precipitationDisplayed() {
        val text = composeTestRule.activity.getString(
            R.string.precipitation,
            FakeDataSource.daysForForecastScreen[0].dayWeather.precipitationInch
        )
        composeTestRule.onNodeWithText(text).assertIsDisplayed()
    }

    @Test
    fun forecastScreen_fahrenheit_precipitationHasNoClick() {
        val text = composeTestRule.activity.getString(
            R.string.precipitation,
            FakeDataSource.daysForForecastScreen[0].dayWeather.precipitationInch
        )
        composeTestRule.onNodeWithText(text).assertHasNoClickAction()
    }

    @Test
    fun forecastScreenHourCard_fahrenheit_tempDisplayed() {
        val text = FakeDataSource.daysForForecastScreen[0].hours[0].tempF
        composeTestRule.onNodeWithText(text).assertIsDisplayed()
    }
}