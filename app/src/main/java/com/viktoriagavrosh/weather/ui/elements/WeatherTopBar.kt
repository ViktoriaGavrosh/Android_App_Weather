package com.viktoriagavrosh.weather.ui.elements

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.viktoriagavrosh.weather.R
import com.viktoriagavrosh.weather.ui.theme.WeatherTheme
import com.viktoriagavrosh.weather.ui.util.NavigationDestination
import com.viktoriagavrosh.weather.ui.util.NavigationDestination.CurrentWeatherDestination
import com.viktoriagavrosh.weather.ui.util.NavigationDestination.ForecastDestination
import com.viktoriagavrosh.weather.ui.util.NavigationDestination.SettingsDestination

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WeatherTopBar(
    selectedScreen: NavigationDestination,
    title: String,
    onCityClick: () -> Unit = {},
    onBackClick: () -> Unit = {},
    onSettingsClick: () -> Unit = {}
) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = title,
                style = MaterialTheme.typography.titleLarge,
                modifier = if (selectedScreen != CurrentWeatherDestination
                    && selectedScreen != SettingsDestination
                ) {
                    Modifier.clickable {
                        onCityClick()
                    }
                } else {
                    Modifier
                }
            )
        },
        modifier = Modifier
            .padding(bottom = dimensionResource(id = R.dimen.padding_small))
            .clip(RoundedCornerShape(dimensionResource(id = R.dimen.corner_size))),
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            titleContentColor = Color.White
        ),
        navigationIcon = {
            if (selectedScreen != CurrentWeatherDestination) {
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
            if (selectedScreen != SettingsDestination) {
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
            selectedScreen = ForecastDestination,
            title = "City"
        )
    }
}