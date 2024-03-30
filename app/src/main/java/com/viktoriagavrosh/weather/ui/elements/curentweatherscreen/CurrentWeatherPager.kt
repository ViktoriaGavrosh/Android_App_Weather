package com.viktoriagavrosh.weather.ui.elements.curentweatherscreen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import com.viktoriagavrosh.weather.R
import com.viktoriagavrosh.weather.model.apimodel.WeatherInfo

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CurrentWeatherPager(
    weatherInfo: WeatherInfo,
    state: PagerState,
    onDetailsClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    HorizontalPager(
        state = state,
        modifier = modifier
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
            // TODO переход к Forecast
        }
    }
}