package com.viktoriagavrosh.weather.navigation

import androidx.activity.ComponentActivity
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.testing.TestNavHostController
import com.viktoriagavrosh.weather.R
import com.viktoriagavrosh.weather.ui.WeatherApp
import com.viktoriagavrosh.weather.ui.navigation.NavigationDestination
import com.viktoriagavrosh.weather.utils.assertCurrentRouteName
import com.viktoriagavrosh.weather.utils.onNodeWithStringId
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class WeatherNavHostTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    private lateinit var navController: TestNavHostController

    @Before
    fun setupWeatherNavHost() {
        composeTestRule.setContent {
            navController = TestNavHostController(LocalContext.current).apply {
                navigatorProvider.addNavigator(ComposeNavigator())
            }
            WeatherApp(
                navController = navController
            )
        }
    }

    @Test
    fun weatherNavHost_verifyStartDestination() {
        navController.assertCurrentRouteName(NavigationDestination.CurrentWeatherDestination.route)
    }

    @Test
    fun weatherNavHost_clickFirstDay_navigateToForecastScreen() {
        navigateToForecastScreen()
        navController
            .assertCurrentRouteName("${NavigationDestination.ForecastDestination.route}/{itemId}")
    }

    @Test
    fun weatherNavHost_clickDetailButtonOnCurrentWeatherScreen_navigateToDetailScreen() {
        navigateToDetailsScreen()
        navController
            .assertCurrentRouteName("${NavigationDestination.DetailsDestination.route}/{itemId}")
    }

    @Test
    fun weatherNavHost_clickDetailButtonOnForecastWeatherScreen_navigateToDetailScreen() {
        navigateToForecastScreen()
        navigateToDetailsScreen()
        navController
            .assertCurrentRouteName("${NavigationDestination.DetailsDestination.route}/{itemId}")
    }

    @Test
    fun weatherNavHost_clickSettingsIconOnCurrentScreen_navigateToSettingsScreen() {
        navigateToSettingsScreen()
        navController
            .assertCurrentRouteName(NavigationDestination.SettingsDestination.route)
    }

    @Test
    fun weatherNavHost_clickSettingsIconOnForecastScreen_navigateToSettingsScreen() {
        navigateToForecastScreen()
        navigateToSettingsScreen()
        navController
            .assertCurrentRouteName(NavigationDestination.SettingsDestination.route)
    }

    @Test
    fun weatherNavHost_clickSettingsIconOnDetailsScreen_navigateToSettingsScreen() {
        navigateToDetailsScreen()
        navigateToSettingsScreen()
        navController
            .assertCurrentRouteName(NavigationDestination.SettingsDestination.route)
    }

    @Test
    fun weatherNavHost_clickBackOnForecastScreen_navigateToCurrentScreen() {
        navigateToForecastScreen()
        performNavigateUp()
        navController
            .assertCurrentRouteName(NavigationDestination.CurrentWeatherDestination.route)
    }

    @Test
    fun weatherNavHost_clickBackOnDetailsScreen_navigateToCurrentScreen() {
        navigateToDetailsScreen()
        performNavigateUp()
        navController
            .assertCurrentRouteName(NavigationDestination.CurrentWeatherDestination.route)
    }

    @Test
    fun weatherNavHost_clickBackOnDetailsScreen_navigateToForecastScreen() {
        navigateToForecastScreen()
        navigateToDetailsScreen()
        performNavigateUp()
        navController
            .assertCurrentRouteName("${NavigationDestination.ForecastDestination.route}/{itemId}")
    }

    @Test
    fun weatherNavHost_clickBackOnSettingsScreen_navigateToCurrentScreen() {
        navigateToSettingsScreen()
        performNavigateUp()
        navController
            .assertCurrentRouteName(NavigationDestination.CurrentWeatherDestination.route)
    }

    @Test
    fun weatherNavHost_clickBackOnSettingsScreen_navigateToForecastScreen() {
        navigateToForecastScreen()
        navigateToSettingsScreen()
        performNavigateUp()
        navController
            .assertCurrentRouteName("${NavigationDestination.ForecastDestination.route}/{itemId}")
    }

    @Test
    fun weatherNavHost_clickBackOnSettingsScreen_navigateToDetailScreen() {
        navigateToDetailsScreen()
        navigateToSettingsScreen()
        performNavigateUp()
        navController
            .assertCurrentRouteName("${NavigationDestination.DetailsDestination.route}/{itemId}")
    }

    private fun navigateToForecastScreen() {
        composeTestRule.onNodeWithStringId(R.string.forecast).performClick()
        composeTestRule.onNodeWithTag("Button 0").performClick()
    }

    private fun navigateToDetailsScreen() {
        val navText = composeTestRule.activity.getString(R.string.details)
        composeTestRule.onNodeWithContentDescription(navText).performClick()
    }

    private fun navigateToSettingsScreen() {
        val navText = composeTestRule.activity.getString(R.string.settings)
        composeTestRule.onNodeWithContentDescription(navText).performClick()
    }

    private fun performNavigateUp() {
        val backText = composeTestRule.activity.getString(R.string.back)
        composeTestRule.onNodeWithContentDescription(backText).performClick()
    }
}
