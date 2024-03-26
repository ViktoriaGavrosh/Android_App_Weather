package com.viktoriagavrosh.weather.ui.elements

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
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
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        WeatherCard(
            condition = weather.weatherCondition.condition,
            iconUri = weather.weatherCondition.iconUri,
            temp = weather.tempC,
            modifier = Modifier.fillMaxWidth()
        )
        DetailRow(
            title = stringResource(R.string.feels_like),
            value = "${weather.feelsLikeTempC.toInt()} ℃",
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = dimensionResource(id = R.dimen.padding_small))
        )
        DetailRow(
            title = stringResource(id = R.string.wind),
            value = "${weather.windSpeedKm.toInt()} km/h",
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = dimensionResource(id = R.dimen.padding_small))
        )
        DetailRow(
            title = stringResource(R.string.precipitation),
            value = "${weather.precipitationMm} mm",
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = dimensionResource(id = R.dimen.padding_small))
        )
        DetailRow(
            title = stringResource(R.string.pressure),
            value = "${weather.pressureMm.toInt()} mmHg",
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = dimensionResource(id = R.dimen.padding_small))
        )
        Button(
            onClick = { onDetailsClick() },
            modifier = Modifier
                .width(dimensionResource(id = R.dimen.button_width))
                .height(dimensionResource(id = R.dimen.button_height))
                .padding(dimensionResource(id = R.dimen.padding_large)),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.surfaceVariant
            )
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_arrow_down),
                contentDescription = stringResource(id = R.string.details)
            )
        }
    }
}

@Composable
fun DetailRow(
    title: String,
    value: String,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "$title $value",
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(
                    vertical = dimensionResource(id = R.dimen.padding_medium)
                )
            )
        }
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