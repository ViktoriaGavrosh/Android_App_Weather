package com.viktoriagavrosh.weather.ui.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.viktoriagavrosh.weather.R
import com.viktoriagavrosh.weather.model.apimodel.WeatherInfo
import com.viktoriagavrosh.weather.ui.elements.CurrentWeatherColumn
import com.viktoriagavrosh.weather.ui.elements.WeatherTabRow
import com.viktoriagavrosh.weather.ui.elements.WeatherTopBar
import com.viktoriagavrosh.weather.ui.theme.WeatherTheme

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CurrentWeatherScreen(
    weatherInfo: WeatherInfo,
    onDetailsClick: () -> Unit,
    onCityClick: () -> Unit,
    modifier: Modifier = Modifier
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
                    .verticalScroll(rememberScrollState())
            ) { index ->
                if (index == 0) {
                    CurrentWeatherColumn(
                        weather = weatherInfo.currentWeather,
                        onDetailsClick = onDetailsClick,
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(top = dimensionResource(id = R.dimen.padding_small))
                    )
                } else {
                    Text(text = "\u2103")
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CurrentWeatherScreenPreview() {
    WeatherTheme {
        CurrentWeatherScreen(
            weatherInfo = WeatherInfo(),
            onDetailsClick = { },
            onCityClick = {}
        )
    }
}