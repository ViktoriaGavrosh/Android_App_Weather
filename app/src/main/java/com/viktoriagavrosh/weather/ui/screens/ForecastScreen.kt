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
import com.viktoriagavrosh.weather.model.apimodel.Day
import com.viktoriagavrosh.weather.model.apimodel.Weather
import com.viktoriagavrosh.weather.model.apimodel.WeatherInfo
import com.viktoriagavrosh.weather.ui.elements.WeatherTabRow
import com.viktoriagavrosh.weather.ui.elements.WeatherTopBar
import com.viktoriagavrosh.weather.ui.elements.forecastscreen.ForecastPager
import com.viktoriagavrosh.weather.ui.theme.WeatherTheme

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ForecastScreen(
    modifier: Modifier = Modifier,
    weatherInfo: WeatherInfo,
    selectedDay: Day = Day(),
    onDetailsClick: (Weather) -> Unit,
    onCityClick: () -> Unit,
    onBackClick: () -> Unit = {},
    onTabClick: (String) -> Unit = {},
    onSettingsClick: () -> Unit
) {
    val tabList = try {
        List(3) {
            weatherInfo.forecast.days[it].date
                .substringAfter("-")
                .replace("-", "/")
        }
    } catch (e: IndexOutOfBoundsException) {
        List(3) { "$it" }
    }
    val selectedDayIndex = tabList.indexOf(
        selectedDay.date
            .substringAfter("-")
            .replace("-", "/")
    )
    val pagerState = rememberPagerState(
        pageCount = { tabList.size },
        initialPage = if (selectedDayIndex != -1) selectedDayIndex else 0
    )
    Column(
        modifier = modifier,
    ) {
        WeatherTopBar(
            text = weatherInfo.location.cityName,
            isBack = true,
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
                isForecast = true,
                onTabClick = onTabClick
            )
            ForecastPager(
                days = weatherInfo.forecast.days,
                state = pagerState,
                onDetailsClick = onDetailsClick,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = dimensionResource(id = R.dimen.padding_small))
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ForecastScreenPreview() {
    WeatherTheme {
        ForecastScreen(
            weatherInfo = WeatherInfo(),
            onDetailsClick = { /*TODO*/ },
            onCityClick = { /*TODO*/ },
            onSettingsClick = {}
        )
    }
}