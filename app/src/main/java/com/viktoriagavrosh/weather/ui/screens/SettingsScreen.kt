package com.viktoriagavrosh.weather.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.viktoriagavrosh.weather.R
import com.viktoriagavrosh.weather.model.Wallpaper
import com.viktoriagavrosh.weather.ui.Settings
import com.viktoriagavrosh.weather.ui.elements.WeatherTopBar
import com.viktoriagavrosh.weather.ui.theme.WeatherTheme

@Composable
fun SettingsScreen(
    settings: Settings,
    onMusicClick: () -> Unit,
    onCelsiusClick: (String) -> Unit,
    onWallpaperClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val listTemp = listOf(
        stringResource(R.string.temp_c),
        stringResource(R.string.temp_f)
    )
    Column(
        modifier = modifier
    ) {
        WeatherTopBar(
            text = stringResource(R.string.settings),
            isBack = true,
            isTitleClickable = false,
            onCityClick = {}
        )
        Column(
            modifier = Modifier
                .padding(dimensionResource(id = R.dimen.padding_small))
                .verticalScroll(rememberScrollState())
        ) {
            MusicCard(
                text = stringResource(R.string.music),
                isMusic = settings.isMusic,
                onMusicClick = onMusicClick,
                modifier = Modifier.fillMaxWidth()
            )
            SettingsRadioButton(
                title = stringResource(R.string.temperature),
                listText = listTemp,
                selectedButton = if (settings.isCelsius) listTemp[0] else listTemp[1],
                onButtonClick = onCelsiusClick,
                modifier = Modifier.fillMaxWidth()
            )
            SettingsRadioButton(
                title = stringResource(id = R.string.wallpaper),
                listText = Wallpaper.entries.map { it.name },
                selectedButton = settings.wallpaper.name,
                onButtonClick = onWallpaperClick,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Composable
private fun MusicCard(
    text: String,
    isMusic: Boolean,
    onMusicClick: () -> Unit,
    modifier: Modifier = Modifier
) {
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
                onCheckedChange = { onMusicClick.invoke() },
                thumbContent = {
                    Icon(
                        imageVector = Icons.Default.Check,
                        contentDescription = stringResource(R.string.music_on)
                    )
                }
            )
        }
    }
}

@Composable
private fun SettingsRadioButton(
    title: String,
    listText: List<String>,
    selectedButton: String,
    onButtonClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .padding(top = dimensionResource(id = R.dimen.padding_small))
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = dimensionResource(id = R.dimen.padding_small))
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )
            listText.forEach { item ->
                Row(
                    modifier = Modifier
                        .selectable(
                            selected = item == selectedButton,
                            onClick = { onButtonClick(item) }
                        )
                        .fillMaxWidth()
                        .padding(dimensionResource(id = R.dimen.padding_small)),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RadioButton(
                        selected = item == selectedButton,
                        onClick = { onButtonClick(item) }
                    )
                    Text(
                        text = item,
                        style = MaterialTheme.typography.titleSmall
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
            settings = Settings(),
            onMusicClick = {},
            onCelsiusClick = {},
            onWallpaperClick = {}
        )
    }
}