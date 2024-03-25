package com.viktoriagavrosh.weather.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import com.viktoriagavrosh.weather.ui.screens.WeatherScreen

@Composable
fun WeatherApp(
    viewModel: WeatherViewModel = viewModel(factory = WeatherViewModel.Factory)
) {
    val uiState = viewModel.uiState.collectAsState()
    val settingsState = viewModel.settingsState.collectAsState()
    // Image morning sky, night sky...
    WeatherScreen(
        weatherInfo = uiState.value,
        settings = settingsState.value,
        onCityClick = {}
    )
}
