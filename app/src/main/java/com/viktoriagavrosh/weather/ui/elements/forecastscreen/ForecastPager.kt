package com.viktoriagavrosh.weather.ui.elements.forecastscreen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.viktoriagavrosh.weather.model.apimodel.Day
import com.viktoriagavrosh.weather.model.apimodel.Weather

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ForecastPager(
    days: List<Day>,
    state: PagerState,
    onDetailsClick: (Weather) -> Unit,
    modifier: Modifier = Modifier
) {
    HorizontalPager(
        state = state,
        modifier = modifier,
        verticalAlignment = Alignment.Bottom
    ) { index ->
        ForecastScreenContent(
            day = days[index],
            onDetailsClick = onDetailsClick,
            modifier = Modifier.fillMaxSize()
        )
    }
}