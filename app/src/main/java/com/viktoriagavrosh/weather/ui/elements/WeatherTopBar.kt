package com.viktoriagavrosh.weather.ui.elements

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.viktoriagavrosh.weather.R
import com.viktoriagavrosh.weather.model.apimodel.CurrentWeather
import com.viktoriagavrosh.weather.ui.WeatherState
import com.viktoriagavrosh.weather.ui.theme.WeatherTheme
import com.viktoriagavrosh.weather.ui.util.Screen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WeatherTopBar(
    selectedScreen: Screen,
    weatherState: WeatherState,
    onCityClick: () -> Unit = {},
    onBackClick: () -> Unit = {},
    onSettingsClick: () -> Unit = {}
) {
    val text = when(selectedScreen) {
        Screen.CurrentWeather,
        Screen.Forecast -> weatherState.weatherInfo.location.cityName
        Screen.Settings -> stringResource(id = R.string.settings)
        Screen.Details -> {
            if (weatherState.selectedWeather is CurrentWeather) {
                stringResource(id = R.string.now)
            } else {
                weatherState.selectedDay.date
            }
        }
    }
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = text,
                style = MaterialTheme.typography.titleLarge,
                modifier = if (selectedScreen != Screen.CurrentWeather
                    && selectedScreen != Screen.Settings ) {
                    Modifier.clickable {
                        onCityClick()
                    }
                } else {
                    Modifier
                }
            )
        },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            titleContentColor = Color.White
        ),
        navigationIcon = {
            if (selectedScreen != Screen.CurrentWeather) {
                IconButton(
                    onClick = { onBackClick() }
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_back),
                        contentDescription = stringResource(R.string.back),
                        modifier = Modifier
                            .padding(start = dimensionResource(id = R.dimen.padding_small))
                            .size(dimensionResource(id = R.dimen.top_bar_icon_size))
                    )
                }
            }
        },
        actions = {
            if (selectedScreen != Screen.Settings) {
                IconButton(
                    onClick = onSettingsClick
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_settings),
                        contentDescription = stringResource(id = R.string.settings)
                    )
                }
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun WeatherTopBarPreview() {
    WeatherTheme {
        WeatherTopBar(
            selectedScreen = Screen.CurrentWeather,
            weatherState = WeatherState()
        )
    }
}