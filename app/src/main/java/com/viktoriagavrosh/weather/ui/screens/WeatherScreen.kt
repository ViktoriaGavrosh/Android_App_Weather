package com.viktoriagavrosh.weather.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.viktoriagavrosh.weather.model.apimodel.CurrentWeather
import com.viktoriagavrosh.weather.model.apimodel.DayWeather
import com.viktoriagavrosh.weather.ui.WeatherViewModel
import com.viktoriagavrosh.weather.ui.elements.WeatherTopBar
import com.viktoriagavrosh.weather.ui.util.Screen

@Composable
fun WeatherScreen(
    viewModel: WeatherViewModel = viewModel(factory = WeatherViewModel.Factory),
    navController: NavHostController = rememberNavController()
) {
    val uiState by viewModel.uiState.collectAsState()

    val backStackEntry by navController.currentBackStackEntryAsState()
    val selectedScreen = Screen.valueOf(
        backStackEntry?.destination?.route ?: Screen.CurrentWeather.name
    )


    // TODO add onBackClick = { navController.navigateUp() }

    Column(
        modifier = Modifier.fillMaxSize(),
    ) {
        WeatherTopBar(
            selectedScreen = selectedScreen,
            weatherState = uiState,
            onCityClick = { TODO() },
            onBackClick = {
                if (selectedScreen == Screen.Forecast) {
                    navController.navigate(Screen.CurrentWeather.name)
                } else {
                    navController.navigateUp()
                }
            },
            onSettingsClick = { navController.navigate(Screen.Settings.name) }
        )
        NavHost(
            navController = navController,
            startDestination = Screen.CurrentWeather.name
        ) {
            composable(route = Screen.CurrentWeather.name) {
                CurrentWeatherScreen(
                    weatherInfo = uiState.weatherInfo,
                    onDetailsClick = { weather ->
                        viewModel.selectWeather(weather = weather)
                        navController.navigate(Screen.Details.name)
                    },
                    onForecastClick = { day ->
                        viewModel.selectDay(day)
                        navController.navigate(Screen.Forecast.name)
                    }
                )
            }
            composable(route = Screen.Forecast.name) {
                ForecastScreen(
                    weatherInfo = uiState.weatherInfo,
                    selectedDay = uiState.selectedDay,
                    onDetailsClick = { weather ->
                        viewModel.selectWeather(weather = weather)
                        navController.navigate(Screen.Details.name)
                    },
                    onTabClick = viewModel::selectDayByDate
                )
            }
            composable(route = Screen.Details.name) {
                when (uiState.selectedWeather) {
                    is CurrentWeather -> {
                        DetailsScreen(
                            weatherDetails = uiState.weatherInfo.currentWeather
                        )
                    }

                    is DayWeather -> {
                        DetailsScreen(
                            weatherDetails = uiState.selectedWeather,
                            dayAstro = uiState.selectedDay.dayAstro
                        )
                    }
                }
            }
            composable(route = Screen.Settings.name) {
                SettingsScreen(
                    settings = uiState.settings,
                    onMusicClick = viewModel::changeMusic,
                    onCelsiusClick = viewModel::changeCelsius,
                    onWallpaperClick = viewModel::changeWallpaper
                )
            }
        }
    }
}