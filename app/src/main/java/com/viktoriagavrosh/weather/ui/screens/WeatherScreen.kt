package com.viktoriagavrosh.weather.ui.screens

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
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.viktoriagavrosh.weather.ui.WeatherViewModel
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

    Column(
        modifier = Modifier.fillMaxSize(),
    ) {
        NavHost(
            navController = navController,
            startDestination = CurrentWeatherDestination.route
        ) {
            composable(route = CurrentWeatherDestination.route) {
                CurrentWeatherScreen(
                    onDetailsClick = { date ->
                        navController.navigate("${DetailsDestination.route}/{${date}}")     // {${it}} because if not -> FatalException
                    },
                    onForecastClick = { date ->
                        navController.navigate("${ForecastDestination.route}/${date}")
                    },
                    weatherInfo = uiState.weatherInfo,
                    city = uiState.weatherInfo.location.cityName,
                    onCityClick = {},
                    onSettingsClick = { navController.navigate(SettingsDestination.route) }
                )
            }
            composable(
                route = ForecastDestination.routeWithArgs,
                arguments = listOf(navArgument(ForecastDestination.itemIdArg) {
                    type = NavType.StringType
                })
            ) { backStackEntry ->
                val dateSelectedDay =
                    backStackEntry.arguments?.getString(ForecastDestination.itemIdArg)
                ForecastScreen(
                    days = uiState.weatherInfo.forecast.days,
                    city = uiState.weatherInfo.location.cityName,
                    dateSelectedDay = dateSelectedDay ?: "",
                    onDetailsClick = { weatherDate ->
                        navController.navigate("${DetailsDestination.route}/{${weatherDate}}")
                    },
                    onCityClick = { TODO() },
                    onBackClick = { navController.popBackStack() },
                    onSettingsClick = { navController.navigate(SettingsDestination.route) }
                )
            }
            composable(
                route = DetailsDestination.routeWithArgs,
                arguments = listOf(navArgument(DetailsDestination.itemIdArg) {
                    type = NavType.StringType
                })
            ) { backStackEntry ->
                val dateSelectedDay =
                    backStackEntry.arguments?.getString(DetailsDestination.itemIdArg)
                        ?.replace(Regex("[},{]"), "")
                DetailsScreen(
                    weatherInfo = uiState.weatherInfo,
                    dateSelectedDay = dateSelectedDay ?: "",
                    onBackClick = { navController.popBackStack() },
                    onSettingsClick = { navController.navigate(SettingsDestination.route) }
                )
            }
            composable(route = SettingsDestination.route) {
                SettingsScreen(
                    settings = uiState.settings,
                    onMusicClick = viewModel::changeMusic,
                    onCelsiusClick = viewModel::changeCelsius,
                    onWallpaperClick = viewModel::changeWallpaper,
                    onBackClick = { navController.popBackStack() }
                )
            }
        }
    }
}