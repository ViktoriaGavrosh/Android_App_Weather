package com.viktoriagavrosh.weather.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.viktoriagavrosh.weather.R
import com.viktoriagavrosh.weather.ui.WeatherState
import com.viktoriagavrosh.weather.ui.theme.WeatherTheme

@Composable
fun WeatherScreen(
    uiState: WeatherState,
    onMusicClick: () -> Unit,
    onCelsiusClick: (String) -> Unit,
    onWallpaperClick: (String) -> Unit,
    onCityClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Image(
        painter = painterResource(id = R.drawable.weather_bg),
        contentDescription = null,
        modifier = Modifier
            .fillMaxSize()
            .alpha(0.5F),
        contentScale = ContentScale.FillBounds
    )
    Column(
        modifier = modifier
    ) {

        CurrentWeatherScreen(
            weatherInfo = uiState.weatherInfo,
            onDetailsClick = { /*TODO*/ },
            onCityClick = onCityClick,
            modifier = Modifier
                .fillMaxSize()
        )

        /*
               SettingsScreen(
                   settings = uiState.settings,
                   onMusicClick = onMusicClick,
                   onCelsiusClick = onCelsiusClick,
                   onWallpaperClick = onWallpaperClick
               )

        */
    }
}

@Preview(showBackground = true)
@Composable
fun WeatherScreenPreview() {
    WeatherTheme {
        WeatherScreen(
            uiState = WeatherState(),
            onMusicClick = {},
            onCelsiusClick = {},
            onCityClick = {},
            onWallpaperClick = {}
        )
    }
}