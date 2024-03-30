package com.viktoriagavrosh.weather.ui.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import com.viktoriagavrosh.weather.R
import com.viktoriagavrosh.weather.model.apimodel.WeatherInfo
import com.viktoriagavrosh.weather.ui.elements.ForecastScreenContent
import com.viktoriagavrosh.weather.ui.elements.WeatherTabRow
import com.viktoriagavrosh.weather.ui.elements.WeatherTopBar
import com.viktoriagavrosh.weather.ui.theme.WeatherTheme

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ForecastScreen(
    weatherInfo: WeatherInfo,
    onCityClick: () -> Unit,
    onDetailsClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val tabList = try {
        List(3) { weatherInfo.forecast.days[it].date.substringAfter("-").replace("-", "/") }
    } catch (e: IndexOutOfBoundsException) {
        List(3) { "$it" }
    }
    val pagerState = rememberPagerState(
        pageCount = { tabList.size },
        initialPage = 0
    )
    Column(
        modifier = modifier,
    ) {
        WeatherTopBar(
            text = weatherInfo.location.cityName,
            onCityClick = onCityClick
        )
        Column(
            modifier = Modifier
                .padding(dimensionResource(id = R.dimen.padding_small)),
        ) {
            WeatherTabRow(
                tabList = tabList,
                pagerState = pagerState
            )
            HorizontalPager(
                state = pagerState,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = dimensionResource(id = R.dimen.padding_small)),
                verticalAlignment = Alignment.Bottom
            ) { index ->
                ForecastScreenContent(
                    day = weatherInfo.forecast.days[index],
                    onDetailsClick = onDetailsClick,
                    modifier = Modifier.fillMaxSize()
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ForecastScreenPreview() {
    WeatherTheme {
        ForecastScreen(
            weatherInfo = WeatherInfo(),
            onCityClick = {},
            onDetailsClick = {}
        )
    }
}