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
import com.viktoriagavrosh.weather.ui.elements.WeatherTopBar
import com.viktoriagavrosh.weather.ui.theme.WeatherTheme

@Composable
fun DetailsScreen(
    modifier: Modifier = Modifier,
    weatherDetails: Weather,
    data: String = "",
    dayAstro: DayAstro = DayAstro()
) {
    var isCurrentDay = true
    val windList = mutableListOf(
        stringResource(id = R.string.wind, weatherDetails.windSpeedKm.toInt())
    )
    val detailsList = mutableListOf(
        stringResource(id = R.string.precipitation, weatherDetails.precipitationMm),
        stringResource(id = R.string.humidity, weatherDetails.humidity.toInt())
    )
    when (weatherDetails) {
        is CurrentWeather -> {
            val weather: CurrentWeather = weatherDetails
            windList.add(stringResource(R.string.wind_gust, weather.windGustKm.toInt()))
            windList.add(stringResource(R.string.wind_direction, weather.windDirection))
            detailsList.add(stringResource(R.string.cloudy, weather.cloud.toInt()))
            detailsList.add(stringResource(id = R.string.pressure, weather.pressureMm.toInt()))
            detailsList.add(stringResource(R.string.visibility, weather.visibleKm.toInt()))
            detailsList.add(stringResource(R.string.uv_index, weather.uvIndex.toInt()))
        }

        is DayWeather -> {
            val weather: DayWeather = weatherDetails
            isCurrentDay = false
            detailsList.add(stringResource(R.string.visibility, weather.visibleKm.toInt()))
            detailsList.add(stringResource(R.string.uv_index, weather.uvIndex.toInt()))
        }
    }
    Column(
        modifier = modifier,
    ) {
        WeatherTopBar(
            text = if (isCurrentDay) stringResource(id = R.string.now) else data,
            isBack = true,
            isTitleClickable = false
        )
        Column(
            modifier = Modifier
                .padding(dimensionResource(id = R.dimen.padding_small))
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.padding_small))
        ) {
            MainCard(
                condition = weatherDetails.weatherCondition.condition,
                iconUri = weatherDetails.weatherCondition.iconUri,
                temp = weatherDetails.tempC
            )
            if (isCurrentDay) {
                DetailsCard(
                    detailsList = listOf(
                        stringResource(
                            id = R.string.feels_like,
                            (weatherDetails as CurrentWeather).feelsLikeTempC.toInt()
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
            if (!isCurrentDay) {
                AstroRow(
                    dayAstro = dayAstro, modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}

@Composable
private fun MainCard(
    modifier: Modifier = Modifier, condition: String, iconUri: String, temp: Double
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
    modifier: Modifier = Modifier, temp: Double, condition: String
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
            text = stringResource(id = R.string.value_temp_c, temp.toInt()),
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
            upTime = dayAstro.sunriseTime,
            downTime = dayAstro.sunsetTime,
            modifier = Modifier.weight(1F)
        )
        AstroCard(
            icon = R.drawable.ic_moon,
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
                    contentDescription = "",    // TODO add description
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
            contentDescription = ""       // TODO add description
        )
        Text(
            text = text,
            style = MaterialTheme.typography.titleLarge,
            textAlign = TextAlign.Center,
            //modifier = Modifier.fillMaxWidth()
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DetailsScreenPreview() {
    WeatherTheme {
        DetailsScreen(
            weatherDetails = DayWeather()
        )
    }
}