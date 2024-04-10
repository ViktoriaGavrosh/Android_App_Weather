package com.viktoriagavrosh.weather.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.viktoriagavrosh.weather.R
import com.viktoriagavrosh.weather.ui.screens.WeatherScreen
import com.viktoriagavrosh.weather.ui.theme.WeatherTheme

@Composable
fun WeatherApp() {
    // Image morning sky, night sky...
    Image(
        painter = painterResource(id = R.drawable.cloudy_bg),
        contentDescription = null,
        modifier = Modifier
            .fillMaxSize()
            .alpha(0.5F),
        contentScale = ContentScale.FillBounds
    )
    WeatherScreen()
}

@Preview(showBackground = true)
@Composable
fun WeatherAppPreview() {
    WeatherTheme {
        WeatherApp()
    }
}