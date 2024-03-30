package com.viktoriagavrosh.weather.ui.elements

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.viktoriagavrosh.weather.R
import com.viktoriagavrosh.weather.model.apimodel.CurrentWeather
import com.viktoriagavrosh.weather.model.apimodel.WeatherCondition
import com.viktoriagavrosh.weather.ui.theme.WeatherTheme

@Composable
fun CurrentWeatherColumn(
    weather: CurrentWeather,
    onDetailsClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val details = listOf(
        stringResource(id = R.string.feels_like, weather.feelsLikeTempC.toInt()),
        stringResource(id = R.string.wind, weather.windSpeedKm.toInt()),
        stringResource(id = R.string.precipitation, weather.precipitationMm),
        stringResource(id = R.string.humidity, weather.humidity.toInt()),
        stringResource(id = R.string.pressure, weather.pressureMm.toInt())
    )
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        WeatherCard(
            condition = weather.weatherCondition.condition,
            iconUri = weather.weatherCondition.iconUri,
            temp = weather.tempC,
            paddingValues = PaddingValues(dimensionResource(id = R.dimen.padding_medium)),
            modifier = Modifier.fillMaxWidth()
        )
        details.forEach {
            DetailRow(
                title = it,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = dimensionResource(id = R.dimen.padding_small))
            )
        }
        DetailButton(
            onDetailsClick = onDetailsClick,
            modifier = Modifier.padding(dimensionResource(id = R.dimen.padding_large))
        )
    }
}

@Composable
fun DetailButton(
    modifier: Modifier = Modifier,
    onDetailsClick: () -> Unit,
    isWhiteBorder: Boolean = false
) {
    OutlinedButton(
        onClick = { onDetailsClick() },
        modifier = modifier
            .width(dimensionResource(id = R.dimen.button_width))
            .height(dimensionResource(id = R.dimen.button_height)),
        border = BorderStroke(
            width = dimensionResource(id = R.dimen.button_border_width),
            color = if (isWhiteBorder) {
                MaterialTheme.colorScheme.onPrimary
            } else {
                MaterialTheme.colorScheme.surfaceVariant
            }
        )
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_arrow_down),
            contentDescription = stringResource(id = R.string.details),
            colorFilter = ColorFilter.tint(
                if (isWhiteBorder) {
                    MaterialTheme.colorScheme.onPrimary
                } else {
                    MaterialTheme.colorScheme.surfaceVariant
                }
            ),
            modifier = Modifier.size(dimensionResource(id = R.dimen.button_icon_size))
        )
    }
}

@Composable
fun DetailRow(
    title: String,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleLarge,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    vertical = dimensionResource(id = R.dimen.padding_medium)
                )
        )
    }
}

@Preview(showBackground = true)
@Composable
fun CurrentWeatherColumnPreview() {
    WeatherTheme {
        CurrentWeatherColumn(
            weather = CurrentWeather(
                weatherCondition = WeatherCondition(condition = "Sunny")
            ),
            onDetailsClick = {},
            modifier = Modifier.fillMaxWidth()
        )
    }
}