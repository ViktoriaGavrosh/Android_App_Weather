package com.viktoriagavrosh.weather.ui.screens

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.viktoriagavrosh.weather.R
import com.viktoriagavrosh.weather.model.apimodel.CurrentWeather
import com.viktoriagavrosh.weather.model.apimodel.DayAstro
import com.viktoriagavrosh.weather.model.apimodel.DayWeather
import com.viktoriagavrosh.weather.model.apimodel.Weather
import com.viktoriagavrosh.weather.model.apimodel.WeatherInfo
import com.viktoriagavrosh.weather.ui.elements.WeatherTopBar
import com.viktoriagavrosh.weather.ui.navigation.NavigationDestination
import com.viktoriagavrosh.weather.ui.theme.WeatherTheme

/**
 * Composable to display screen with details of day weather
 */
@Composable
fun DetailsScreen(
    modifier: Modifier = Modifier,
    weatherInfo: WeatherInfo,
    dateSelectedDay: String,
    isCelsius: Boolean,
    onBackClick: () -> Unit = {},
    onSettingsClick: () -> Unit = {}
) {
    if (dateSelectedDay.isEmpty()) {
        val currentWeather: CurrentWeather = weatherInfo.currentWeather
        DetailScreenContent(
            weatherInfo = weatherInfo,
            weather = currentWeather,
            isCurrentWeather = true,
            windList = if (isCelsius) {
                listOf(
                    stringResource(id = R.string.wind, currentWeather.windSpeedKm),
                    stringResource(R.string.wind_gust, currentWeather.windGustKm),
                    stringResource(R.string.wind_direction, currentWeather.windDirection)
                )
            } else listOf(
                stringResource(id = R.string.wind, currentWeather.windSpeedMile),
                stringResource(R.string.wind_gust, currentWeather.windGustMile),
                stringResource(R.string.wind_direction, currentWeather.windDirection)
            ),
            detailsList = if (isCelsius) {
                listOf(
                    stringResource(id = R.string.precipitation, currentWeather.precipitationMm),
                    stringResource(id = R.string.humidity, currentWeather.humidityPercent),
                    stringResource(R.string.cloudy, currentWeather.cloudy),
                    stringResource(id = R.string.pressure, currentWeather.pressureMm),
                    stringResource(R.string.visibility, currentWeather.visibleKm),
                    stringResource(R.string.uv_index, currentWeather.uVIndex)
                )
            } else listOf(
                stringResource(id = R.string.precipitation, currentWeather.precipitationInch),
                stringResource(id = R.string.humidity, currentWeather.humidityPercent),
                stringResource(R.string.cloudy, currentWeather.cloudy),
                stringResource(id = R.string.pressure, currentWeather.pressureIn),
                stringResource(R.string.visibility, currentWeather.visibleMile),
                stringResource(R.string.uv_index, currentWeather.uVIndex)
            ),
            topBarTitle = stringResource(id = R.string.now),
            isCelsius = isCelsius,
            onBackClick = onBackClick,
            onSettingsClick = onSettingsClick,
            modifier = modifier
        )
    } else {
        val selectedDay =
            weatherInfo.forecast.days.firstOrNull { it.date == dateSelectedDay }
        val dayWeather: DayWeather = selectedDay?.dayWeather ?: DayWeather()
        DetailScreenContent(
            weatherInfo = weatherInfo,
            weather = dayWeather,
            isCurrentWeather = false,
            windList = if (isCelsius) {
                listOf(stringResource(id = R.string.wind, dayWeather.windSpeedKm))
            } else listOf(stringResource(id = R.string.wind, dayWeather.windSpeedMile)),
            detailsList = if (isCelsius) {
                listOf(
                    stringResource(id = R.string.precipitation, dayWeather.precipitationMm),
                    stringResource(id = R.string.humidity, dayWeather.humidityPercent),
                    stringResource(R.string.visibility, dayWeather.visibleKm),
                    stringResource(R.string.uv_index, dayWeather.uVIndex)
                )
            } else listOf(
                stringResource(id = R.string.precipitation, dayWeather.precipitationInch),
                stringResource(id = R.string.humidity, dayWeather.humidityPercent),
                stringResource(R.string.visibility, dayWeather.visibleMile),
                stringResource(R.string.uv_index, dayWeather.uVIndex)
            ),
            dayAstro = selectedDay?.dayAstro ?: DayAstro(),
            topBarTitle = selectedDay?.date ?: "",
            isCelsius = isCelsius,
            onBackClick = onBackClick,
            onSettingsClick = onSettingsClick,
            modifier = modifier
        )
    }
}

@Composable
private fun DetailScreenContent(
    modifier: Modifier = Modifier,
    weatherInfo: WeatherInfo,
    onBackClick: () -> Unit = {},
    onSettingsClick: () -> Unit = {},
    topBarTitle: String,
    isCelsius: Boolean,
    weather: Weather,
    dayAstro: DayAstro = DayAstro(),
    isCurrentWeather: Boolean,
    windList: List<String>,
    detailsList: List<String>
) {
    Column(
        modifier = modifier
            .padding(dimensionResource(id = R.dimen.padding_small))
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.padding_small))
    ) {
        WeatherTopBar(
            selectedScreen = NavigationDestination.DetailsDestination,
            title = topBarTitle,
            onBackClick = onBackClick,
            onSettingsClick = onSettingsClick
        )
        MainCard(
            condition = weather.weatherCondition.condition,
            iconUri = weather.weatherCondition.iconUri,
            temp = if (isCelsius) weather.tempC else weather.tempF
        )
        if (isCurrentWeather) {
            DetailsCard(
                detailsList = listOf(
                    stringResource(
                        id = R.string.feels_like,
                        if (isCelsius) {
                            weatherInfo.currentWeather.feelsLikeTempC
                        } else {
                            weatherInfo.currentWeather.feelsLikeTempF
                        }
                    )
                ), modifier = Modifier.fillMaxWidth()
            )
        }
        DetailsCard(
            detailsList = windList, modifier = Modifier.fillMaxWidth()
        )
        DetailsCard(
            detailsList = detailsList, modifier = Modifier.fillMaxWidth()
        )
        if (!isCurrentWeather) {
            AstroRow(
                dayAstro = dayAstro,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Composable
private fun MainCard(
    modifier: Modifier = Modifier,
    condition: String,
    iconUri: String,
    temp: String
) {
    Card(
        modifier = modifier
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(dimensionResource(id = R.dimen.padding_medium)),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = ImageRequest.Builder(context = LocalContext.current).data(iconUri).build(),
                contentDescription = condition,
                error = painterResource(id = R.drawable.ic_broken_image),
                placeholder = painterResource(id = R.drawable.ic_broken_image),
                modifier = Modifier
                    .padding(start = dimensionResource(id = R.dimen.padding_medium))
                    .size(dimensionResource(id = R.dimen.image_size))
            )
            TempColumn(
                temp = temp,
                condition = condition,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = dimensionResource(id = R.dimen.padding_small))
            )
        }
    }
}

@Composable
private fun TempColumn(
    modifier: Modifier = Modifier, temp: String, condition: String
) {
    Column(
        modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = condition,
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = dimensionResource(id = R.dimen.padding_small))
        )
        Text(
            text = temp,
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.displayLarge,
            modifier = Modifier
        )
    }
}

@Composable
private fun DetailsCard(
    detailsList: List<String>, modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
    ) {
        Column(
            modifier = Modifier.padding(dimensionResource(id = R.dimen.padding_large)),
            verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.padding_small))
        ) {
            detailsList.forEach {
                Text(
                    text = it,
                    style = MaterialTheme.typography.titleLarge,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}

@Composable
private fun AstroRow(
    dayAstro: DayAstro, modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.padding_small))
    ) {
        AstroCard(
            icon = R.drawable.ic_sun,
            description = stringResource(id = R.string.sun),
            upTime = dayAstro.sunriseTime,
            downTime = dayAstro.sunsetTime,
            modifier = Modifier.weight(1F)
        )
        AstroCard(
            icon = R.drawable.ic_moon,
            description = stringResource(R.string.moon),
            upTime = dayAstro.moonriseTime,
            downTime = dayAstro.moonsetTime,
            moonPhase = dayAstro.moonPhase,
            modifier = Modifier.weight(1F)
        )
    }
}

@Composable
private fun AstroCard(
    modifier: Modifier = Modifier,
    @DrawableRes icon: Int,
    description: String,
    upTime: String,
    downTime: String,
    moonPhase: String = ""
) {
    Card(
        modifier = modifier
    ) {
        Column(
            modifier = Modifier.padding(vertical = dimensionResource(id = R.dimen.padding_large)),
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = icon),
                    contentDescription = description,
                    modifier = Modifier.padding(
                        start = dimensionResource(id = R.dimen.padding_medium),
                        end = dimensionResource(id = R.dimen.padding_small)
                    )
                )
                Column(
                    verticalArrangement = Arrangement
                        .spacedBy(dimensionResource(id = R.dimen.padding_small))
                ) {
                    AstroDetailRow(
                        isUp = true, text = upTime
                    )
                    AstroDetailRow(
                        isUp = false, text = downTime
                    )
                }
            }

            Text(
                text = moonPhase.ifEmpty { " " },
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(start = dimensionResource(id = R.dimen.padding_medium))
            )
        }
    }
}

@Composable
private fun AstroDetailRow(
    isUp: Boolean, text: String, modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
    ) {
        Image(
            painter = if (isUp) {
                painterResource(id = R.drawable.ic_up)
            } else {
                painterResource(id = R.drawable.ic_down)
            },
            contentDescription = if (isUp) {
                stringResource(R.string.up)
            } else {
                stringResource(R.string.down)
            },
            modifier = Modifier.testTag(text)
        )
        Text(
            text = text,
            style = MaterialTheme.typography.titleLarge,
            textAlign = TextAlign.Center
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DetailsScreenPreview() {
    WeatherTheme {
        DetailsScreen(
            weatherInfo = WeatherInfo(),
            isCelsius = true,
            dateSelectedDay = ""
        )
    }
}