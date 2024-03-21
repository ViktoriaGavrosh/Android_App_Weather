package com.viktoriagavrosh.weather.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun WeatherApp(
    viewModel: WeatherViewModel = viewModel(factory = WeatherViewModel.Factory)
) {
    val uiState = viewModel.uiState.collectAsState()
    Column {
        Text(
            text = uiState.value.location.cityName
        )
        Text(
            text = uiState.value.currentWeather.feelsLikeTempC.toString().ifEmpty { "feel" }
        )
        Text(
            text = uiState.value.forecast.days.size.toString()
        )
        Text(
            text = uiState.value.currentWeather.precipitationMm.toString().ifEmpty { "precip" }
        )
    }
}