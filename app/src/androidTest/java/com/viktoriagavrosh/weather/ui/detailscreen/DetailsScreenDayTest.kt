package com.viktoriagavrosh.weather.ui.detailscreen

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertHasNoClickAction
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsNotDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import com.viktoriagavrosh.weather.R
import com.viktoriagavrosh.weather.fake.FakeDataSource
import com.viktoriagavrosh.weather.ui.screens.DetailsScreen
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class DetailsScreenDayTest {

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
    fun detailScreenDay_dateDisplayedOnTopBar() {
        val text = FakeDataSource.fakeWeatherInfo.forecast.days[0].date
        composeTestRule.onNodeWithText(text).assertIsDisplayed()
    }

    @Test
    fun detailScreenDay_nowHasNoClick() {
        val text = FakeDataSource.fakeWeatherInfo.forecast.days[0].date
        composeTestRule.onNodeWithText(text).assertHasNoClickAction()
    }

    @Test
    fun detailScreenDay_conditionDisplayed() {
        val text = FakeDataSource.fakeWeatherInfo.forecast.days[0]
            .dayWeather.weatherCondition.condition
        composeTestRule.onNodeWithText(text).assertIsDisplayed()
    }

    @Test
    fun detailScreenDay_conditionHasNoClick() {
        val text = FakeDataSource.fakeWeatherInfo.forecast.days[0]
            .dayWeather.weatherCondition.condition
        composeTestRule.onNodeWithText(text).assertHasNoClickAction()
    }

    @Test
    fun detailScreenDay_iconDisplayed() {
        val text = FakeDataSource.fakeWeatherInfo.forecast.days[0]
            .dayWeather.weatherCondition.condition
        composeTestRule.onNodeWithContentDescription(text).assertIsDisplayed()
    }

    @Test
    fun detailScreenDay_iconHasNoClick() {
        val text = FakeDataSource.fakeWeatherInfo.forecast.days[0]
            .dayWeather.weatherCondition.condition
        composeTestRule.onNodeWithContentDescription(text).assertHasNoClickAction()
    }

    @Test
    fun detailScreenDay_feelsLikeNotDisplayed() {
        val text = composeTestRule.activity.getString(
            R.string.feels_like,
            FakeDataSource.fakeWeatherInfo.currentWeather.feelsLikeTempC
        )
        composeTestRule.onNodeWithText(text).assertIsNotDisplayed()
    }

    @Test
    fun detailScreenDay_windGustNotDisplayed() {
        val text = composeTestRule.activity.getString(
            R.string.wind_gust,
            FakeDataSource.fakeWeatherInfo.currentWeather.windGustKm
        )
        composeTestRule.onNodeWithText(text).assertIsNotDisplayed()
    }

    @Test
    fun detailScreenDay_windDirectionNotDisplayed() {
        val text = composeTestRule.activity.getString(
            R.string.wind_direction,
            FakeDataSource.fakeWeatherInfo.currentWeather.windDirection
        )
        composeTestRule.onNodeWithText(text).assertIsNotDisplayed()
    }

    @Test
    fun detailScreenDay_humidityDisplayed() {
        val text = composeTestRule.activity.getString(
            R.string.humidity,
            FakeDataSource.fakeWeatherInfo.forecast.days[0].dayWeather.humidityPercent
        )
        composeTestRule.onNodeWithText(text).assertIsDisplayed()
    }

    @Test
    fun detailScreenDay_humidityHasNoClick() {
        val text = composeTestRule.activity.getString(
            R.string.humidity,
            FakeDataSource.fakeWeatherInfo.forecast.days[0].dayWeather.humidityPercent
        )
        composeTestRule.onNodeWithText(text).assertHasNoClickAction()
    }

    @Test
    fun detailScreenDay_cloudyNotDisplayed() {
        val text = composeTestRule.activity.getString(
            R.string.cloudy,
            FakeDataSource.fakeWeatherInfo.currentWeather.cloudy
        )
        composeTestRule.onNodeWithText(text).assertIsNotDisplayed()
    }

    @Test
    fun detailScreenDay_pressureNotDisplayed() {
        val text = composeTestRule.activity.getString(
            R.string.pressure,
            FakeDataSource.fakeWeatherInfo.currentWeather.pressureMm
        )
        composeTestRule.onNodeWithText(text).assertIsNotDisplayed()
    }

    @Test
    fun detailScreenDay_uvIndexDisplayed() {
        val text = composeTestRule.activity.getString(
            R.string.uv_index,
            FakeDataSource.fakeWeatherInfo.forecast.days[0].dayWeather.uVIndex
        )
        composeTestRule.onNodeWithText(text).assertIsDisplayed()
    }

    @Test
    fun detailScreenDay_uvIndexHasNoClick() {
        val text = composeTestRule.activity.getString(
            R.string.uv_index,
            FakeDataSource.fakeWeatherInfo.forecast.days[0].dayWeather.uVIndex
        )
        composeTestRule.onNodeWithText(text).assertHasNoClickAction()
    }
}