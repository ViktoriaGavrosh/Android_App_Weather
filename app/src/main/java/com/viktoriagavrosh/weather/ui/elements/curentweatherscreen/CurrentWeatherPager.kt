package com.viktoriagavrosh.weather.ui.elements.curentweatherscreen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import com.viktoriagavrosh.weather.R
import com.viktoriagavrosh.weather.model.apimodel.Day
import com.viktoriagavrosh.weather.model.apimodel.WeatherInfo
import com.viktoriagavrosh.weather.ui.theme.WeatherTheme

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CurrentWeatherPager(
    weatherInfo: WeatherInfo,
    state: PagerState,
    onDetailsClick: (String) -> Unit,
    onForecastClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    HorizontalPager(
        state = state,
        modifier = modifier
    ) { index ->
        if (index == 0) {
            CurrentWeatherColumn(
                weather = weatherInfo.currentWeather,
                onDetailsClick = onDetailsClick,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = dimensionResource(id = R.dimen.padding_small))
                    .verticalScroll(rememberScrollState())
            )
        } else {
            ButtonsColumn(
                days = weatherInfo.forecast.days,
                onForecastClick = onForecastClick,
                modifier = Modifier
                    .fillMaxSize()
            )
        }
    }
}

@Composable
private fun ButtonsColumn(
    modifier: Modifier = Modifier,
    days: List<Day>,
    onForecastClick: (String) -> Unit
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        days.forEach {
            Button(
                onClick = { onForecastClick(it.date) },
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.surfaceVariant
                ),
                modifier = Modifier.padding(top = dimensionResource(id = R.dimen.padding_extra_large))
            ) {
                Text(
                    text = it.date,
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.padding(vertical = dimensionResource(id = R.dimen.padding_medium))
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ButtonsColumnPreview() {
    WeatherTheme {
        ButtonsColumn(
            days = List(3) { Day() },
            onForecastClick = {}
        )
    }
}