package com.viktoriagavrosh.weather.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectable
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.viktoriagavrosh.weather.R
import com.viktoriagavrosh.weather.ui.SettingsState
import com.viktoriagavrosh.weather.ui.elements.WeatherTopBar
import com.viktoriagavrosh.weather.ui.theme.WeatherTheme

@Composable
fun SettingsScreen(
    settings: SettingsState,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        WeatherTopBar(
            text = stringResource(R.string.settings),
            onCityClick = {}
        )
        Column(
            modifier = Modifier.padding(dimensionResource(id = R.dimen.padding_small))
        ) {
            MusicCard(
                text = stringResource(R.string.music),
                isMusic = settings.isMusic,
                modifier = Modifier.fillMaxWidth()
            )
            TempRadioButton(
                isCelsius = settings.isCelsius,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Composable
private fun MusicCard(
    text: String,
    isMusic: Boolean,
    modifier: Modifier = Modifier
) {
    //val
    Card(
        modifier = modifier
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = text,
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(
                    vertical = dimensionResource(id = R.dimen.padding_medium)
                )
            )
            Switch(
                checked = isMusic,
                onCheckedChange = {}
            )
        }
    }
}

@Composable
private fun TempRadioButton(
    isCelsius: Boolean,
    modifier: Modifier = Modifier
) {
    val listTemp = listOf(
        stringResource(R.string.temp_c),
        stringResource(R.string.temp_f)
    )
    val selectedTemp = if (isCelsius) listTemp[0] else listTemp[1]
    Card(
        modifier = modifier
            .padding(top = dimensionResource(id = R.dimen.padding_small))
    ) {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            listTemp.forEach { item ->
                Row(
                    modifier = Modifier.selectable(
                        selected = item == selectedTemp,
                        onClick = {}
                    )
                ) {
                    RadioButton(
                        selected = item == selectedTemp,
                        onClick = {}
                    )
                    Text(
                        text = item,
                        style = MaterialTheme.typography.titleLarge
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SettingsScreenPreview() {
    WeatherTheme {
        SettingsScreen(
            settings = SettingsState()
        )
    }
}