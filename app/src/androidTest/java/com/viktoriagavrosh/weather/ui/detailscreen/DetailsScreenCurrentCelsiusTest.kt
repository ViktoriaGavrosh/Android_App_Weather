package com.viktoriagavrosh.weather.ui.detailscreen

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertHasNoClickAction
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import com.viktoriagavrosh.weather.R
import com.viktoriagavrosh.weather.fake.FakeDataSource
import com.viktoriagavrosh.weather.ui.screens.DetailsScreen
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class DetailsScreenCurrentCelsiusTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Before
    fun launchDetailScreen() {
        composeTestRule.setContent {
            DetailsScreen(
                weatherInfo = FakeDataSource.fakeWeatherInfo,
                dateSelectedDay = "",
                isCelsius = true
            )
        }
    }

    @Test
    fun detailsScreenCurrent_celsius_tempDisplayed() {
        val text = FakeDataSource.fakeWeatherInfo.currentWeather.tempC
        composeTestRule.onNodeWithText(text).assertIsDisplayed()
    }

    @Test
    fun detailsScreenCurrent_celsius_tempHasNoClick() {
        val text = FakeDataSource.fakeWeatherInfo.currentWeather.tempC
        composeTestRule.onNodeWithText(text).assertHasNoClickAction()
    }

    @Test
    fun detailsScreenCurrent_celsius_feelsLikeDisplayed() {
        val text = composeTestRule.activity.getString(
            R.string.feels_like,
            FakeDataSource.fakeWeatherInfo.currentWeather.feelsLikeTempC
        )
        composeTestRule.onNodeWithText(text).assertIsDisplayed()
    }

    @Test
    fun detailsScreenCurrent_celsius_feelsLikeHasNoClick() {
        val text = composeTestRule.activity.getString(
            R.string.feels_like,
            FakeDataSource.fakeWeatherInfo.currentWeather.feelsLikeTempC
        )
        composeTestRule.onNodeWithText(text).assertHasNoClickAction()
    }

    @Test
    fun detailsScreenCurrent_celsius_windDisplayed() {
        val text = composeTestRule.activity.getString(
            R.string.wind,
            FakeDataSource.fakeWeatherInfo.currentWeather.windSpeedKm
        )
        composeTestRule.onNodeWithText(text).assertIsDisplayed()
    }

    @Test
    fun detailsScreenCurrent_celsius_windHasNoClick() {
        val text = composeTestRule.activity.getString(
            R.string.wind,
            FakeDataSource.fakeWeatherInfo.currentWeather.windSpeedKm
        )
        composeTestRule.onNodeWithText(text).assertHasNoClickAction()
    }

    @Test
    fun detailsScreenCurrent_celsius_windGustDisplayed() {
        val text = composeTestRule.activity.getString(
            R.string.wind_gust,
            FakeDataSource.fakeWeatherInfo.currentWeather.windGustKm
        )
        composeTestRule.onNodeWithText(text).assertIsDisplayed()
    }

    @Test
    fun detailsScreenCurrent_celsius_windGustHasNoClick() {
        val text = composeTestRule.activity.getString(
            R.string.wind_gust,
            FakeDataSource.fakeWeatherInfo.currentWeather.windGustKm
        )
        composeTestRule.onNodeWithText(text).assertHasNoClickAction()
    }

    @Test
    fun detailsScreenCurrent_celsius_precipitationDisplayed() {
        val text = composeTestRule.activity.getString(
            R.string.precipitation,
            FakeDataSource.fakeWeatherInfo.currentWeather.precipitationMm
        )
        composeTestRule.onNodeWithText(text).assertIsDisplayed()
    }

    @Test
    fun detailsScreenCurrent_celsius_precipitationHasNoClick() {
        val text = composeTestRule.activity.getString(
            R.string.precipitation,
            FakeDataSource.fakeWeatherInfo.currentWeather.precipitationMm
        )
        composeTestRule.onNodeWithText(text).assertHasNoClickAction()
    }

    @Test
    fun detailsScreenCurrent_celsius_pressureDisplayed() {
        val text = composeTestRule.activity.getString(
            R.string.pressure,
            FakeDataSource.fakeWeatherInfo.currentWeather.pressureMm
        )
        composeTestRule.onNodeWithText(text).assertIsDisplayed()
    }

    @Test
    fun detailsScreenCurrent_celsius_pressureHasNoClick() {
        val text = composeTestRule.activity.getString(
            R.string.pressure,
            FakeDataSource.fakeWeatherInfo.currentWeather.pressureMm
        )
        composeTestRule.onNodeWithText(text).assertHasNoClickAction()
    }

    @Test
    fun detailsScreenCurrent_celsius_visibilityDisplayed() {
        val text = composeTestRule.activity.getString(
            R.string.visibility,
            FakeDataSource.fakeWeatherInfo.currentWeather.visibleKm
        )
        composeTestRule.onNodeWithText(text).assertIsDisplayed()
    }

    @Test
    fun detailsScreenCurrent_celsius_visibilityHasNoClick() {
        val text = composeTestRule.activity.getString(
            R.string.visibility,
            FakeDataSource.fakeWeatherInfo.currentWeather.visibleKm
        )
        composeTestRule.onNodeWithText(text).assertHasNoClickAction()
    }
}