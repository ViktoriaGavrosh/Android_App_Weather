package com.viktoriagavrosh.weather.ui.current

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertHasClickAction
import androidx.compose.ui.test.assertHasNoClickAction
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.viktoriagavrosh.weather.R
import com.viktoriagavrosh.weather.fake.FakeDataSource
import com.viktoriagavrosh.weather.ui.screens.CurrentWeatherScreen
import com.viktoriagavrosh.weather.utils.onNodeWithStringId
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class CurrentWeatherScreenTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Before
    fun launchCurrentScreen() {
        composeTestRule.setContent {
            CurrentWeatherScreen(
                city = "FakeCity",
                weatherInfo = FakeDataSource.weatherInfoForCurrentScreen,
                isCelsius = true,
                onDetailsClick = {}
            )
        }
    }

    @Test
    fun currentScreen_backButtonNotExistOnTopBar() {
        val backText = composeTestRule.activity.getString(R.string.back)
        composeTestRule.onNodeWithContentDescription(backText).assertDoesNotExist()
    }

    @Test
    fun currentScreen_cityDisplayedOnTopBar() {
        composeTestRule.onNodeWithText("FakeCity").assertIsDisplayed()
    }

    @Test
    fun currentScreen_cityHasClick() {
        composeTestRule.onNodeWithText("FakeCity").assertHasClickAction()
    }

    @Test
    fun currentScreen_settingsButtonDisplayedOnTopBar() {
        val settingsText = composeTestRule.activity.getString(R.string.settings)
        composeTestRule.onNodeWithContentDescription(settingsText).assertIsDisplayed()
    }

    @Test
    fun currentScreen_settingsHasClick() {
        val settingsText = composeTestRule.activity.getString(R.string.settings)
        composeTestRule.onNodeWithContentDescription(settingsText).assertHasClickAction()
    }

    @Test
    fun currentScreen_nowTabDisplayed() {
        composeTestRule.onNodeWithStringId(R.string.now).assertIsDisplayed()
    }

    @Test
    fun currentScreen_nowTabHasClick() {
        composeTestRule.onNodeWithStringId(R.string.now).assertHasClickAction()
    }

    @Test
    fun currentScreen_forecastTabDisplayed() {
        composeTestRule.onNodeWithStringId(R.string.forecast).assertIsDisplayed()
    }

    @Test
    fun currentScreen_forecastTabHasClick() {
        composeTestRule.onNodeWithStringId(R.string.forecast).assertHasClickAction()
    }

    @Test
    fun currentScreen_mainCard_conditionDisplayed() {
        composeTestRule.onNodeWithText(FakeDataSource.condition).assertIsDisplayed()
    }

    @Test
    fun currentScreen_mainCard_conditionHasNoClick() {
        composeTestRule.onNodeWithText(FakeDataSource.condition).assertHasNoClickAction()
    }

    @Test
    fun currentScreen_mainCard_iconDisplayed() {
        composeTestRule.onNodeWithContentDescription(FakeDataSource.condition)
            .assertIsDisplayed()
    }

    @Test
    fun currentScreen_mainCard_iconHasNoClick() {
        composeTestRule.onNodeWithContentDescription(FakeDataSource.condition)
            .assertHasNoClickAction()
    }

    @Test
    fun currentScreen_humidityDisplayed() {
        val text = composeTestRule.activity.getString(
            R.string.humidity,
            FakeDataSource.weatherInfoForCurrentScreen.currentWeather.humidityPercent
        )
        composeTestRule.onNodeWithText(text).assertIsDisplayed()
    }

    @Test
    fun currentScreen_humidityHasNoClick() {
        val text = composeTestRule.activity.getString(
            R.string.humidity,
            FakeDataSource.weatherInfoForCurrentScreen.currentWeather.humidityPercent
        )
        composeTestRule.onNodeWithText(text).assertHasNoClickAction()
    }

    @Test
    fun currentScreen_detailsButtonDisplayed() {
        val buttonText = composeTestRule.activity.getString(R.string.details)
        composeTestRule.onNodeWithContentDescription(buttonText).assertIsDisplayed()
    }

    @Test
    fun currentScreen_detailsButtonHasClick() {
        val buttonText = composeTestRule.activity.getString(R.string.details)
        composeTestRule.onNodeWithContentDescription(buttonText).assertHasClickAction()
    }

    @Test
    fun currentScreen_forecastTab_firstButtonDisplayed() {
        navigateToForecastTab()
        composeTestRule.onNodeWithText(FakeDataSource.days[0].date).assertIsDisplayed()
    }

    @Test
    fun currentScreen_forecastTab_firstButtonHasClick() {
        navigateToForecastTab()
        composeTestRule.onNodeWithText(FakeDataSource.days[0].date).assertHasClickAction()
    }

    @Test
    fun currentScreen_forecastTab_secondButtonDisplayed() {
        navigateToForecastTab()
        composeTestRule.onNodeWithText(FakeDataSource.days[1].date).assertIsDisplayed()
    }

    @Test
    fun currentScreen_forecastTab_secondButtonHasClick() {
        navigateToForecastTab()
        composeTestRule.onNodeWithText(FakeDataSource.days[1].date).assertHasClickAction()
    }


    @Test
    fun currentScreen_forecastTab_thirdButtonDisplayed() {
        navigateToForecastTab()
        composeTestRule.onNodeWithText(FakeDataSource.days[2].date).assertIsDisplayed()
    }

    @Test
    fun currentScreen_forecastTab_thirdButtonHasClick() {
        navigateToForecastTab()
        composeTestRule.onNodeWithText(FakeDataSource.days[2].date).assertHasClickAction()
    }

    private fun navigateToForecastTab() {
        composeTestRule.onNodeWithStringId(R.string.forecast).performClick()
    }
}