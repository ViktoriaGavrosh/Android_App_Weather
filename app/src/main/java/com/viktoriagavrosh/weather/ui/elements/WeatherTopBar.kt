package com.viktoriagavrosh.weather.ui.elements

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.viktoriagavrosh.weather.R
import com.viktoriagavrosh.weather.ui.navigation.NavigationDestination
import com.viktoriagavrosh.weather.ui.navigation.NavigationDestination.CurrentWeatherDestination
import com.viktoriagavrosh.weather.ui.navigation.NavigationDestination.ForecastDestination
import com.viktoriagavrosh.weather.ui.navigation.NavigationDestination.SettingsDestination
import com.viktoriagavrosh.weather.ui.theme.WeatherTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WeatherTopBar(
    selectedScreen: NavigationDestination,
    title: String,
    onCityChangeClick: (String, Context) -> Unit = { _, _ -> },
    onBackClick: () -> Unit = {},
    onSettingsClick: () -> Unit = {}
) {
    var showCityDialog by rememberSaveable {
        mutableStateOf(false)
    }
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = title,
                style = MaterialTheme.typography.titleLarge,
                modifier = if (selectedScreen == CurrentWeatherDestination
                ) {
                    Modifier.clickable { showCityDialog = true }
                } else {
                    Modifier
                }
            )
        },
        modifier = Modifier
            .padding(bottom = dimensionResource(id = R.dimen.padding_small))
            .clip(RoundedCornerShape(dimensionResource(id = R.dimen.corner_size))),
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            titleContentColor = Color.White
        ),
        navigationIcon = {
            if (selectedScreen != CurrentWeatherDestination) {
                IconButton(
                    onClick = { onBackClick() }
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_back),
                        contentDescription = stringResource(R.string.back),
                        modifier = Modifier
                            .padding(start = dimensionResource(id = R.dimen.padding_small))
                            .size(dimensionResource(id = R.dimen.top_bar_icon_size))
                    )
                }
            }
        },
        actions = {
            if (selectedScreen != SettingsDestination) {
                IconButton(
                    onClick = onSettingsClick
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_settings),
                        contentDescription = stringResource(id = R.string.settings)
                    )
                }
            }
        }
    )
    if (showCityDialog) {
        val context = LocalContext.current
        ChangeCityDialog(
            onConfirm = {
                showCityDialog = false
                onCityChangeClick(it, context)
            },
            onDismiss = { showCityDialog = false },
            modifier = Modifier.padding(dimensionResource(id = R.dimen.padding_medium))
        )
    }
}

@Composable
private fun ChangeCityDialog(
    modifier: Modifier = Modifier,
    onConfirm: (String) -> Unit,
    onDismiss: () -> Unit
) {
    var text by rememberSaveable {
        mutableStateOf("")
    }
    AlertDialog(
        onDismissRequest = onDismiss,
        confirmButton = {
            Button(
                onClick = { onConfirm(text) }
            ) {
                Text(
                    text = stringResource(R.string.ok),
                    style = MaterialTheme.typography.titleLarge
                )
            }
        },
        modifier = modifier,
        dismissButton = {
            OutlinedButton(
                onClick = onDismiss
            ) {
                Text(
                    text = stringResource(R.string.cancel),
                    style = MaterialTheme.typography.titleLarge
                )
            }
        },
        text = {
            OutlinedTextField(
                value = text,
                onValueChange = { text = it },
                textStyle = MaterialTheme.typography.titleLarge.copy(
                    color = MaterialTheme.colorScheme.primary
                ),
                label = {
                    Text(
                        text = stringResource(id = R.string.enter_city),
                        color = MaterialTheme.colorScheme.primary,
                        style = MaterialTheme.typography.titleLarge
                    )
                }
            )
        },
        containerColor = MaterialTheme.colorScheme.onPrimary
    )
}

@Preview
@Composable
fun ChangeCityDialogPreview() {
    WeatherTheme {
        ChangeCityDialog(
            onConfirm = {},
            onDismiss = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
fun WeatherTopBarPreview() {
    WeatherTheme {
        WeatherTopBar(
            selectedScreen = ForecastDestination,
            title = "City"
        )
    }
}