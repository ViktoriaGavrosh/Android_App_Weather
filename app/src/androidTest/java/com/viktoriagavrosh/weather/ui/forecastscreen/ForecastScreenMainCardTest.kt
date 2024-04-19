package com.viktoriagavrosh.weather.ui.forecastscreen

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertHasClickAction
import androidx.compose.ui.test.assertHasNoClickAction
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsNotDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.viktoriagavrosh.weather.R
import com.viktoriagavrosh.weather.fake.FakeDataSource
import com.viktoriagavrosh.weather.ui.screens.ForecastScreen
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class ForecastScreenMainCardTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Before
    fun launchForecastScreen() {
        composeTestRule.setContent {
            ForecastScreen(
                days = FakeDataSource.fakeWeatherInfo.forecast.days,
                dateSelectedDay = FakeDataSource.fakeWeatherInfo.forecast.days[0].date,
                isCelsius = true,
                onDetailsClick = {}
            )
        }
    }

    @Test
    fun forecastScreenMainCard_day_dateDisplayed() {
        val tag = composeTestRule.activity.getString(R.string.date_or_time)
        composeTestRule.onNodeWithTag(tag).assertIsDisplayed()
    }

    @Test
    fun forecastScreenMainCard_day_dateHasNoClick() {
        val tag = composeTestRule.activity.getString(R.string.date_or_time)
        composeTestRule.onNodeWithTag(tag).assertHasNoClickAction()
    }

    @Test
    fun forecastScreenMainCard_hour_dateDisplayed() {
        switchToHour()
        val text = FakeDataSource.fakeWeatherInfo.forecast.days[0].hours[0].time
        composeTestRule.onNodeWithText(text).assertIsDisplayed()
    }

    @Test
    fun forecastScreenMainCard_hour_dateHasNoClick() {
        switchToHour()
        val text = FakeDataSource.fakeWeatherInfo.forecast.days[0].hours[0].time
        composeTestRule.onNodeWithText(text).assertHasNoClickAction()
    }

    @Test
    fun forecastScreenMainCard_day_conditionDisplayed() {
        val text = FakeDataSource.fakeWeatherInfo.forecast.days[0]
            .dayWeather.weatherCondition.condition
        composeTestRule.onNodeWithText(text).assertIsDisplayed()
    }

    @Test
    fun forecastScreenMainCard_day_conditionHasNoClick() {
        val text = FakeDataSource.fakeWeatherInfo.forecast.days[0]
            .dayWeather.weatherCondition.condition
        composeTestRule.onNodeWithText(text).assertHasNoClickAction()
    }

    @Test
    fun forecastScreenMainCard_hour_conditionDisplayed() {
        switchToHour()
        val text = FakeDataSource.fakeWeatherInfo.forecast.days[0]
            .hours[0].weatherCondition.condition
        composeTestRule.onNodeWithText(text).assertIsDisplayed()
    }

    @Test
    fun forecastScreenMainCard_hour_conditionHasNoClick() {
        switchToHour()
        val text = FakeDataSource.fakeWeatherInfo.forecast.days[0]
            .hours[0].weatherCondition.condition
        composeTestRule.onNodeWithText(text).assertHasNoClickAction()
    }

    @Test
    fun forecastScreenMainCard_day_iconDisplayed() {
        val text = FakeDataSource.fakeWeatherInfo.forecast.days[0]
            .dayWeather.weatherCondition.condition
        composeTestRule.onNodeWithContentDescription(text).assertIsDisplayed()
    }

    @Test
    fun forecastScreenMainCard_day_iconHasNoClick() {
        val text = FakeDataSource.fakeWeatherInfo.forecast.days[0]
            .dayWeather.weatherCondition.condition
        composeTestRule.onNodeWithContentDescription(text).assertHasNoClickAction()
    }

    @Test
    fun forecastScreenMainCard_hour_iconDisplayed() {
        switchToHour()
        val text = FakeDataSource.fakeWeatherInfo.forecast.days[0]
            .hours[0].weatherCondition.condition
        composeTestRule.onNodeWithContentDescription(text).assertIsDisplayed()
    }

    @Test
    fun forecastScreenMainCard_hour_iconHasNoClick() {
        switchToHour()
        val text = FakeDataSource.fakeWeatherInfo.forecast.days[0]
            .hours[0].weatherCondition.condition
        composeTestRule.onNodeWithContentDescription(text).assertHasNoClickAction()
    }

    @Test
    fun forecastScreenMainCard_day_humidityDisplayed() {
        val text = composeTestRule.activity.getString(
            R.string.humidity,
            FakeDataSource.fakeWeatherInfo.forecast.days[0]
                .dayWeather.humidityPercent
        )
        composeTestRule.onNodeWithText(text).assertIsDisplayed()
    }

    @Test
    fun forecastScreenMainCard_day_humidityHasNoClick() {
        val text = composeTestRule.activity.getString(
            R.string.humidity,
            FakeDataSource.fakeWeatherInfo.forecast.days[0].dayWeather.humidityPercent
        )
        composeTestRule.onNodeWithText(text).assertHasNoClickAction()
    }

    @Test
    fun forecastScreenMainCard_hour_humidityDisplayed() {
        switchToHour()
        val text = composeTestRule.activity.getString(
            R.string.humidity,
            FakeDataSource.fakeWeatherInfo.forecast.days[0].hours[0].humidityPercent
        )
        composeTestRule.onNodeWithText(text).assertIsDisplayed()
    }

    @Test
    fun forecastScreenMainCard_hour_humidityHasNoClick() {
        switchToHour()
        val text = composeTestRule.activity.getString(
            R.string.humidity,
            FakeDataSource.fakeWeatherInfo.forecast.days[0].hours[0].humidityPercent
        )
        composeTestRule.onNodeWithText(text).assertHasNoClickAction()
    }

    @Test
    fun forecastScreenMainCard_day_buttonDisplayed() {
        val test = composeTestRule.activity.getString(R.string.details)
        composeTestRule.onNodeWithContentDescription(test).assertIsDisplayed()
    }

    @Test
    fun forecastScreenMainCard_day_buttonHasClick() {
        val test = composeTestRule.activity.getString(R.string.details)
        composeTestRule.onNodeWithContentDescription(test).assertHasClickAction()
    }

    @Test
    fun forecastScreenMainCard_day_buttonNotDisplayed() {
        switchToHour()
        val test = composeTestRule.activity.getString(R.string.details)
        composeTestRule.onNodeWithContentDescription(test).assertIsNotDisplayed()
    }

    private fun switchToHour() {
        val text = FakeDataSource.fakeWeatherInfo.forecast.days[0]
            .hours[0].time.substringAfter(" ")
        composeTestRule.onNodeWithText(text).performClick()
    }

}