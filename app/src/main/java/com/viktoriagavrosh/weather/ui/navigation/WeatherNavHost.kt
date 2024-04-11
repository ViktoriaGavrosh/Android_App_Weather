package com.viktoriagavrosh.weather.ui.navigation

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.viktoriagavrosh.weather.model.apimodel.WeatherInfo
import com.viktoriagavrosh.weather.ui.WeatherViewModel
import com.viktoriagavrosh.weather.ui.screens.CurrentWeatherScreen
import com.viktoriagavrosh.weather.ui.screens.DetailsScreen
import com.viktoriagavrosh.weather.ui.screens.ForecastScreen
import com.viktoriagavrosh.weather.ui.screens.SettingsScreen

/**
 * Provides Navigation graph for the application
 */
@Composable
fun WeatherNavHost(
    navController: NavHostController,
    viewModel: WeatherViewModel,
    uiState: WeatherInfo,
    modifier: Modifier = Modifier
) {
    val isMusicState = viewModel.isMusic.collectAsState()
    val isCelsiusState = viewModel.isCelsius.collectAsState()
    val wallpaperIdState = viewModel.wallpaperId.collectAsState()

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
                    weatherInfo = uiState,
                    city = uiState.location.cityName,
                    onCityChangeClick = viewModel::changeCity,
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
                    days = uiState.forecast.days,
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
                    weatherInfo = uiState,
                    dateSelectedDay = dateSelectedDay ?: "",
                    onBackClick = { navController.popBackStack() },
                    onSettingsClick = { navController.navigate(NavigationDestination.SettingsDestination.route) }
                )
            }
            composable(route = NavigationDestination.SettingsDestination.route) {
                SettingsScreen(
                    isMusicState = isMusicState,
                    isCelsiusState = isCelsiusState,
                    wallpaperIdState = wallpaperIdState,
                    onMusicClick = viewModel::changeMusic,
                    onCelsiusClick = viewModel::changeCelsius,
                    onWallpaperClick = viewModel::changeWallpaper,
                    onBackClick = { navController.popBackStack() }
                )
            }
        }
    }
}