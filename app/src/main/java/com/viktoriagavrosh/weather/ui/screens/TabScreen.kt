package com.viktoriagavrosh.weather.ui.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import com.viktoriagavrosh.weather.R
import com.viktoriagavrosh.weather.model.apimodel.WeatherInfo
import com.viktoriagavrosh.weather.ui.elements.WeatherTopBar
import com.viktoriagavrosh.weather.ui.elements.curentweatherscreen.CurrentWeatherPager
import com.viktoriagavrosh.weather.ui.elements.forecastscreen.ForecastPager
import com.viktoriagavrosh.weather.ui.theme.WeatherTheme
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TabScreen(
    weatherInfo: WeatherInfo,
    tabList: List<String>,
    onDetailsClick: () -> Unit,
    onCityClick: () -> Unit,
    isBack: Boolean,
    modifier: Modifier = Modifier
) {
    val pagerState = rememberPagerState(
        pageCount = { tabList.size },
        initialPage = 0
    )
    Column(
        modifier = modifier,
    ) {
        WeatherTopBar(
            text = weatherInfo.location.cityName,
            isBack = isBack,
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
            if (isBack) {
                ForecastPager(
                    days = weatherInfo.forecast.days,
                    state = pagerState,
                    onDetailsClick = onDetailsClick,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = dimensionResource(id = R.dimen.padding_small))
                )
            } else {
                CurrentWeatherPager(
                    weatherInfo = weatherInfo,
                    onDetailsClick = onDetailsClick,
                    state = pagerState
                )
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun WeatherTabRow(
    tabList: List<String>,
    pagerState: PagerState,
    modifier: Modifier = Modifier
) {
    val tabIndex = pagerState.currentPage
    val coroutineScope = rememberCoroutineScope()
    Column(
        modifier = modifier
    ) {
        TabRow(
            selectedTabIndex = tabIndex,
            modifier = Modifier
                .clip(RoundedCornerShape(dimensionResource(id = R.dimen.corner_size))),
            contentColor = MaterialTheme.colorScheme.onPrimary
        ) {
            tabList.forEachIndexed { index, text ->
                Tab(
                    selected = false,
                    onClick = {
                        coroutineScope.launch {
                            pagerState.animateScrollToPage(index)
                        }
                    },
                    text = {
                        Text(
                            text = text,
                            style = MaterialTheme.typography.headlineSmall
                        )
                    }
                )
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun TabScreenPreview() {
    WeatherTheme {
        TabScreen(
            weatherInfo = WeatherInfo(),
            tabList = listOf("1", "2"),
            onDetailsClick = { /*TODO*/ },
            onCityClick = { /*TODO*/ },
            isBack = false
        )
    }
}