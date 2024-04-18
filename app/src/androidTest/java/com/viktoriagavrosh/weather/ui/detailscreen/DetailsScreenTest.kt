package com.viktoriagavrosh.weather.ui.detailscreen

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertHasClickAction
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import com.viktoriagavrosh.weather.R
import com.viktoriagavrosh.weather.fake.FakeDataSource
import com.viktoriagavrosh.weather.ui.screens.DetailsScreen
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class DetailsScreenTest {

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
    fun detailsScreen_backButtonExistOnTopBar() {
        val backText = composeTestRule.activity.getString(R.string.back)
        composeTestRule.onNodeWithContentDescription(backText).assertExists()
    }

    @Test
    fun detailsScreen_backButtonHasClick() {
        val backText = composeTestRule.activity.getString(R.string.back)
        composeTestRule.onNodeWithContentDescription(backText).assertHasClickAction()
    }

    @Test
    fun detailsScreen_settingsButtonDisplayedOnTopBar() {
        val settingsText = composeTestRule.activity.getString(R.string.settings)
        composeTestRule.onNodeWithContentDescription(settingsText).assertIsDisplayed()
    }

    @Test
    fun detailsScreen_settingsButtonHasClick() {
        val settingsText = composeTestRule.activity.getString(R.string.settings)
        composeTestRule.onNodeWithContentDescription(settingsText).assertHasClickAction()
    }
}