package com.viktoriagavrosh.weather.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.viktoriagavrosh.weather.R
import com.viktoriagavrosh.weather.model.apimodel.WeatherInfo
import com.viktoriagavrosh.weather.ui.elements.WeatherTopBar
import com.viktoriagavrosh.weather.ui.theme.WeatherTheme

@Composable
fun WeatherScreen(
    weatherInfo: WeatherInfo,
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
    Scaffold(
        topBar = {
            WeatherTopBar(
                text = weatherInfo.location.cityName,
                onCityClick = onCityClick
            )
        },
        containerColor = Color.Transparent,
        modifier = modifier
    ) {
        CurrentWeatherScreen(
            weather = weatherInfo.currentWeather,
            onDetailsClick = { /*TODO*/ },
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun WeatherScreenPreview() {
    WeatherTheme {
        WeatherScreen(
            weatherInfo = WeatherInfo(),
            onCityClick = {}
        )
    }
}