package com.viktoriagavrosh.weather.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import com.viktoriagavrosh.weather.ui.screens.WeatherScreen

@Composable
fun WeatherApp(
    viewModel: WeatherViewModel = viewModel(factory = WeatherViewModel.Factory)
) {
    val uiState by viewModel.uiState.collectAsState()
    // Image morning sky, night sky...
    WeatherScreen(
        uiState = uiState,
        onMusicClick = viewModel::changeMusic,
        onCelsiusClick = viewModel::changeCelsius,
        onCityClick = {},
        onWallpaperClick = viewModel::changeWallpaper
    )
}
