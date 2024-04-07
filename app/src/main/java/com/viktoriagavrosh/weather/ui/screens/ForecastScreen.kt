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
import com.viktoriagavrosh.weather.ui.elements.WeatherTabRow
import com.viktoriagavrosh.weather.ui.elements.WeatherTopBar
import com.viktoriagavrosh.weather.ui.elements.forecastscreen.ForecastPager
import com.viktoriagavrosh.weather.ui.theme.WeatherTheme
import com.viktoriagavrosh.weather.ui.util.NavigationDestination

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ForecastScreen(
    modifier: Modifier = Modifier,
    days: List<Day>,
    city: String,
    dateSelectedDay: String,
    onDetailsClick: (String) -> Unit,
    onCityClick: () -> Unit = {},
    onBackClick: () -> Unit = {},
    onSettingsClick: () -> Unit = {}
) {
    val tabList = try {
        List(3) {
            days[it].date
                .substringAfter("-")
                .replace("-", "/")
        }
    } catch (e: IndexOutOfBoundsException) {
        List(3) { "$it" }
    }
    val selectedDayIndex = tabList.indexOf(
        dateSelectedDay.substringAfter("-")
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
            title = city,
            onCityClick = onCityClick,
            onBackClick = onBackClick,
            onSettingsClick = onSettingsClick
        )
        WeatherTabRow(
            tabList = tabList,
            pagerState = pagerState
        )
        ForecastPager(
            days = days,
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
            days = emptyList(),
            city = "City",
            dateSelectedDay = "",
            onDetailsClick = {}
        )
    }
}