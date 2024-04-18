package com.viktoriagavrosh.weather.ui.detailscreen

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertHasNoClickAction
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import com.viktoriagavrosh.weather.R
import com.viktoriagavrosh.weather.fake.FakeDataSource
import com.viktoriagavrosh.weather.ui.screens.DetailsScreen
import com.viktoriagavrosh.weather.utils.onNodeWithStringId
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class DetailsScreenCurrentTest {

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
    fun detailScreenCurrent_nowDisplayedOnTopBar() {
        composeTestRule.onNodeWithStringId(R.string.now).assertIsDisplayed()
    }

    @Test
    fun detailScreenCurrent_nowHasNoClick() {
        composeTestRule.onNodeWithStringId(R.string.now).assertHasNoClickAction()
    }

    @Test
    fun detailScreenCurrent_conditionDisplayed() {
        val text = FakeDataSource.fakeWeatherInfo.currentWeather.weatherCondition.condition
        composeTestRule.onNodeWithText(text).assertIsDisplayed()
    }

    @Test
    fun detailScreenCurrent_conditionHasNoClick() {
        val text = FakeDataSource.fakeWeatherInfo.currentWeather.weatherCondition.condition
        composeTestRule.onNodeWithText(text).assertHasNoClickAction()
    }

    @Test
    fun detailScreenCurrent_iconDisplayed() {
        val text = FakeDataSource.fakeWeatherInfo.currentWeather.weatherCondition.condition
        composeTestRule.onNodeWithContentDescription(text).assertIsDisplayed()
    }

    @Test
    fun detailScreenCurrent_iconHasNoClick() {
        val text = FakeDataSource.fakeWeatherInfo.currentWeather.weatherCondition.condition
        composeTestRule.onNodeWithContentDescription(text).assertHasNoClickAction()
    }

    @Test
    fun detailScreenCurrent_windDirectionDisplayed() {
        val text = composeTestRule.activity.getString(
            R.string.wind_direction,
            FakeDataSource.fakeWeatherInfo.currentWeather.windDirection
        )
        composeTestRule.onNodeWithText(text).assertIsDisplayed()
    }

    @Test
    fun detailScreenCurrent_windDirectionHasNoClick() {
        val text = composeTestRule.activity.getString(
            R.string.wind_direction,
            FakeDataSource.fakeWeatherInfo.currentWeather.windDirection
        )
        composeTestRule.onNodeWithText(text).assertHasNoClickAction()
    }

    @Test
    fun detailScreenCurrent_humidityDisplayed() {
        val text = composeTestRule.activity.getString(
            R.string.humidity,
            FakeDataSource.fakeWeatherInfo.currentWeather.humidityPercent
        )
        composeTestRule.onNodeWithText(text).assertIsDisplayed()
    }

    @Test
    fun detailScreenCurrent_humidityHasNoClick() {
        val text = composeTestRule.activity.getString(
            R.string.humidity,
            FakeDataSource.fakeWeatherInfo.currentWeather.humidityPercent
        )
        composeTestRule.onNodeWithText(text).assertHasNoClickAction()
    }

    @Test
    fun detailScreenCurrent_cloudyDisplayed() {
        val text = composeTestRule.activity.getString(
            R.string.cloudy,
            FakeDataSource.fakeWeatherInfo.currentWeather.cloudy
        )
        composeTestRule.onNodeWithText(text).assertIsDisplayed()
    }

    @Test
    fun detailScreenCurrent_cloudyHasNoClick() {
        val text = composeTestRule.activity.getString(
            R.string.cloudy,
            FakeDataSource.fakeWeatherInfo.currentWeather.cloudy
        )
        composeTestRule.onNodeWithText(text).assertHasNoClickAction()
    }

    @Test
    fun detailScreenCurrent_uvIndexDisplayed() {
        val text = composeTestRule.activity.getString(
            R.string.uv_index,
            FakeDataSource.fakeWeatherInfo.currentWeather.uVIndex
        )
        composeTestRule.onNodeWithText(text).assertIsDisplayed()
    }

    @Test
    fun detailScreenCurrent_uvIndexHasNoClick() {
        val text = composeTestRule.activity.getString(
            R.string.uv_index,
            FakeDataSource.fakeWeatherInfo.currentWeather.uVIndex
        )
        composeTestRule.onNodeWithText(text).assertHasNoClickAction()
    }
}