package com.viktoriagavrosh.weather.ui.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import com.viktoriagavrosh.weather.R
import com.viktoriagavrosh.weather.ui.WeatherState
import com.viktoriagavrosh.weather.ui.elements.WeatherTabRow
import com.viktoriagavrosh.weather.ui.elements.WeatherTopBar
import com.viktoriagavrosh.weather.ui.elements.forecastscreen.ForecastPager
import com.viktoriagavrosh.weather.ui.theme.WeatherTheme
import com.viktoriagavrosh.weather.ui.util.NavigationDestination

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ForecastScreen(
    modifier: Modifier = Modifier,
    weatherState: WeatherState,
    onDetailsClick: (String) -> Unit,
    onTabClick: (String) -> Unit = {},
    onCityClick: () -> Unit = {},
    onBackClick: () -> Unit = {},
    onSettingsClick: () -> Unit = {}
) {
    val tabList = try {
        List(3) {
            weatherState.weatherInfo.forecast.days[it].date
                .substringAfter("-")
                .replace("-", "/")
        }
    } catch (e: IndexOutOfBoundsException) {
        List(3) { "$it" }
    }
    val selectedDayIndex = tabList.indexOf(
        weatherState.selectedDay.date
            .substringAfter("-")
            .replace("-", "/")
    )
    val pagerState = rememberPagerState(
        pageCount = { tabList.size },
        initialPage = if (selectedDayIndex != -1) selectedDayIndex else 0
    )
    Column(
        modifier = modifier
            .padding(dimensionResource(id = R.dimen.padding_small)),
    ) {
        WeatherTopBar(
            selectedScreen = NavigationDestination.ForecastDestination,
            weatherState = weatherState,
            onCityClick = onCityClick,
            onBackClick = onBackClick,
            onSettingsClick = onSettingsClick
        )
        WeatherTabRow(
            tabList = tabList,
            pagerState = pagerState,
            isForecast = true,
            onTabClick = onTabClick
        )
        ForecastPager(
            days = weatherState.weatherInfo.forecast.days,
            state = pagerState,
            onDetailsClick = onDetailsClick,
            modifier = Modifier
                .fillMaxSize()
                .padding(top = dimensionResource(id = R.dimen.padding_small))
        )
    }

}

@Preview(showBackground = true)
@Composable
fun ForecastScreenPreview() {
    WeatherTheme {
        ForecastScreen(
            weatherState = WeatherState(),
            onDetailsClick = { /*TODO*/ }
        )
    }
}