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

class DetailsScreenDayFahrenheitTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Before
    fun launchDetailScreen() {
        composeTestRule.setContent {
            DetailsScreen(
                weatherInfo = FakeDataSource.fakeWeatherInfo,
                dateSelectedDay = FakeDataSource.fakeWeatherInfo.forecast.days[0].date,
                isCelsius = false
            )
        }
    }

    @Test
    fun detailsScreenDay_fahrenheit_tempDisplayed() {
        val text = FakeDataSource.fakeWeatherInfo.forecast.days[0].dayWeather.tempF
        composeTestRule.onNodeWithText(text).assertIsDisplayed()
    }

    @Test
    fun detailsScreenDay_fahrenheit_tempHasNoClick() {
        val text = FakeDataSource.fakeWeatherInfo.forecast.days[0].dayWeather.tempF
        composeTestRule.onNodeWithText(text).assertHasNoClickAction()
    }

    @Test
    fun detailsScreenDay_fahrenheit_windDisplayed() {
        val text = composeTestRule.activity.getString(
            R.string.wind,
            FakeDataSource.fakeWeatherInfo.forecast.days[0].dayWeather.windSpeedMile
        )
        composeTestRule.onNodeWithText(text).assertIsDisplayed()
    }

    @Test
    fun detailsScreenDay_fahrenheit_windHasNoClick() {
        val text = composeTestRule.activity.getString(
            R.string.wind,
            FakeDataSource.fakeWeatherInfo.forecast.days[0].dayWeather.windSpeedMile
        )
        composeTestRule.onNodeWithText(text).assertHasNoClickAction()
    }

    @Test
    fun detailsScreenDay_fahrenheit_precipitationDisplayed() {
        val text = composeTestRule.activity.getString(
            R.string.precipitation,
            FakeDataSource.fakeWeatherInfo.forecast.days[0].dayWeather.precipitationInch
        )
        composeTestRule.onNodeWithText(text).assertIsDisplayed()
    }

    @Test
    fun detailsScreenDay_fahrenheit_precipitationHasNoClick() {
        val text = composeTestRule.activity.getString(
            R.string.precipitation,
            FakeDataSource.fakeWeatherInfo.forecast.days[0].dayWeather.precipitationInch
        )
        composeTestRule.onNodeWithText(text).assertHasNoClickAction()
    }

    @Test
    fun detailsScreenDay_fahrenheit_visibilityDisplayed() {
        val text = composeTestRule.activity.getString(
            R.string.visibility,
            FakeDataSource.fakeWeatherInfo.forecast.days[0].dayWeather.visibleMile
        )
        composeTestRule.onNodeWithText(text).assertIsDisplayed()
    }

    @Test
    fun detailsScreenDay_fahrenheit_visibilityHasNoClick() {
        val text = composeTestRule.activity.getString(
            R.string.visibility,
            FakeDataSource.fakeWeatherInfo.forecast.days[0].dayWeather.visibleMile
        )
        composeTestRule.onNodeWithText(text).assertHasNoClickAction()
    }
}