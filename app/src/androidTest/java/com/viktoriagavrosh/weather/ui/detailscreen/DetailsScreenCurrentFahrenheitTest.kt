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

class DetailsScreenCurrentFahrenheitTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Before
    fun launchDetailScreen() {
        composeTestRule.setContent {
            DetailsScreen(
                weatherInfo = FakeDataSource.fakeWeatherInfo,
                dateSelectedDay = "",
                isCelsius = false
            )
        }
    }

    @Test
    fun detailsScreenCurrent_fahrenheit_tempDisplayed() {
        val text = FakeDataSource.fakeWeatherInfo.currentWeather.tempF
        composeTestRule.onNodeWithText(text).assertIsDisplayed()
    }

    @Test
    fun detailsScreenCurrent_fahrenheit_tempHasNoClick() {
        val text = FakeDataSource.fakeWeatherInfo.currentWeather.tempF
        composeTestRule.onNodeWithText(text).assertHasNoClickAction()
    }

    @Test
    fun detailsScreenCurrent_fahrenheit_feelsLikeDisplayed() {
        val text = composeTestRule.activity.getString(
            R.string.feels_like,
            FakeDataSource.fakeWeatherInfo.currentWeather.feelsLikeTempF
        )
        composeTestRule.onNodeWithText(text).assertIsDisplayed()
    }

    @Test
    fun detailsScreenCurrent_fahrenheit_feelsLikeHasNoClick() {
        val text = composeTestRule.activity.getString(
            R.string.feels_like,
            FakeDataSource.fakeWeatherInfo.currentWeather.feelsLikeTempF
        )
        composeTestRule.onNodeWithText(text).assertHasNoClickAction()
    }

    @Test
    fun detailsScreenCurrent_fahrenheit_windDisplayed() {
        val text = composeTestRule.activity.getString(
            R.string.wind,
            FakeDataSource.fakeWeatherInfo.currentWeather.windSpeedMile
        )
        composeTestRule.onNodeWithText(text).assertIsDisplayed()
    }

    @Test
    fun detailsScreenCurrent_fahrenheit_windHasNoClick() {
        val text = composeTestRule.activity.getString(
            R.string.wind,
            FakeDataSource.fakeWeatherInfo.currentWeather.windSpeedMile
        )
        composeTestRule.onNodeWithText(text).assertHasNoClickAction()
    }

    @Test
    fun detailsScreenCurrent_fahrenheit_windGustDisplayed() {
        val text = composeTestRule.activity.getString(
            R.string.wind_gust,
            FakeDataSource.fakeWeatherInfo.currentWeather.windGustMile
        )
        composeTestRule.onNodeWithText(text).assertIsDisplayed()
    }

    @Test
    fun detailsScreenCurrent_fahrenheit_windGustHasNoClick() {
        val text = composeTestRule.activity.getString(
            R.string.wind_gust,
            FakeDataSource.fakeWeatherInfo.currentWeather.windGustMile
        )
        composeTestRule.onNodeWithText(text).assertHasNoClickAction()
    }

    @Test
    fun detailsScreenCurrent_fahrenheit_precipitationDisplayed() {
        val text = composeTestRule.activity.getString(
            R.string.precipitation,
            FakeDataSource.fakeWeatherInfo.currentWeather.precipitationInch
        )
        composeTestRule.onNodeWithText(text).assertIsDisplayed()
    }

    @Test
    fun detailsScreenCurrent_fahrenheit_precipitationHasNoClick() {
        val text = composeTestRule.activity.getString(
            R.string.precipitation,
            FakeDataSource.fakeWeatherInfo.currentWeather.precipitationInch
        )
        composeTestRule.onNodeWithText(text).assertHasNoClickAction()
    }

    @Test
    fun detailsScreenCurrent_fahrenheit_pressureDisplayed() {
        val text = composeTestRule.activity.getString(
            R.string.pressure,
            FakeDataSource.fakeWeatherInfo.currentWeather.pressureIn
        )
        composeTestRule.onNodeWithText(text).assertIsDisplayed()
    }

    @Test
    fun detailsScreenCurrent_fahrenheit_pressureHasNoClick() {
        val text = composeTestRule.activity.getString(
            R.string.pressure,
            FakeDataSource.fakeWeatherInfo.currentWeather.pressureIn
        )
        composeTestRule.onNodeWithText(text).assertHasNoClickAction()
    }

    @Test
    fun detailsScreenCurrent_fahrenheit_visibilityDisplayed() {
        val text = composeTestRule.activity.getString(
            R.string.visibility,
            FakeDataSource.fakeWeatherInfo.currentWeather.visibleMile
        )
        composeTestRule.onNodeWithText(text).assertIsDisplayed()
    }

    @Test
    fun detailsScreenCurrent_fahrenheit_visibilityHasNoClick() {
        val text = composeTestRule.activity.getString(
            R.string.visibility,
            FakeDataSource.fakeWeatherInfo.currentWeather.visibleMile
        )
        composeTestRule.onNodeWithText(text).assertHasNoClickAction()
    }
}