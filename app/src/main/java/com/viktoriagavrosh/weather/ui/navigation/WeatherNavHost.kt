package com.viktoriagavrosh.weather.ui.navigation

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.viktoriagavrosh.weather.ui.WeatherState
import com.viktoriagavrosh.weather.ui.WeatherViewModel
import com.viktoriagavrosh.weather.ui.screens.CurrentWeatherScreen
import com.viktoriagavrosh.weather.ui.screens.DetailsScreen
import com.viktoriagavrosh.weather.ui.screens.ForecastScreen
import com.viktoriagavrosh.weather.ui.screens.SettingsScreen

@Composable
fun WeatherNavHost(
    navController: NavHostController,
    viewModel: WeatherViewModel,
    uiState: WeatherState,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
    ) {
        NavHost(
            navController = navController,
            startDestination = NavigationDestination.CurrentWeatherDestination.route
        ) {
            composable(route = NavigationDestination.CurrentWeatherDestination.route) {
                CurrentWeatherScreen(
                    onDetailsClick = { date ->
                        navController.navigate("${NavigationDestination.DetailsDestination.route}/{${date}}")     // {${it}} because if not -> FatalException
                    },
                    onForecastClick = { date ->
                        navController.navigate("${NavigationDestination.ForecastDestination.route}/${date}")
                    },
                    weatherInfo = uiState.weatherInfo,
                    city = uiState.weatherInfo.location.cityName,
                    onCityChangeClick = viewModel::getWeatherInfo,
                    onSettingsClick = { navController.navigate(NavigationDestination.SettingsDestination.route) }
                )
            }
            composable(
                route = NavigationDestination.ForecastDestination.routeWithArgs,
                arguments = listOf(navArgument(NavigationDestination.ForecastDestination.itemIdArg) {
                    type = NavType.StringType
                })
            ) { backStackEntry ->
                val dateSelectedDay =
                    backStackEntry.arguments?.getString(NavigationDestination.ForecastDestination.itemIdArg)
                ForecastScreen(
                    days = uiState.weatherInfo.forecast.days,
                    dateSelectedDay = dateSelectedDay ?: "",
                    onDetailsClick = { weatherDate ->
                        navController.navigate("${NavigationDestination.DetailsDestination.route}/{${weatherDate}}")
                    },
                    onBackClick = { navController.popBackStack() },
                    onSettingsClick = { navController.navigate(NavigationDestination.SettingsDestination.route) }
                )
            }
            composable(
                route = NavigationDestination.DetailsDestination.routeWithArgs,
                arguments = listOf(navArgument(NavigationDestination.DetailsDestination.itemIdArg) {
                    type = NavType.StringType
                })
            ) { backStackEntry ->
                val dateSelectedDay =
                    backStackEntry.arguments?.getString(NavigationDestination.DetailsDestination.itemIdArg)
                        ?.replace(Regex("[},{]"), "")
                DetailsScreen(
                    weatherInfo = uiState.weatherInfo,
                    dateSelectedDay = dateSelectedDay ?: "",
                    onBackClick = { navController.popBackStack() },
                    onSettingsClick = { navController.navigate(NavigationDestination.SettingsDestination.route) }
                )
            }
            composable(route = NavigationDestination.SettingsDestination.route) {
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