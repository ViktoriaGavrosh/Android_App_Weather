package com.viktoriagavrosh.weather.ui.detailscreen

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertHasNoClickAction
import androidx.compose.ui.test.assertIsDisplayed
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

class DetailsScreenAstroDayTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Before
    fun launchDetailScreen() {
        composeTestRule.setContent {
            DetailsScreen(
                weatherInfo = FakeDataSource.fakeWeatherInfo,
                dateSelectedDay = FakeDataSource.fakeWeatherInfo.forecast.days[0].date,
                isCelsius = true
            )
        }
    }

    @Test
    fun detailsScreenDay_astro_sunIconDisplayed() {
        val text = composeTestRule.activity.getString(R.string.sun)
        composeTestRule.onNodeWithContentDescription(text).assertIsDisplayed()
    }

    @Test
    fun detailsScreenDay_astro_sunIconHasNoClick() {
        val text = composeTestRule.activity.getString(R.string.sun)
        composeTestRule.onNodeWithContentDescription(text).assertHasNoClickAction()
    }

    @Test
    fun detailsScreenDay_astro_sunriseTimeDisplayed() {
        val text = FakeDataSource.fakeWeatherInfo.forecast.days[0].dayAstro.sunriseTime
        composeTestRule.onNodeWithText(text).assertIsDisplayed()
    }

    @Test
    fun detailsScreenDay_astro_sunriseTimeHasNoClick() {
        val text = FakeDataSource.fakeWeatherInfo.forecast.days[0].dayAstro.sunriseTime
        composeTestRule.onNodeWithText(text).assertHasNoClickAction()
    }

    @Test
    fun detailsScreenDay_astro_sunsetTimeDisplayed() {
        val text = FakeDataSource.fakeWeatherInfo.forecast.days[0].dayAstro.sunsetTime
        composeTestRule.onNodeWithText(text).assertIsDisplayed()
    }

    @Test
    fun detailsScreenDay_astro_sunsetTimeHasNoClick() {
        val text = FakeDataSource.fakeWeatherInfo.forecast.days[0].dayAstro.sunsetTime
        composeTestRule.onNodeWithText(text).assertHasNoClickAction()
    }

    @Test
    fun detailsScreenDay_astro_moonIconDisplayed() {
        val text = composeTestRule.activity.getString(R.string.moon)
        composeTestRule.onNodeWithContentDescription(text).assertIsDisplayed()
    }

    @Test
    fun detailsScreenDay_astro_moonIconHasNoClick() {
        val text = composeTestRule.activity.getString(R.string.moon)
        composeTestRule.onNodeWithContentDescription(text).assertHasNoClickAction()
    }

    @Test
    fun detailsScreenDay_astro_moonriseTimeDisplayed() {
        val text = FakeDataSource.fakeWeatherInfo.forecast.days[0].dayAstro.moonriseTime
        composeTestRule.onNodeWithText(text).assertIsDisplayed()
    }

    @Test
    fun detailsScreenDay_astro_moonriseTimeHasNoClick() {
        val text = FakeDataSource.fakeWeatherInfo.forecast.days[0].dayAstro.moonriseTime
        composeTestRule.onNodeWithText(text).assertHasNoClickAction()
    }

    @Test
    fun detailsScreenDay_astro_moonsetTimeDisplayed() {
        val text = FakeDataSource.fakeWeatherInfo.forecast.days[0].dayAstro.moonsetTime
        composeTestRule.onNodeWithText(text).assertIsDisplayed()
    }

    @Test
    fun detailsScreenDay_astro_moonsetTimeHasNoClick() {
        val text = FakeDataSource.fakeWeatherInfo.forecast.days[0].dayAstro.moonsetTime
        composeTestRule.onNodeWithText(text).assertHasNoClickAction()
    }

    @Test
    fun detailsScreenDay_astro_moonPhaseTimeDisplayed() {
        val text = FakeDataSource.fakeWeatherInfo.forecast.days[0].dayAstro.moonPhase
        composeTestRule.onNodeWithText(text).assertIsDisplayed()
    }

    @Test
    fun detailsScreenDay_astro_moonPhaseTimeHasNoClick() {
        val text = FakeDataSource.fakeWeatherInfo.forecast.days[0].dayAstro.moonPhase
        composeTestRule.onNodeWithText(text).assertHasNoClickAction()
    }

    @Test
    fun detailsScreenDay_astro_sunriseIconDisplayed() {
        val text = FakeDataSource.fakeWeatherInfo.forecast.days[0].dayAstro.sunriseTime
        composeTestRule.onNodeWithTag(text).assertIsDisplayed()
    }

    @Test
    fun detailsScreenDay_astro_sunriseIconHasNoClick() {
        val text = FakeDataSource.fakeWeatherInfo.forecast.days[0].dayAstro.sunriseTime
        composeTestRule.onNodeWithTag(text).assertHasNoClickAction()
    }

    @Test
    fun detailsScreenDay_astro_sunsetIconDisplayed() {
        val text = FakeDataSource.fakeWeatherInfo.forecast.days[0].dayAstro.sunsetTime
        composeTestRule.onNodeWithTag(text).assertIsDisplayed()
    }

    @Test
    fun detailsScreenDay_astro_sunsetIconHasNoClick() {
        val text = FakeDataSource.fakeWeatherInfo.forecast.days[0].dayAstro.sunsetTime
        composeTestRule.onNodeWithTag(text).assertHasNoClickAction()
    }

    @Test
    fun detailsScreenDay_astro_moonriseIconDisplayed() {
        val text = FakeDataSource.fakeWeatherInfo.forecast.days[0].dayAstro.moonriseTime
        composeTestRule.onNodeWithTag(text).assertIsDisplayed()
    }

    @Test
    fun detailsScreenDay_astro_moonriseIconHasNoClick() {
        val text = FakeDataSource.fakeWeatherInfo.forecast.days[0].dayAstro.moonriseTime
        composeTestRule.onNodeWithTag(text).assertHasNoClickAction()
    }

    @Test
    fun detailsScreenDay_astro_moonsetIconDisplayed() {
        val text = FakeDataSource.fakeWeatherInfo.forecast.days[0].dayAstro.moonsetTime
        composeTestRule.onNodeWithTag(text).assertIsDisplayed()
    }

    @Test
    fun detailsScreenDay_astro_moonsetIconHasNoClick() {
        val text = FakeDataSource.fakeWeatherInfo.forecast.days[0].dayAstro.moonsetTime
        composeTestRule.onNodeWithTag(text).assertHasNoClickAction()
    }
}