package com.viktoriagavrosh.weather.ui

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertHasClickAction
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsNotDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import com.viktoriagavrosh.weather.R
import com.viktoriagavrosh.weather.fake.FakeDataSource
import com.viktoriagavrosh.weather.ui.screens.CurrentWeatherScreen
import com.viktoriagavrosh.weather.utils.onNodeWithStringId
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class ChangeCityDialogTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Before
    fun launchCurrentScreen() {
        composeTestRule.setContent {
            CurrentWeatherScreen(
                city = "FakeCity",
                weatherInfo = FakeDataSource.fakeWeatherInfo,
                isCelsius = true,
                onDetailsClick = {}
            )
        }
    }

    @Test
    fun cityDialog_clickOnCity_cityDialogDisplayed() {
        showCityDialog()
        composeTestRule.onNodeWithStringId(R.string.cancel).assertIsDisplayed()
    }

    @Test
    fun cityDialog_cancelButtonDisplayed() {
        showCityDialog()
        composeTestRule.onNodeWithStringId(R.string.cancel).assertIsDisplayed()
    }

    @Test
    fun cityDialog_cancelButtonHasClick() {
        showCityDialog()
        composeTestRule.onNodeWithStringId(R.string.cancel).assertHasClickAction()
    }

    @Test
    fun cityDialog_cancelButtonClick_cityDialogNotDisplayed() {
        showCityDialog()
        composeTestRule.onNodeWithStringId(R.string.cancel).performClick()
        composeTestRule.onNodeWithStringId(R.string.cancel).assertIsNotDisplayed()
    }

    @Test
    fun cityDialog_okButtonDisplayed() {
        showCityDialog()
        composeTestRule.onNodeWithStringId(R.string.ok).assertIsDisplayed()
    }

    @Test
    fun cityDialog_okButtonHasClick() {
        showCityDialog()
        composeTestRule.onNodeWithStringId(R.string.ok).assertHasClickAction()
    }

    @Test
    fun cityDialog_okButtonClick_cityDialogNotDisplayed() {
        showCityDialog()
        composeTestRule.onNodeWithStringId(R.string.ok).performClick()
        composeTestRule.onNodeWithStringId(R.string.ok).assertIsNotDisplayed()
    }

    @Test
    fun cityDialog_textFieldDisplayed() {
        showCityDialog()
        composeTestRule.onNodeWithStringId(R.string.enter_city).assertIsDisplayed()
    }

    @Test
    fun cityDialog_textFieldNotSelected() {
        showCityDialog()
        val text = "test"
        composeTestRule.onNodeWithStringId(R.string.enter_city).performTextInput(text)
        composeTestRule.onNodeWithText(text).assertIsDisplayed()
    }

    private fun showCityDialog() {
        composeTestRule.onNodeWithText("FakeCity").performClick()
    }
}