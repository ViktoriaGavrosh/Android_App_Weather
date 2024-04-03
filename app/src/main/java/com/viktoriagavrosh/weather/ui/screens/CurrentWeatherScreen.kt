package com.viktoriagavrosh.weather.ui.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.viktoriagavrosh.weather.R
import com.viktoriagavrosh.weather.model.apimodel.Day
import com.viktoriagavrosh.weather.model.apimodel.Weather
import com.viktoriagavrosh.weather.model.apimodel.WeatherInfo
import com.viktoriagavrosh.weather.ui.elements.WeatherTabRow
import com.viktoriagavrosh.weather.ui.elements.WeatherTopBar
import com.viktoriagavrosh.weather.ui.elements.curentweatherscreen.CurrentWeatherPager
import com.viktoriagavrosh.weather.ui.theme.WeatherTheme

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CurrentWeatherScreen(
    modifier: Modifier = Modifier,
    weatherInfo: WeatherInfo,
    onDetailsClick: (Weather) -> Unit,
    onCityClick: () -> Unit,
    onBackClick: () -> Unit = {},
    onForecastClick: (Day) -> Unit = {},
    onTabClick: (String) -> Unit = {},
    onSettingsClick: () -> Unit
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
        modifier = modifier,
    ) {
        WeatherTopBar(
            text = weatherInfo.location.cityName,
            isBack = false,
            onCityClick = onCityClick,
            onBackClick = onBackClick,
            onSettingsClick = onSettingsClick
        )
        Column(
            modifier = Modifier
                .padding(dimensionResource(id = R.dimen.padding_small)),
        ) {
            WeatherTabRow(
                tabList = tabList,
                pagerState = pagerState,
                onTabClick = onTabClick
            )
            CurrentWeatherPager(
                weatherInfo = weatherInfo,
                onDetailsClick = onDetailsClick,
                state = pagerState,
                onForecastClick = onForecastClick
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CurrentWeatherScreenPreview() {
    WeatherTheme {
        CurrentWeatherScreen(
            weatherInfo = WeatherInfo(),
            onDetailsClick = { /*TODO*/ },
            onCityClick = { /*TODO*/ },
            onSettingsClick = {}
        )
    }
}