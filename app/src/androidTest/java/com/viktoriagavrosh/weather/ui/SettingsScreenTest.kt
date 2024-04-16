package com.viktoriagavrosh.weather.ui

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertHasClickAction
import androidx.compose.ui.test.assertHasNoClickAction
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsNotDisplayed
import androidx.compose.ui.test.assertIsNotSelected
import androidx.compose.ui.test.assertIsOn
import androidx.compose.ui.test.assertIsSelected
import androidx.compose.ui.test.assertIsToggleable
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import com.viktoriagavrosh.weather.R
import com.viktoriagavrosh.weather.fake.FakeDataSource
import com.viktoriagavrosh.weather.model.Wallpaper
import com.viktoriagavrosh.weather.ui.screens.SettingsScreen
import com.viktoriagavrosh.weather.utils.onNodeWithStringId
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class SettingsScreenTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Before
    fun launchSettingsScreen() {
        composeTestRule.setContent {
            SettingsScreen(
                isMusic = FakeDataSource.isMusic,
                isCelsius = FakeDataSource.isCelsius,
                wallpaperId = FakeDataSource.wallpaperId,
                onMusicClick = {},
                onCelsiusClick = {},
                onWallpaperClick = {}
            )
        }
    }

    @Test
    fun settingsScreen_backButtonExistOnTopBar() {
        val backText = composeTestRule.activity.getString(R.string.back)
        composeTestRule.onNodeWithContentDescription(backText).assertExists()
    }

    @Test
    fun settingsScreen_backButtonHasClick() {
        val backText = composeTestRule.activity.getString(R.string.back)
        composeTestRule.onNodeWithContentDescription(backText).assertHasClickAction()
    }

    @Test
    fun settingsScreen_settingsDisplayedOnTopBar() {
        composeTestRule.onNodeWithStringId(R.string.settings).assertIsDisplayed()
    }

    @Test
    fun settingsScreen_settingsHasNoClick() {
        composeTestRule.onNodeWithStringId(R.string.settings).assertHasNoClickAction()
    }

    @Test
    fun settingsScreen_settingsButtonNotDisplayedOnTopBar() {
        val settingsText = composeTestRule.activity.getString(R.string.settings)
        composeTestRule.onNodeWithContentDescription(settingsText).assertIsNotDisplayed()
    }

    @Test
    fun settingsScreen_musicDisplayed() {
        composeTestRule.onNodeWithStringId(R.string.music).assertIsDisplayed()
    }

    @Test
    fun settingsScreen_musicHasNoClick() {
        composeTestRule.onNodeWithStringId(R.string.music).assertHasNoClickAction()
    }

    @Test
    fun settingsScreen_musicSwitchDisplayed() {
        val tag = composeTestRule.activity.getString(R.string.music_switch)
        composeTestRule.onNodeWithTag(tag).assertIsDisplayed()
    }

    @Test
    fun settingsScreen_musicSwitchToggleable() {
        val tag = composeTestRule.activity.getString(R.string.music_switch)
        composeTestRule.onNodeWithTag(tag).assertIsToggleable()
    }

    @Test
    fun settingsScreen_musicSwitchOn() {
        val tag = composeTestRule.activity.getString(R.string.music_switch)
        composeTestRule.onNodeWithTag(tag).assertIsOn()
    }

    @Test
    fun settingsScreen_temperatureDisplayed() {
        composeTestRule.onNodeWithStringId(R.string.temperature).assertIsDisplayed()
    }

    @Test
    fun settingsScreen_temperatureHasNoClick() {
        composeTestRule.onNodeWithStringId(R.string.temperature).assertHasNoClickAction()
    }

    @Test
    fun settingsScreen_cRadioButtonDisplayed() {
        composeTestRule.onNodeWithStringId(R.string.temp_c).assertIsDisplayed()
    }

    @Test
    fun settingsScreen_cRadioButtonHasClick() {
        composeTestRule.onNodeWithStringId(R.string.temp_c).assertHasClickAction()
    }

    @Test
    fun settingsScreen_cRadioButtonSelected() {
        composeTestRule.onNodeWithStringId(R.string.temp_c).assertIsSelected()
    }

    @Test
    fun settingsScreen_fRadioButtonDisplayed() {
        composeTestRule.onNodeWithStringId(R.string.temp_f).assertIsDisplayed()
    }

    @Test
    fun settingsScreen_fRadioButtonHasClick() {
        composeTestRule.onNodeWithStringId(R.string.temp_f).assertHasClickAction()
    }

    @Test
    fun settingsScreen_fRadioButtonNotSelected() {
        composeTestRule.onNodeWithStringId(R.string.temp_f).assertIsNotSelected()
    }

    @Test
    fun settingsScreen_wallpaperDisplayed() {
        composeTestRule.onNodeWithStringId(R.string.wallpaper).assertIsDisplayed()
    }

    @Test
    fun settingsScreen_wallpaperHasNoClick() {
        composeTestRule.onNodeWithStringId(R.string.wallpaper).assertHasNoClickAction()
    }

    @Test
    fun settingsScreen_cloudyRadioButtonDisplayed() {
        composeTestRule.onNodeWithText(Wallpaper.CLOUDY.name).assertIsDisplayed()
    }

    @Test
    fun settingsScreen_cloudyRadioButtonHasClick() {
        composeTestRule.onNodeWithText(Wallpaper.CLOUDY.name).assertHasClickAction()
    }

    @Test
    fun settingsScreen_cloudyRadioButtonSelected() {
        composeTestRule.onNodeWithText(Wallpaper.CLOUDY.name).assertIsSelected()
    }

    @Test
    fun settingsScreen_skyRadioButtonDisplayed() {
        composeTestRule.onNodeWithText(Wallpaper.SKY.name).assertIsDisplayed()
    }

    @Test
    fun settingsScreen_skyRadioButtonHasClick() {
        composeTestRule.onNodeWithText(Wallpaper.SKY.name).assertHasClickAction()
    }

    @Test
    fun settingsScreen_skyRadioButtonNotSelected() {
        composeTestRule.onNodeWithText(Wallpaper.SKY.name).assertIsNotSelected()
    }

    @Test
    fun settingsScreen_sunnyRadioButtonDisplayed() {
        composeTestRule.onNodeWithText(Wallpaper.SUNNY.name).assertIsDisplayed()
    }

    @Test
    fun settingsScreen_sunnyRadioButtonHasClick() {
        composeTestRule.onNodeWithText(Wallpaper.SUNNY.name).assertHasClickAction()
    }

    @Test
    fun settingsScreen_sunnyRadioButtonNotSelected() {
        composeTestRule.onNodeWithText(Wallpaper.SUNNY.name).assertIsNotSelected()
    }
}