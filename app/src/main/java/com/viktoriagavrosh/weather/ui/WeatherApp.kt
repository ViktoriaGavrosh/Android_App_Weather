package com.viktoriagavrosh.weather.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.viktoriagavrosh.weather.ui.navigation.WeatherNavHost
import com.viktoriagavrosh.weather.ui.theme.WeatherTheme

@Composable
fun WeatherApp(
    viewModel: WeatherViewModel = viewModel(factory = WeatherViewModel.Factory),
    navController: NavHostController = rememberNavController()
) {
    val context = LocalContext.current
    viewModel.getWeatherInfo("Minsk", context)
    val uiState by viewModel.uiState.collectAsState()

    Image(
        painter = painterResource(id = uiState.settings.wallpaper.imageId),
        contentDescription = null,
        modifier = Modifier
            .fillMaxSize()
            .alpha(0.5F),
        contentScale = ContentScale.FillBounds
    )
    WeatherNavHost(
        navController = navController,
        viewModel = viewModel,
        uiState = uiState,
        modifier = Modifier.fillMaxSize()
    )
}

@Preview(showBackground = true)
@Composable
fun WeatherAppPreview() {
    WeatherTheme {
        WeatherApp()
    }
}