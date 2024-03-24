package com.viktoriagavrosh.weather.ui.elements

import androidx.compose.foundation.clickable
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.viktoriagavrosh.weather.ui.theme.WeatherTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WeatherTopBar(
    text: String,
    onCityClick: () -> Unit
) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = text,
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.clickable {
                    onCityClick()
                }
            )
        },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            //containerColor = Blue100,
            titleContentColor = Color.White
        )
    )
}

@Preview(showBackground = true)
@Composable
fun WeatherTopBarPreview() {
    WeatherTheme {
        WeatherTopBar(
            text = "City",
            onCityClick = {}
        )
    }
}