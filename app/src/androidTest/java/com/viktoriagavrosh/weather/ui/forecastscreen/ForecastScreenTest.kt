package com.viktoriagavrosh.weather.ui.forecastscreen

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertHasClickAction
import androidx.compose.ui.test.assertHasNoClickAction
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import com.viktoriagavrosh.weather.R
import com.viktoriagavrosh.weather.fake.FakeDataSource
import com.viktoriagavrosh.weather.ui.screens.ForecastScreen
import com.viktoriagavrosh.weather.utils.onNodeWithStringId
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class ForecastScreenTest {

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
    fun forecastScreen_backButtonExistOnTopBar() {
        val backText = composeTestRule.activity.getString(R.string.back)
        composeTestRule.onNodeWithContentDescription(backText).assertExists()
    }

    @Test
    fun forecastScreen_backButtonHasClick() {
        val backText = composeTestRule.activity.getString(R.string.back)
        composeTestRule.onNodeWithContentDescription(backText).assertHasClickAction()
    }

    @Test
    fun forecastScreen_forecastDisplayedOnTopBar() {
        composeTestRule.onNodeWithStringId(R.string.forecast_top_bar).assertIsDisplayed()
    }

    @Test
    fun forecastScreen_forecastHasNoClick() {
        composeTestRule.onNodeWithStringId(R.string.forecast_top_bar).assertHasNoClickAction()
    }

    @Test
    fun forecastScreen_settingsButtonDisplayedOnTopBar() {
        val settingsText = composeTestRule.activity.getString(R.string.settings)
        composeTestRule.onNodeWithContentDescription(settingsText).assertIsDisplayed()
    }

    @Test
    fun forecastScreen_settingsButtonHasClick() {
        val settingsText = composeTestRule.activity.getString(R.string.settings)
        composeTestRule.onNodeWithContentDescription(settingsText).assertHasClickAction()
    }

    @Test
    fun forecastScreen_firstTabDisplayed() {
        val tag = FakeDataSource.daysForForecastScreen[0].date
            .substringAfter("-")
            .replace("-", "/")
        composeTestRule.onNodeWithTag(tag).assertIsDisplayed()
    }

    @Test
    fun forecastScreen_firstTabHasClick() {
        val tag = FakeDataSource.daysForForecastScreen[0].date
            .substringAfter("-")
            .replace("-", "/")
        composeTestRule.onNodeWithTag(tag).assertHasClickAction()
    }

    @Test
    fun forecastScreen_secondTabDisplayed() {
        val tag = FakeDataSource.daysForForecastScreen[1].date
            .substringAfter("-")
            .replace("-", "/")
        composeTestRule.onNodeWithTag(tag).assertIsDisplayed()
    }

    @Test
    fun forecastScreen_secondTabHasClick() {
        val tag = FakeDataSource.daysForForecastScreen[1].date
            .substringAfter("-")
            .replace("-", "/")
        composeTestRule.onNodeWithTag(tag).assertHasClickAction()
    }

    @Test
    fun forecastScreen_thirdTabDisplayed() {
        val tag = FakeDataSource.daysForForecastScreen[2].date
            .substringAfter("-")
            .replace("-", "/")
        composeTestRule.onNodeWithTag(tag).assertIsDisplayed()
    }

    @Test
    fun forecastScreen_thirdTabHasClick() {
        val tag = FakeDataSource.daysForForecastScreen[2].date
            .substringAfter("-")
            .replace("-", "/")
        composeTestRule.onNodeWithTag(tag).assertHasClickAction()
    }

    @Test
    fun forecastScreen_HourCard_timeDisplayed() {
        val text = FakeDataSource.daysForForecastScreen[0].hours[0].time.substringAfter(" ")
        composeTestRule.onNodeWithText(text).assertIsDisplayed()
    }

    // time has click, because HourCard has click
    @Test
    fun forecastScreen_hourCard_timeHasNoClick() {
        val text = FakeDataSource.daysForForecastScreen[0].hours[0].time.substringAfter(" ")
        composeTestRule.onNodeWithText(text).assertHasClickAction()
    }

    @Test
    fun forecastScreen_hourCard_iconDisplayed() {
        val text = composeTestRule.activity.getString(
            R.string.icon_description,
            FakeDataSource.daysForForecastScreen[0].hours[0].weatherCondition.condition
            )
        composeTestRule.onNodeWithContentDescription(text).assertIsDisplayed()
    }
}