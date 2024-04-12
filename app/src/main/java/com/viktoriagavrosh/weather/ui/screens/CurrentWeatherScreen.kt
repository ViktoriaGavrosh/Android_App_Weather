package com.viktoriagavrosh.weather.ui.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.viktoriagavrosh.weather.R
import com.viktoriagavrosh.weather.model.apimodel.CurrentWeather
import com.viktoriagavrosh.weather.model.apimodel.Day
import com.viktoriagavrosh.weather.model.apimodel.WeatherInfo
import com.viktoriagavrosh.weather.ui.elements.DetailButton
import com.viktoriagavrosh.weather.ui.elements.WeatherCard
import com.viktoriagavrosh.weather.ui.elements.WeatherTabRow
import com.viktoriagavrosh.weather.ui.elements.WeatherTopBar
import com.viktoriagavrosh.weather.ui.navigation.NavigationDestination
import com.viktoriagavrosh.weather.ui.theme.WeatherTheme

/**
 * Composable to display screen with current weather
 */
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CurrentWeatherScreen(
    modifier: Modifier = Modifier,
    city: String,
    weatherInfo: WeatherInfo,
    isCelsius: Boolean,
    onDetailsClick: (String) -> Unit,
    onForecastClick: (String) -> Unit = {},
    onCityChangeClick: (String) -> Unit = {},
    onSettingsClick: () -> Unit = {}
) {
    val tabList = listOf(
        stringResource(id = R.string.now),
        stringResource(id = R.string.forecast)
    )
    val pagerState = rememberPagerState(
        pageCount = { tabList.size },
        initialPage = 0
    )
    Column(
        modifier = modifier
            .padding(dimensionResource(id = R.dimen.padding_small)),
    ) {
        WeatherTopBar(
            selectedScreen = NavigationDestination.CurrentWeatherDestination,
            title = city,
            onCityChangeClick = onCityChangeClick,
            onSettingsClick = onSettingsClick
        )
        WeatherTabRow(
            tabList = tabList,
            pagerState = pagerState
        )
        CurrentWeatherPager(
            weatherInfo = weatherInfo,
            onDetailsClick = onDetailsClick,
            state = pagerState,
            isCelsius = isCelsius,
            onForecastClick = onForecastClick
        )
    }

}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun CurrentWeatherPager(
    weatherInfo: WeatherInfo,
    state: PagerState,
    isCelsius: Boolean,
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
                isCelsius = isCelsius,
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

@Composable
private fun CurrentWeatherColumn(
    weather: CurrentWeather,
    isCelsius: Boolean,
    onDetailsClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val details = if (isCelsius) {
        listOf(
            stringResource(id = R.string.feels_like, weather.feelsLikeTempC),
            stringResource(id = R.string.wind, weather.windSpeedKm),
            stringResource(id = R.string.precipitation, weather.precipitationMm),
            stringResource(id = R.string.humidity, weather.humidityPercent),
            stringResource(id = R.string.pressure, weather.pressureMm)
        )
    } else {
        listOf(
            stringResource(id = R.string.feels_like, weather.feelsLikeTempF),
            stringResource(id = R.string.wind, weather.windSpeedMile),
            stringResource(id = R.string.precipitation, weather.precipitationInch),
            stringResource(id = R.string.humidity, weather.humidityPercent),
            stringResource(id = R.string.pressure, weather.pressureIn)
        )
    }

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        WeatherCard(
            condition = weather.weatherCondition.condition,
            iconUri = weather.weatherCondition.iconUri,
            temp = if (isCelsius) weather.tempC else weather.tempF,
            paddingValues = PaddingValues(dimensionResource(id = R.dimen.padding_medium)),
            modifier = Modifier.fillMaxWidth()
        )
        details.forEach {
            DetailRow(
                title = it,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = dimensionResource(id = R.dimen.padding_small))
            )
        }
        DetailButton(
            onDetailsClick = onDetailsClick,
            modifier = Modifier.padding(dimensionResource(id = R.dimen.padding_large))
        )
    }
}

@Composable
private fun DetailRow(
    title: String,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleLarge,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    vertical = dimensionResource(id = R.dimen.padding_medium)
                )
        )
    }
}

@Preview(showBackground = true)
@Composable
fun CurrentWeatherScreenPreview() {
    WeatherTheme {
        CurrentWeatherScreen(
            onDetailsClick = {},
            weatherInfo = WeatherInfo(),
            city = "City",
            isCelsius = false
        )
    }
}