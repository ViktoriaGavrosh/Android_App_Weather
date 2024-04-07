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
import com.viktoriagavrosh.weather.model.apimodel.WeatherInfo
import com.viktoriagavrosh.weather.ui.elements.WeatherTabRow
import com.viktoriagavrosh.weather.ui.elements.WeatherTopBar
import com.viktoriagavrosh.weather.ui.elements.curentweatherscreen.CurrentWeatherPager
import com.viktoriagavrosh.weather.ui.theme.WeatherTheme
import com.viktoriagavrosh.weather.ui.util.NavigationDestination

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CurrentWeatherScreen(
    modifier: Modifier = Modifier,
    city: String,
    weatherInfo: WeatherInfo,
    onDetailsClick: (String) -> Unit,
    onForecastClick: (String) -> Unit = {},
    onCityClick: () -> Unit = {},
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
            onCityClick = onCityClick,
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
            onForecastClick = onForecastClick
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
            city = "City"
        )
    }
}