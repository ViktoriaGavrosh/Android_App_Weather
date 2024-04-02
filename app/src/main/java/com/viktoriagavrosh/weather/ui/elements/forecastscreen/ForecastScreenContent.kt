package com.viktoriagavrosh.weather.ui.elements.forecastscreen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
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
import com.viktoriagavrosh.weather.ui.theme.WeatherTheme

@Composable
fun ForecastScreenContent(
    day: Day,
    onDetailsClick: (Weather) -> Unit,
    modifier: Modifier = Modifier
) {
    var weather by remember {
        mutableStateOf(DayWeather() as Weather)
    }
    var isDay by remember {
        mutableStateOf(true)
    }
    if (isDay) weather = day.dayWeather
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Bottom
    ) {
        DayCard(
            dateTime = day.date,
            weather = weather,
            isDay = isDay,
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
            modifier = Modifier
                .padding(top = dimensionResource(id = R.dimen.padding_small))

        )
    }
}

@Composable
private fun DayCard(
    modifier: Modifier = Modifier,
    dateTime: String,
    weather: Weather,
    isDay: Boolean,
    onDetailsClick: (Weather) -> Unit
) {
    val details = listOf(
        stringResource(id = R.string.wind, weather.windSpeedKm.toInt()),
        stringResource(id = R.string.precipitation, weather.precipitationMm),
        stringResource(R.string.humidity, weather.humidity.toInt())
    )

    Card(
        modifier = modifier
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceAround,
            modifier = Modifier.fillMaxSize()
        ) {
            Text(
                text = if (isDay) dateTime else (weather as Hour).time,
                style = MaterialTheme.typography.titleLarge
            )
            WeatherCard(
                condition = weather.weatherCondition.condition,
                iconUri = weather.weatherCondition.iconUri,
                temp = weather.tempC,
                isTransparentBackground = true
            )
            details.forEach {
                Text(
                    text = it,
                    style = MaterialTheme.typography.titleLarge
                )
            }
            if (isDay) {
                DetailButton(
                    onDetailsClick = onDetailsClick,
                    weather = weather,
                    isWhiteBorder = true
                )
            }
        }
    }
}

@Composable
private fun HoursRow(
    hours: List<Hour>,
    onHourClick: (Weather) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyRow(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.padding_small))
    ) {
        items(
            items = hours
        ) {
            HourCard(
                hour = it,
                onHourClick = onHourClick,
                modifier = Modifier
            )
        }
    }
}

@Composable
private fun HourCard(
    hour: Hour,
    onHourClick: (Weather) -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .clickable {
                onHourClick(hour)
            }
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
                contentDescription = hour.weatherCondition.condition,
                error = painterResource(id = R.drawable.ic_broken_image),
                placeholder = painterResource(id = R.drawable.ic_broken_image),
                modifier = Modifier
                    .padding(bottom = dimensionResource(id = R.dimen.padding_small))
                    .size(dimensionResource(id = R.dimen.small_image_size))
            )
            Text(
                text = stringResource(id = R.string.value_temp_c, hour.tempC.toInt()),
                modifier = Modifier.padding(bottom = dimensionResource(id = R.dimen.padding_small))
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ForecastScreenContentPreview() {
    WeatherTheme {
        ForecastScreenContent(
            day = Day(),
            onDetailsClick = {}
        )
    }
}