package com.viktoriagavrosh.weather.ui.screens

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.viktoriagavrosh.weather.model.apimodel.CurrentWeather
import com.viktoriagavrosh.weather.model.apimodel.DayWeather
import com.viktoriagavrosh.weather.ui.WeatherViewModel
import com.viktoriagavrosh.weather.ui.elements.WeatherTopBar
import com.viktoriagavrosh.weather.ui.util.NavigationDestination

import com.viktoriagavrosh.weather.ui.util.NavigationDestination.CurrentWeatherDestination
import com.viktoriagavrosh.weather.ui.util.NavigationDestination.DetailsDestination
import com.viktoriagavrosh.weather.ui.util.NavigationDestination.ForecastDestination
import com.viktoriagavrosh.weather.ui.util.NavigationDestination.SettingsDestination


@Composable
fun WeatherScreen(
    viewModel: WeatherViewModel = viewModel(factory = WeatherViewModel.Factory),
    navController: NavHostController = rememberNavController()
) {
    val uiState by viewModel.uiState.collectAsState()

    val backStackEntry by navController.currentBackStackEntryAsState()
    val selectedScreen = NavigationDestination::class.sealedSubclasses.firstOrNull {
        Log.e(
            "123",
            "${it.objectInstance?.route} = ${backStackEntry?.destination?.route}"
        )   // TODO log
        it.objectInstance?.route == backStackEntry?.destination?.route
    }?.objectInstance ?: CurrentWeatherDestination

    // TODO add onBackClick = { navController.navigateUp() }

    Column(
        modifier = Modifier.fillMaxSize(),
    ) {
        WeatherTopBar(
            selectedScreen = selectedScreen,
            weatherState = uiState,
            onCityClick = { TODO() },
            onBackClick = {
                if (selectedScreen == ForecastDestination) {
                    navController.navigate(CurrentWeatherDestination.route)
                } else {
                    navController.navigateUp()
                }
            },
            onSettingsClick = { navController.navigate(SettingsDestination.route) }
        )
        NavHost(
            navController = navController,
            startDestination = CurrentWeatherDestination.route
        ) {
            composable(route = CurrentWeatherDestination.route) {
                CurrentWeatherScreen(
                    weatherInfo = uiState.weatherInfo,
                    onDetailsClick = {
                        viewModel.selectWeather(weatherDate = "")    // TODO double work !!!
                        navController.navigate("${DetailsDestination.route}/{${it}}")     // {${it}} because if not -> FatalException
                    },
                    onForecastClick = { date ->
                        viewModel.selectDay(date)
                        navController.navigate("${ForecastDestination.route}/${it}")
                    }
                )
            }
            composable(
                route = ForecastDestination.routeWithArgs,
                arguments = listOf(navArgument(ForecastDestination.itemIdArg) {
                    type = NavType.StringType
                })
            ) {
                ForecastScreen(
                    weatherInfo = uiState.weatherInfo,
                    selectedDay = uiState.selectedDay,
                    onDetailsClick = { weatherDate ->
                        viewModel.selectWeather(weatherDate = weatherDate)
                        navController.navigate("${DetailsDestination.route}/{${weatherDate}}")
                    },
                    onTabClick = viewModel::selectDayByDate
                )
            }
            composable(
                route = DetailsDestination.routeWithArgs,
                arguments = listOf(navArgument(DetailsDestination.itemIdArg) {
                    type = NavType.StringType
                })
            ) {
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
            composable(route = SettingsDestination.route) {
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