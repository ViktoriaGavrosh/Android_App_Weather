package com.viktoriagavrosh.weather.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.viktoriagavrosh.weather.R
import com.viktoriagavrosh.weather.model.apimodel.WeatherInfo
import com.viktoriagavrosh.weather.ui.SettingsState
import com.viktoriagavrosh.weather.ui.elements.WeatherTopBar
import com.viktoriagavrosh.weather.ui.theme.WeatherTheme

@Composable
fun WeatherScreen(
    weatherInfo: WeatherInfo,
    settings: SettingsState,
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
/*
        CurrentWeatherScreen(
            city = weatherInfo.location.cityName,
            weather = weatherInfo.currentWeather,
            onDetailsClick = { /*TODO*/ },
            onCityClick = onCityClick,
            modifier = Modifier
                .fillMaxSize()
        )

 */
        SettingsScreen(
            settings = settings
        )
    }
}

@Preview(showBackground = true)
@Composable
fun WeatherScreenPreview() {
    WeatherTheme {
        WeatherScreen(
            weatherInfo = WeatherInfo(),
            settings = SettingsState(),
            onCityClick = {}
        )
    }
}