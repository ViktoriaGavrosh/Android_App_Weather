package com.viktoriagavrosh.weather.ui.detailscreen

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertIsNotDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import com.viktoriagavrosh.weather.R
import com.viktoriagavrosh.weather.fake.FakeDataSource
import com.viktoriagavrosh.weather.ui.screens.DetailsScreen
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class DetailsScreenAstroCurrentTest {

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
    fun detailsScreenCurrent_astro_sunIconNotDisplayed() {
        val text = composeTestRule.activity.getString(R.string.sun)
        composeTestRule.onNodeWithContentDescription(text).assertIsNotDisplayed()
    }

    @Test
    fun detailsScreenCurrent_astro_sunriseTimeNotDisplayed() {
        val text = FakeDataSource.fakeWeatherInfo.forecast.days[0].dayAstro.sunriseTime
        composeTestRule.onNodeWithText(text).assertIsNotDisplayed()
    }

    @Test
    fun detailsScreenCurrent_astro_sunsetTimeNotDisplayed() {
        val text = FakeDataSource.fakeWeatherInfo.forecast.days[0].dayAstro.sunsetTime
        composeTestRule.onNodeWithText(text).assertIsNotDisplayed()
    }

    @Test
    fun detailsScreenCurrent_astro_moonIconNotDisplayed() {
        val text = composeTestRule.activity.getString(R.string.moon)
        composeTestRule.onNodeWithContentDescription(text).assertIsNotDisplayed()
    }

    @Test
    fun detailsScreenCurrent_astro_moonriseTimeNotDisplayed() {
        val text = FakeDataSource.fakeWeatherInfo.forecast.days[0].dayAstro.moonriseTime
        composeTestRule.onNodeWithText(text).assertIsNotDisplayed()
    }

    @Test
    fun detailsScreenCurrent_astro_moonsetTimeNotDisplayed() {
        val text = FakeDataSource.fakeWeatherInfo.forecast.days[0].dayAstro.moonsetTime
        composeTestRule.onNodeWithText(text).assertIsNotDisplayed()
    }

    @Test
    fun detailsScreenCurrent_astro_moonPhaseTimeNotDisplayed() {
        val text = FakeDataSource.fakeWeatherInfo.forecast.days[0].dayAstro.moonPhase
        composeTestRule.onNodeWithText(text).assertIsNotDisplayed()
    }

    @Test
    fun detailsScreenCurrent_astro_sunriseIconNotDisplayed() {
        val text = FakeDataSource.fakeWeatherInfo.forecast.days[0].dayAstro.sunriseTime
        composeTestRule.onNodeWithTag(text).assertIsNotDisplayed()
    }

    @Test
    fun detailsScreenCurrent_astro_sunsetIconNotDisplayed() {
        val text = FakeDataSource.fakeWeatherInfo.forecast.days[0].dayAstro.sunsetTime
        composeTestRule.onNodeWithTag(text).assertIsNotDisplayed()
    }

    @Test
    fun detailsScreenCurrent_astro_moonriseIconNotDisplayed() {
        val text = FakeDataSource.fakeWeatherInfo.forecast.days[0].dayAstro.moonriseTime
        composeTestRule.onNodeWithTag(text).assertIsNotDisplayed()
    }

    @Test
    fun detailsScreenCurrent_astro_moonsetIconNotDisplayed() {
        val text = FakeDataSource.fakeWeatherInfo.forecast.days[0].dayAstro.moonsetTime
        composeTestRule.onNodeWithTag(text).assertIsNotDisplayed()
    }
}