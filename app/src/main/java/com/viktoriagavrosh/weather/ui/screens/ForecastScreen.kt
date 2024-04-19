package com.viktoriagavrosh.weather.ui.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.viktoriagavrosh.weather.R
import com.viktoriagavrosh.weather.model.apimodel.Day
import com.viktoriagavrosh.weather.model.apimodel.DayWeather
import com.viktoriagavrosh.weather.model.apimodel.Hour
import com.viktoriagavrosh.weather.model.apimodel.Weather
import com.viktoriagavrosh.weather.ui.elements.DetailButton
import com.viktoriagavrosh.weather.ui.elements.WeatherCard
import com.viktoriagavrosh.weather.ui.elements.WeatherTabRow
import com.viktoriagavrosh.weather.ui.elements.WeatherTopBar
import com.viktoriagavrosh.weather.ui.navigation.NavigationDestination
import com.viktoriagavrosh.weather.ui.theme.WeatherTheme

/**
 * Composable to display screen with forecast (three days)
 */
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ForecastScreen(
    modifier: Modifier = Modifier,
    days: List<Day>,
    dateSelectedDay: String,
    isCelsius: Boolean,
    onDetailsClick: (String) -> Unit,
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
        modifier = modifier.padding(dimensionResource(id = R.dimen.padding_small))
    ) {
        WeatherTopBar(
            selectedScreen = NavigationDestination.ForecastDestination,
            title = stringResource(R.string.forecast_top_bar),
            onBackClick = onBackClick,
            onSettingsClick = onSettingsClick
        )
        WeatherTabRow(tabList = tabList, pagerState = pagerState)
        ForecastPager(
            days = days,
            state = pagerState,
            isCelsius = isCelsius,
            onDetailsClick = onDetailsClick,
            modifier = Modifier
                .fillMaxSize()
                .padding(top = dimensionResource(id = R.dimen.padding_small))
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun ForecastPager(
    days: List<Day>,
    state: PagerState,
    isCelsius: Boolean,
    onDetailsClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    HorizontalPager(
        state = state,
        modifier = modifier,
        verticalAlignment = Alignment.Bottom
    ) { index ->
        ForecastScreenContent(
            day = days[index],
            isCelsius = isCelsius,
            onDetailsClick = onDetailsClick,
            modifier = Modifier.fillMaxSize()
        )
    }
}

@Composable
private fun ForecastScreenContent(
    day: Day,
    isCelsius: Boolean,
    onDetailsClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    var weather by remember { mutableStateOf(DayWeather() as Weather) }
    var isDay by remember { mutableStateOf(true) }
    if (isDay) weather = day.dayWeather
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Bottom
    ) {
        DayCard(
            dateTime = day.date,
            weather = weather,
            isDay = isDay,
            isCelsius = isCelsius,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1F),
            onDetailsClick = onDetailsClick
        )
        HoursRow(
            hours = day.hours,
            onHourClick = {
                isDay = false
                weather = it
            },
            isCelsius = isCelsius,
            modifier = Modifier.padding(top = dimensionResource(id = R.dimen.padding_small))
        )
    }
}

@Composable
private fun DayCard(
    modifier: Modifier = Modifier,
    dateTime: String,
    weather: Weather,
    isDay: Boolean,
    isCelsius: Boolean,
    onDetailsClick: (String) -> Unit
) {
    val details = if (isCelsius) {
        listOf(
            stringResource(id = R.string.wind, weather.windSpeedKm),
            stringResource(id = R.string.precipitation, weather.precipitationMm),
            stringResource(R.string.humidity, weather.humidityPercent)
        )
    } else listOf(
        stringResource(id = R.string.wind, weather.windSpeedMile),
        stringResource(id = R.string.precipitation, weather.precipitationInch),
        stringResource(R.string.humidity, weather.humidityPercent)
    )

    Card(modifier = modifier) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceAround,
            modifier = Modifier.fillMaxSize()
        ) {
            Text(
                text = if (isDay) dateTime else (weather as Hour).time,
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.testTag(stringResource(R.string.date_or_time))
            )
            WeatherCard(
                condition = weather.weatherCondition.condition,
                iconUri = weather.weatherCondition.iconUri,
                temp = if (isCelsius) weather.tempC else weather.tempF,
                isTransparentBackground = true
            )
            details.forEach {
                Text(text = it, style = MaterialTheme.typography.titleLarge)
            }
            if (isDay) {
                DetailButton(
                    onDetailsClick = onDetailsClick,
                    weatherDate = dateTime,
                    isWhiteBorder = true
                )
            }
        }
    }
}

@Composable
private fun HoursRow(
    hours: List<Hour>,
    isCelsius: Boolean,
    onHourClick: (Weather) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyRow(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.padding_small))
    ) {
        items(items = hours) {
            HourCard(
                hour = it,
                onHourClick = onHourClick,
                isCelsius = isCelsius,
                modifier = Modifier
            )
        }
    }
}

@Composable
private fun HourCard(
    hour: Hour,
    isCelsius: Boolean,
    onHourClick: (Weather) -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.clickable { onHourClick(hour) }
    ) {
        Column(
            modifier = Modifier,
            verticalArrangement = Arrangement.SpaceAround,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = hour.time.substringAfter(" "),
                modifier = Modifier.padding(
                    top = dimensionResource(id = R.dimen.padding_small),
                    start = dimensionResource(id = R.dimen.padding_small),
                    end = dimensionResource(id = R.dimen.padding_small)
                )
            )
            AsyncImage(
                model = ImageRequest
                    .Builder(context = LocalContext.current)
                    .data(hour.weatherCondition.iconUri)
                    .build(),
                contentDescription = stringResource(
                    id = R.string.icon_description,
                    hour.weatherCondition.condition
                ),
                error = painterResource(id = R.drawable.ic_broken_image),
                placeholder = painterResource(id = R.drawable.ic_broken_image),
                modifier = Modifier
                    .padding(bottom = dimensionResource(id = R.dimen.padding_small))
                    .size(dimensionResource(id = R.dimen.small_image_size))
            )
            Text(
                text = if (isCelsius) hour.tempC else hour.tempF,
                modifier = Modifier.padding(bottom = dimensionResource(id = R.dimen.padding_small))
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ForecastScreenPreview() {
    WeatherTheme {
        ForecastScreen(
            days = emptyList(),
            dateSelectedDay = "",
            isCelsius = true,
            onDetailsClick = {}
        )
    }
}